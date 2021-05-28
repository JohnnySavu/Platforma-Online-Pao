package repository;



import config.Database;
import models.users.Teacher;
import service.ReadingFromFileService;
import service.WritingInFileService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TeacherRepository {

    private TeacherRepository(){}

    public static void save(Teacher teacher){
        try(Connection connection = Database.getDatabase()){
            String query = "Insert into teachers (id, info) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(teacher.getId()));
            newRow.add(teacher.getName());
            newRow.add(teacher.getPhoneNumber());
            newRow.add(teacher.getEmail());
            newRow.add(teacher.getAdress());
            newRow.add(teacher.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            newRow.add(String.valueOf(teacher.getNoYearsExperience()));
            newRow.add(String.valueOf(teacher.getRating()));
            newRow.add(String.valueOf(teacher.getSalary()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, teacher.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();


        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while saving");
        }
    }

    public static List<Teacher> load(){
        String query = "select info from teachers";
        try(Connection connection = Database.getDatabase()){
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery(query);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List <Teacher> ans = new ArrayList<>();
            while (resultset.next()){
                List<String> lst = ReadingFromFileService.getInstance().databaseContent(
                        resultset.getString(1)
                );
                int id = Integer.parseInt(lst.get(0));
                String name = lst.get(1);
                String phoneNumber = lst.get(2);
                String email = lst.get(3);
                String address = lst.get(4);
                LocalDate birthDay = LocalDate.parse(lst.get(5), formatter);
                int noYearsExperience = Integer.parseInt(lst.get(6));
                float rating = Float.parseFloat(lst.get(7));
                float salary = Float.parseFloat(lst.get(8));
                Teacher aux = new Teacher(name, phoneNumber, email, address, birthDay, noYearsExperience, rating, salary);
                aux.setId(id);
                Teacher.setCounter(id);
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
            String query = "delete from teachers where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public static void update(Teacher teacher){
        try(Connection connection = Database.getDatabase()){
            String query = "update teachers set info = ? where id = " + teacher.getId();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(teacher.getId()));
            newRow.add(teacher.getName());
            newRow.add(teacher.getPhoneNumber());
            newRow.add(teacher.getEmail());
            newRow.add(teacher.getAdress());
            newRow.add(teacher.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            newRow.add(String.valueOf(teacher.getNoYearsExperience()));
            newRow.add(String.valueOf(teacher.getRating()));
            newRow.add(String.valueOf(teacher.getSalary()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setString(1, info);
            preparedStatement.execute();


        }catch(SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("Something went wrong while updating");
        }
    }


}
