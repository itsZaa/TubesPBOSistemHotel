package model;

public class Staff extends User {
    private int staffId;
    private String NIK;
    private double salary;
    private StaffType staffType;

    public Staff() {
    }

    public Staff(int staffId, String NIK, double salary, StaffType staffType) {
        this.staffId = staffId;
        this.NIK = NIK;
        this.salary = salary;
        this.staffType = staffType;
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

    public StaffType getStaffType() {
        return this.staffType;
    }

    public void setStaffType(StaffType staffType) {
        this.staffType = staffType;
    }

    @Override
    public String toString() {
        return "{" +
                " staffId='" + getStaffId() + "'" +
                ", NIK='" + getNIK() + "'" +
                ", salary='" + getSalary() + "'" +
                ", staffType='" + getStaffType() + "'" +
                "}";
    }
}