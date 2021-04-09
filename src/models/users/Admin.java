package models.users;

public class Admin extends User{

    public Admin(){}

    public Admin(String name, String phoneNumber, String email){
        super(name, phoneNumber, email);
    }

    public void changeSalary(Teacher obj, float ammount){
        obj.salary = ammount;
    }

    @Override
    public String toString() {
        return "Admin\n" + super.toString();
    }
}
