package service;

import models.courses.ProgrammingCourse;
import models.users.Teacher;
import models.users.User;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ProgrammingCourseService {
    
    public static List<ProgrammingCourse> ProgrammingCourseList = new ArrayList<>();

    public static ProgrammingCourseService instance = null;
    private ProgrammingCourseService() {}

    public static ProgrammingCourseService getInstance() {
        if(instance == null)
            instance = new ProgrammingCourseService();
        return instance;
    }

    public void addProgrammingCourse(ProgrammingCourse course)  {
        ProgrammingCourseList.add(course);

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(course.getId()));//id = the latest index in the list
        newRow.add(course.getName());
        newRow.add(String.valueOf(course.getNoHours()));
        newRow.add(String.valueOf(course.getPrice()));
        newRow.add(course.getProgrammingLanguage());
        newRow.add(String.valueOf(course.getTeacher().getId()));
        newRow.add(String.valueOf(course.getNoProjects()));

        for (var requirement : course.getProjectsRequirments()) {
            newRow.add(requirement);
        }

        WritingInFileService.getInstance().csvWrite("src/resources/programmingCourses.csv", newRow);

        System.out.println("\nThe ProgrammingCourse was written to the csv file\n");
    }

    public void readProgrammingCourse() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("src/resources/programmingCourses.csv");
        for (List<String> lst: content) {
            int id = Integer.parseInt(lst.get(0));
            String name = lst.get(1);
            int noHours = Integer.parseInt(lst.get(2));
            float price= Float.parseFloat(lst.get(3));
            String programmingLanguage = lst.get(4);
            int idTeacher = Integer.parseInt(lst.get(5));
            int noProjects = Integer.parseInt(lst.get(6));
            List<String> projectRequirements = new ArrayList<>();
            if(noProjects > 0)
                for(int i = 0; i < lst.size(); ++i) {
                    projectRequirements.add(lst.get(i));
                }
            ProgrammingCourse aux = new ProgrammingCourse( name, noHours, price, projectRequirements,
                    noProjects, programmingLanguage, Service.getInstance().getTeacherById(idTeacher));
            ProgrammingCourse.setCounter(id);
            aux.setId(id);
            ProgrammingCourseList.add(aux);

        }
    }       
}
