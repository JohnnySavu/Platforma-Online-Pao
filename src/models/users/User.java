package models.users;

import java.time.LocalDate;
import java.util.Objects;

public abstract class User implements Comparable<User>{
    protected int id;
    public static int counter = 0;
    private String name;
    protected String phoneNumber;
    protected String email;
    protected String adress = "Empty";
    protected LocalDate birthday;


    public User(){
        this.id = User.counter;
        ++User.counter;
    }

    public User(String name, String phoneNumber, String email){
        this.id = User.counter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        ++User.counter;
    }

    public User(String name, String phoneNumber, String email, String adress, LocalDate birthday) {
        this.id = User.counter;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.adress = adress;
        this.birthday = birthday;
        ++User.counter;
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
        if(User.counter < counter)
            User.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        return "ID " + this.id + "\n Name: " + this.name + "\n Email: " + this.email + "\n Phone number: " + this.phoneNumber + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(adress, user.adress) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phoneNumber, email, adress, birthday);
    }

    @Override
    public int compareTo(User o) {
        if (this.id < o.id)
            return -1;
        else if (this.id == o.id)
            return 0;
        else return 1;

    }
}
