package service;



import config.Database;
import models.courses.Enrollment;
import models.courses.MathCourse;
import models.users.Student;
import repository.EnrollmentRepository;
import service.ReadingFromFileService;
import service.WritingInFileService;

import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class EnrollmentService {

    public static EnrollmentService instance = null;
    private EnrollmentService() {}

    public static EnrollmentService getInstance() {
        if(instance == null)
            instance = new EnrollmentService();
        return instance;
    }

    public void addEnrollment(int studentId, int courseId){

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(studentId));
        newRow.add(String.valueOf(courseId));

        WritingInFileService.getInstance().csvWrite("src/resources/enrollment.csv", newRow);

        System.out.println("\nThe MathCourse was written to the csv file\n");
    }

    public void loadEnrollment() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("src/resources/enrollment.csv");
        for (List<String> lst: content) {
            int studentId = Integer.parseInt(lst.get(0));
            int courseId = Integer.parseInt(lst.get(1));
            Enrollment.getInstance().addToCourse(studentId, courseId);
        }
    }

}
