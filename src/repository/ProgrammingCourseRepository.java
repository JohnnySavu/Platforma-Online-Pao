package repository;

import config.Database;
import models.courses.ProgrammingCourse;
import service.ReadingFromFileService;
import service.Service;
import service.WritingInFileService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgrammingCourseRepository {

    public static void save(ProgrammingCourse course){
        try(Connection connection = Database.getDatabase()){
            String query = "Insert into programming_courses (id, info) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(course.getId()));//id = the latest index in the list
            newRow.add(course.getName());
            newRow.add(String.valueOf(course.getNoHours()));
            newRow.add(String.valueOf(course.getPrice()));
            newRow.add(course.getProgrammingLanguage());
            newRow.add(String.valueOf(course.getTeacher().getId()));
            newRow.add(String.valueOf(course.getNoProjects()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();


        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while saving");
        }
    }

    public static List<ProgrammingCourse> load(){
        String query = "select info from programming_courses";
        try(Connection connection = Database.getDatabase()){
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery(query);


            List <ProgrammingCourse> ans = new ArrayList<>();
            while (resultset.next()){
                List<String> lst = ReadingFromFileService.getInstance().databaseContent(
                        resultset.getString(1)
                );
                int id = Integer.parseInt(lst.get(0));
                String name = lst.get(1);
                int noHours = Integer.parseInt(lst.get(2));
                float price= Float.parseFloat(lst.get(3));
                String programmingLanguage = lst.get(4);
                int idTeacher = Integer.parseInt(lst.get(5));
                int noProjects = Integer.parseInt(lst.get(6));
                List<String> projectRequirements = new ArrayList<>();
                if(noProjects > 0)
                    projectRequirements.addAll(lst);

                ProgrammingCourse aux = new ProgrammingCourse( name, noHours, price, projectRequirements,
                        noProjects, programmingLanguage, Service.getInstance().getTeacherById(idTeacher));
                ProgrammingCourse.setCounter(id);
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
            String query = "delete from programming_courses where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public static void update(ProgrammingCourse course){
        try(Connection connection = Database.getDatabase()){
            String query = "update programming_courses set info = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            List<String> newRow = new ArrayList<>();
            newRow.add(String.valueOf(course.getId()));//id = the latest index in the list
            newRow.add(course.getName());
            newRow.add(String.valueOf(course.getNoHours()));
            newRow.add(String.valueOf(course.getPrice()));
            newRow.add(course.getProgrammingLanguage());
            newRow.add(String.valueOf(course.getTeacher().getId()));
            newRow.add(String.valueOf(course.getNoProjects()));

            String info = WritingInFileService.getInstance().databaseWrite(newRow);

            preparedStatement.setInt(1, course.getId());
            preparedStatement.setString(2, info);
            preparedStatement.execute();



        }catch(SQLException exception){
            throw new RuntimeException("Something went wrong while updating");
        }
    }


}
