package service;

import models.courses.MathCourse;
import models.users.Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MathCourseService {
    public static List<MathCourse> MathCourseList = new ArrayList<>();

    public static MathCourseService instance = null;
    private MathCourseService() {}

    public static MathCourseService getInstance() {
        if(instance == null)
            instance = new MathCourseService();
        return instance;
    }

    public void addMathCourse(String name, int noHours, float price, String subject, int teacherId)  {
        MathCourseList.add(new MathCourse(name, noHours, price, subject, Service.getInstance().getTeacherById(teacherId)));

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(MathCourseList.get(MathCourseList.size() - 1).getId()));
        newRow.add(name);
        newRow.add(String.valueOf(noHours));
        newRow.add(String.valueOf(price));
        newRow.add(subject);
        newRow.add(String.valueOf(teacherId));

        WritingInFileService.getInstance().csvWrite("../resources/mathCourses.csv", newRow);

        System.out.println("\nThe MathCourse was written to the csv file\n");
    }

    public void readMathCourse() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("../resources/mathCourses.csv");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            int noHours = Integer.parseInt(lst.get(2));
            float price= Float.parseFloat(lst.get(3));
            String subject = lst.get(4);
            int idTeacher = Integer.parseInt(lst.get(5));

            MathCourseList.add(new MathCourse(name, noHours, price, subject, Service.getInstance().getTeacherById(idTeacher)));

        }
    }

}
