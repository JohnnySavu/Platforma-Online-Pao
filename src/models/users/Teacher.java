package models.users;

import java.util.Objects;

//TODO : toString, equls, hashcode, clone

public class Teacher extends User implements Comparable<Teacher> {
    private int noYearsExperience;
    private float rating;
    protected float salary;

    public Teacher(){ }

    public Teacher(String name, String phoneNumber, String email){
        super(name, phoneNumber, email);
    }

    public int getNoYearsExperience() {
        return noYearsExperience;
    }

    public void setNoYearsExperience(int noYearsExperience) {
        this.noYearsExperience = noYearsExperience;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getSalary(){
        return this.salary;
    }

    @Override
    public String toString(){
        return "Teacher\n" + super.toString() + "rating : " + this.rating +"\n years of experience: "
                + this.noYearsExperience + "\n";
    }

    @Override
    public int compareTo(Teacher o) {
        if (this.rating < o.rating)
            return -1;
        else if (this.rating == o.rating)
            return 0;
        else
            return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return noYearsExperience == teacher.noYearsExperience && Float.compare(teacher.rating, rating) == 0 && Float.compare(teacher.salary, salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), noYearsExperience, rating, salary);
    }
}
