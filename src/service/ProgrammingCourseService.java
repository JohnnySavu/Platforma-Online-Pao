package service;

import models.courses.ProgrammingCourse;
import models.users.Teacher;

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

    public void addProgrammingCourse(String name, int noHours, float price, List<String> projectsRequirments,
                                        int noProjects, String programmingLanguage, int id)  {
        ProgrammingCourseList.add(new ProgrammingCourse(name, noHours, price, projectsRequirments,
                                                        noProjects, programmingLanguage, Service.getInstance().getTeacherById(id)));

        List<String> newRow = new ArrayList<>();
        newRow.add(String.valueOf(ProgrammingCourseList.get(ProgrammingCourseList.size() - 1).getId()));//id = the latest index in the list
        newRow.add(name);
        newRow.add(String.valueOf(noHours));
        newRow.add(String.valueOf(price));
        newRow.add(programmingLanguage);
        newRow.add(String.valueOf(id));
        newRow.add(String.valueOf(noProjects));

        for (var requirement : projectsRequirments) {
            newRow.add(requirement);
        }

        WritingInFileService.getInstance().csvWrite("../resources/programmingCourses.csv", newRow);

        System.out.println("\nThe ProgrammingCourse was written to the csv file\n");
    }

    public void readProgrammingCourse() throws ParseException {
        ReadingFromFileService rffs = ReadingFromFileService.getInstance();
        List<List<String>> content = rffs.csvContent("../resources/programmingCourses.csv");
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
                for(int i = 7; i < lst.size(); ++i) {
                    projectRequirements.add(lst.get(i));
                }

            ProgrammingCourseList.add(new ProgrammingCourse( name, noHours, price, projectRequirements,
                                        noProjects, programmingLanguage, Service.getInstance().getTeacherById(id)));

        }
    }       
}
