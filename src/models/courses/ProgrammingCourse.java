package models.courses;

import java.util.List;
import java.util.Objects;
import models.users.Teacher;

public class ProgrammingCourse extends Course{
    private List<String> projectsRequirments;
    private int noProjects;
    private String programmingLanguage;

    public ProgrammingCourse (String name, int noHours, float price, List<String> projectsRequirments,
                              int noProjects, String programmingLanguage, Teacher teacher){
        super(name, noHours, price, teacher);
        this.programmingLanguage = programmingLanguage;
        this.projectsRequirments = projectsRequirments;
        this.noProjects = noProjects;
    }


    public List<String> getProjectsRequirments() {
        return projectsRequirments;
    }

    public void setProjectsRequirments(List<String> projectsRequirments) {
        this.projectsRequirments = projectsRequirments;
    }

    public int getNoProjects() {
        return noProjects;
    }

    public void setNoProjects(int noProjects) {
        this.noProjects = noProjects;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProgrammingCourse that = (ProgrammingCourse) o;
        return noProjects == that.noProjects && Objects.equals(projectsRequirments, that.projectsRequirments) && Objects.equals(programmingLanguage, that.programmingLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), projectsRequirments, noProjects, programmingLanguage);
    }
}
