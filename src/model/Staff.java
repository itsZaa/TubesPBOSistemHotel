package model;

public class Staff extends User {
    private int staffId;
    private String NIK;
    private double salary;

    public Staff() {
    }

    public Staff(int staffId, String NIK, double salary, String username, String fullname, String address,String password, GenderType gender, String phoneNumber, String email, UserType type) {
        super(username,fullname,address,password,gender,phoneNumber,email,type); 
        this.staffId = staffId;
        this.NIK = NIK;
        this.salary = salary;
    }

    public int getStaffId() {
        return this.staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getNIK() {
        return this.NIK;
    }

    public void setNIK(String NIK) {
        this.NIK = NIK;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "{" +
                " staffId='" + getStaffId() + "'" +
                ", NIK='" + getNIK() + "'" +
                ", salary='" + getSalary() + "'" +
                "}";
    }
}