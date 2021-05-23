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

    private HashMap<Integer, List<Integer>> coursesList;

    private Enrollment(){
        coursesList = new HashMap<Integer, List<Integer>>();
    }

    public void addToCourse(Student student, Course course){

        List<Integer> newList = coursesList.get(student.getId());
        if (newList == null)
            newList = new ArrayList<Integer>();

        newList.add(course.getId());

        coursesList.put(student.getId(), newList);
    }

    public void removeFromCourse(Student student, Course course) throws NoSuchCourseException {
        List<Integer> auxList = coursesList.get(student.getId());
        if (auxList == null || !auxList.contains(course.getId()))
            throw new NoSuchCourseException("No such course for this user");

        auxList.remove(course.getId());
    }

}
