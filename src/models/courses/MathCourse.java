package models.courses;

import java.util.List;
import java.util.Objects;

import models.users.Teacher;

public class MathCourse extends Course{
    private String subject;

    public MathCourse(){

    }

    public MathCourse(String name, int noHours, float price, String subject, Teacher teacher){
        super(name, noHours, price, teacher);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathCourse that = (MathCourse) o;
        return Objects.equals(subject, that.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject);
    }
}
