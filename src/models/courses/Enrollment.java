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

    public List<Integer> getCourses(int id){
        return coursesList.get(id);
    }

    public void addToCourse(int studentId, int courseId){

        List<Integer> newList = coursesList.get(studentId);
        if (newList == null)
            newList = new ArrayList<Integer>();

        newList.add(courseId);

        coursesList.put(studentId, newList);
    }

    public void removeFromCourse(int studentId, int courseId) throws NoSuchCourseException {
        List<Integer> auxList = coursesList.get(studentId);
        if (auxList == null || !auxList.contains(courseId))
            throw new NoSuchCourseException("No such course for this user");

        auxList.remove(courseId);
    }

}
