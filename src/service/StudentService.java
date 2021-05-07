package service;

import models.users.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    public void addStudent(String name, String phoneNumber, String email, String address, LocalDate birthDay, float mathScore, float programmingScore)  {
        studentList.add(new Student(name, phoneNumber, email, address, birthDay, mathScore, programmingScore));

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(studentList.get(studentList.size() - 1).getId()));
        newRow.add(name);
        newRow.add(phoneNumber);
        newRow.add(email);
        newRow.add(address);
        newRow.add(new SimpleDateFormat("dd/MM/yyyy").format(birthDay));
        newRow.add(String.valueOf(mathScore));
        newRow.add(String.valueOf(programmingScore));
        WritingInFileService.getInstance().csvWrite("../resources/students.csv", newRow);

        System.out.println("\nThe student was written to the csv file\n");
    }

    public void readStudent() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("../resources/students.csv");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            String phoneNumber = lst.get(2);
            String email = lst.get(3);
            String adress = lst.get(4);
            LocalDate birthDay = LocalDate.parse(lst.get(5));
            float mathScore = Float.parseFloat(lst.get(6));
            float programmingScore = Float.parseFloat(lst.get(7));

            studentList.add(new Student(name,phoneNumber,email,adress,birthDay,mathScore,programmingScore));

        }
    }
}
