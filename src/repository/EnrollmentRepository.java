package repository;

import config.Database;
import models.courses.Enrollment;
import models.courses.MathCourse;
import service.ReadingFromFileService;
import service.Service;
import service.WritingInFileService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    public static void add(int studentId, int courseId){
        try(Connection connection = Database.getDatabase()){
            String query = "Insert into enrollment (id_student, id_course) VALUES(?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.execute();
        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while saving");
        }

    }

    public static void delete(int studentId, int courseId){
        try(Connection connection = Database.getDatabase()){
            String query = "delete from enrollment  where id_student = ? and id_course = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);


            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.execute();
        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }

    public static void load(){
        try(Connection connection = Database.getDatabase()){
            String query = "select id_student, id_course from enrollment";
            Statement statement = connection.createStatement();
            ResultSet resultset =  statement.executeQuery(query);


            while (resultset.next()){
                int studentId = resultset.getInt(1);
                int courseId = resultset.getInt(2);
                Enrollment.getInstance().addToCourse(studentId, courseId);
            }
        }
        catch(SQLException exception){
            throw new RuntimeException("Something went wrong while deleting");
        }
    }
}
