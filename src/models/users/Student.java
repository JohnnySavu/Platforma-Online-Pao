package models.users;

import java.util.Objects;

public class Student extends User{
    private float mathScore;
    private float programmingScore;



    public Student (){


    }

    public Student(String name, String phoneNumber, String email){
        super(name, phoneNumber, email);
    }

    public float getMathScore() {
        return mathScore;
    }

    public void setMathScore(float mathScore) {
        this.mathScore = mathScore;
    }

    public float getProgrammingScore() {
        return programmingScore;
    }

    public void setProgrammingScore(float programmingScore) {
        this.programmingScore = programmingScore;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Float.compare(student.mathScore, mathScore) == 0 && Float.compare(student.programmingScore, programmingScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mathScore, programmingScore);
    }

    @Override
    public String toString(){
        return  "Student\n" + super.toString();
    }
}
