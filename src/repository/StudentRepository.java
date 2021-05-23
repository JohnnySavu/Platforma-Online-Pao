package repository;



import config.Database;
import models.users.Student;
import service.ReadingFromFileService;
import service.WritingInFileService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public static void save(Student student){
        try(Connection connection = Database.getDatabase()){
            String query = "Insert into students (id, info) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();

            newRow.add(String.valueOf(student.getId()));
            newRow.add(student.getName());
            newRow.add(student.getPhoneNumber());
            newRow.add(student.getEmail());
            newRow.add(student.getAdress());
            newRow.add(student.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            newRow.add(String.valueOf(student.getMathScore()));
            newRow.add(String.valueOf(student.getProgrammingScore()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();


        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while saving");
        }
    }

    public static List<Student> load(){
        String query = "select info from students";
        try(Connection connection = Database.getDatabase()){
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery(query);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List <Student> ans = new ArrayList<>();
            while (resultset.next()){
                List<String> lst = ReadingFromFileService.getInstance().databaseContent(
                        resultset.getString(1)
                );
                int id = Integer.parseInt(lst.get(0));
                String name = lst.get(1);
                String phoneNumber = lst.get(2);
                String email = lst.get(3);
                String adress = lst.get(4);
                LocalDate birthDay = LocalDate.parse(lst.get(5), formatter);
                float mathScore = Float.parseFloat(lst.get(6));
                float programmingScore = Float.parseFloat(lst.get(7));
                Student aux = new Student(name,phoneNumber,email,adress,birthDay,mathScore,programmingScore);
                aux.setId(id);
                Student.setCounter(id);
                ans.add(aux);
            }
            return ans;
        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while loading");
        }
    }

    public static void delete(int id){
        try(Connection connection = Database.getDatabase()){
            String query = "delete from students where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public static void update(Student student){
        try(Connection connection = Database.getDatabase()){
            String query = "update students set info = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();

            newRow.add(String.valueOf(student.getId()));
            newRow.add(student.getName());
            newRow.add(student.getPhoneNumber());
            newRow.add(student.getEmail());
            newRow.add(student.getAdress());
            newRow.add(student.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            newRow.add(String.valueOf(student.getMathScore()));
            newRow.add(String.valueOf(student.getProgrammingScore()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(2, student.getId());
            preparedStatement.setString(1, info);
            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while updating");
        }
    }


}
