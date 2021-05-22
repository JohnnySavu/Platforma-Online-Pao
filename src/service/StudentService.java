package service;

import models.users.Student;
import models.users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static List<Student> studentList = new ArrayList<>();

    public static StudentService instance = null;
    private StudentService() {}

    public static StudentService getInstance() {
        if(instance == null)
            instance = new StudentService();
        return instance;
    }

    public void addStudent(Student student)  {
        studentList.add(student);

        List<String> newRow = new ArrayList<>();

        newRow.add(String.valueOf(student.getId()));
        newRow.add(student.getName());
        newRow.add(student.getPhoneNumber());
        newRow.add(student.getEmail());
        newRow.add(student.getAdress());
        newRow.add(student.getBirthday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        newRow.add(String.valueOf(student.getMathScore()));
        newRow.add(String.valueOf(student.getProgrammingScore()));
        WritingInFileService.getInstance().csvWrite("src/resources/students.csv", newRow);

        System.out.println("\nThe student was written to the csv file\n");
    }

    public void readStudent() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("src/resources/students.csv");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            String phoneNumber = lst.get(2);
            String email = lst.get(3);
            String adress = lst.get(4);
            LocalDate birthDay = LocalDate.parse(lst.get(5), formatter);
            float mathScore = Float.parseFloat(lst.get(6));
            float programmingScore = Float.parseFloat(lst.get(7));

            studentList.add(new Student(name,phoneNumber,email,adress,birthDay,mathScore,programmingScore));

        }

    }
}
