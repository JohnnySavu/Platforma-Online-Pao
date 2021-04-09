package service;

import models.courses.Course;
import models.exceptions.NoSuchTeacherException;
import models.users.Admin;
import models.users.Student;
import models.users.Teacher;
import models.users.User;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<User> userList;
    private List<Course> courseList;

    public static Service instance;

    public static Service getInstance(){
        if (instance == null)
            instance = new Service();
        return instance;
    }

    private Service(){
        this.userList = new ArrayList<User>();
        this.courseList = new ArrayList<Course>();
    }


    public void addNewStudent(String nume, String phoneNumber, String email){
        Student aux = new Student(nume, phoneNumber, email);
        userList.add(aux);
    }

    public void addNewTeacher(String nume, String phoneNumber, String email){
        Teacher aux = new Teacher(nume, phoneNumber, email);
        userList.add(aux);
    }

    public void addNewAdmin(String nume, String phoneNumber, String email){
        Admin aux = new Admin(nume, phoneNumber, email);
        userList.add(aux);
    }

    public Teacher getTeacherById(int id){
        for (User user : userList)
            if (user instanceof Teacher && user.getId() == id)
                return (Teacher)user;
        return null;
    }

    public void addNewMathCourse(String name, int noHours, float price, String subject, int teacherId) throws NoSuchTeacherException {
        Teacher teacher = getTeacherById(teacherId);
        if (teacher == null)
            throw new NoSuchTeacherException("No teacher with this id");

    }



}
