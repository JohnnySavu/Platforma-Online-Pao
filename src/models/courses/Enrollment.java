package models.courses;

import models.users.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import models.users.Student;
import models.courses.Course;
import models.exceptions.NoSuchCourseException;

public class Enrollment {
    public static Enrollment instance;

    public static Enrollment getInstance(){
        if (instance == null)
            instance = new Enrollment();
        return instance;
    }

    private HashMap<Student, List<Course>> coursesList;

    private Enrollment(){
        coursesList = new HashMap<Student, List<Course>>();
    }

    public void addToCourse(Student student, Course course){

        List<Course> newList = coursesList.get(student);
        if (newList == null)
            newList = new ArrayList<Course>();

        newList.add(course);

        coursesList.put(student, newList);
    }

    public void removeFromCourse(Student student, Course course) throws NoSuchCourseException {
        List<Course> auxList = coursesList.get(student);
        if (auxList == null || !auxList.contains(course))
            throw new NoSuchCourseException("No such course for this user");

        auxList.remove(course);
    }

}
