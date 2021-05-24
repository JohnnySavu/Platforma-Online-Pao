

package models.courses;

import java.util.Objects;

import models.quiz.Quiz;
import models.users.Teacher;


public abstract class Course implements Comparable<Course>{
    protected int id;
    protected static int counter;
    protected String name;
    protected int noHours;
    protected float price;
    Teacher teacher;
    Quiz finalQuiz;


    public Course(){
        this.id = Course.counter;
        ++counter;
    }

    public Course(String name, int noHours, float price, Teacher teacher){
        this.name = name;
        this.noHours = noHours;
        this.price = price;
        this.id = Course.counter;
        this.teacher = teacher;
        ++counter;

    }

    public Quiz getFinalQuiz() {
        return finalQuiz;
    }

    public void setFinalQuiz(Quiz finalQuiz) {
        this.finalQuiz = finalQuiz;
    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        ++counter;
        if (Course.counter < counter)
            Course.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoHours() {
        return noHours;
    }

    public void setNoHours(int noHours) {
        this.noHours = noHours;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int compareTo(Course o) {

        if (this.price < o.price)
            return -1;
        else if (this.price == o.price)
            return 0;
        else
            return 1;
    }

    @Override
    public String toString(){
        return "ID:" + this.id + "\n Nume:" + this.name + "\n" +
                "Duration of the course in hours : " + this.noHours + "\n" + " Price: " + this.price;
    }


    @Override
    public boolean equals (Object o){
        if(o == null)
            return false;
        if(o instanceof Course)
            return this.id == ((Course)o).id;

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, noHours, price);
    }



}
