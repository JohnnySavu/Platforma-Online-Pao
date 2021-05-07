package service;

import models.users.Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherService {
    public static List<Teacher> teacherList = new ArrayList<>();

    public static TeacherService instance = null;
    private TeacherService() {}

    public static TeacherService getInstance() {
        if(instance == null)
            instance = new TeacherService();
        return instance;
    }
    
    public void addTeacher(String name, String phoneNumber, String email, String address, LocalDate birthDay, int noYearsExperience, float rating, float salary)  {
        teacherList.add(new Teacher(name, phoneNumber, email, address, birthDay, noYearsExperience, rating, salary));

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(teacherList.get(teacherList.size() - 1).getId()));
        newRow.add(name);
        newRow.add(phoneNumber);
        newRow.add(email);
        newRow.add(address);
        newRow.add(new SimpleDateFormat("dd/MM/yyyy").format(birthDay));
        newRow.add(String.valueOf(noYearsExperience));
        newRow.add(String.valueOf(rating));
        newRow.add(String.valueOf(salary));
        WritingInFileService.getInstance().csvWrite("../resources/teachers.csv", newRow);

        System.out.println("\nThe Teacher was written to the csv file\n");
    }

    public void readTeacher() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("../resources/teachers.csv");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            String phoneNumber = lst.get(2);
            String email = lst.get(3);
            String address = lst.get(4);
            LocalDate birthDay = LocalDate.parse(lst.get(5));
            int noYearsExperience = Integer.parseInt(lst.get(6));
            float rating = Float.parseFloat(lst.get(7));
            float salary = Float.parseFloat(lst.get(8));

            teacherList.add(new Teacher(name, phoneNumber, email, address, birthDay, noYearsExperience, rating, salary));

        }
    }
}
