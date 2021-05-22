package service;

import models.users.Teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    
    public void addTeacher(Teacher teacher)  {
        teacherList.add(teacher);

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
        WritingInFileService.getInstance().csvWrite("src/resources/teachers.csv", newRow);

        System.out.println("\nThe Teacher was written to the csv file\n");
    }

    public void readTeacher() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("src/resources/teachers.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            String phoneNumber = lst.get(2);
            String email = lst.get(3);
            String address = lst.get(4);
            LocalDate birthDay = LocalDate.parse(lst.get(5), formatter);
            int noYearsExperience = Integer.parseInt(lst.get(6));
            float rating = Float.parseFloat(lst.get(7));
            float salary = Float.parseFloat(lst.get(8));

            teacherList.add(new Teacher(name, phoneNumber, email, address, birthDay, noYearsExperience, rating, salary));

        }
    }
}
