package repository;


import config.Database;
import models.courses.MathCourse;
import service.ReadingFromFileService;
import service.Service;
import service.WritingInFileService;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MathCourseRepository {

    public static void save(MathCourse course){
        try(Connection connection = Database.getDatabase()){
            String query = "Insert into math_courses (id, info) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(course.getId()));
            newRow.add(course.getName());
            newRow.add(String.valueOf(course.getNoHours()));
            newRow.add(String.valueOf(course.getPrice()));
            newRow.add(course.getSubject());
            newRow.add(String.valueOf(course.getTeacher().getId()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();


        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while saving");
        }
    }

    public static List<MathCourse> load(){
        String query = "select info from math_courses";
        try(Connection connection = Database.getDatabase()){
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery(query);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            List <MathCourse> ans = new ArrayList<>();
            while (resultset.next()){
                List<String> lst = ReadingFromFileService.getInstance().databaseContent(
                        resultset.getString(1)
                );
                int id = Integer.parseInt(lst.get(0));
                String name = lst.get(1);
                int noHours = Integer.parseInt(lst.get(2));
                float price= Float.parseFloat(lst.get(3));
                String subject = lst.get(4);
                int idTeacher = Integer.parseInt(lst.get(5));
                MathCourse aux = new MathCourse(name, noHours, price, subject, Service.getInstance().getTeacherById(idTeacher));
                MathCourse.setCounter(id);
                aux.setId(id);
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
            String query = "delete from math_courses where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public static void update(MathCourse course){
        try(Connection connection = Database.getDatabase()){
            String query = "update math_courses set info = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(course.getId()));
            newRow.add(course.getName());
            newRow.add(String.valueOf(course.getNoHours()));
            newRow.add(String.valueOf(course.getPrice()));
            newRow.add(course.getSubject());
            newRow.add(String.valueOf(course.getTeacher().getId()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();


        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while updating");
        }
    }


}
