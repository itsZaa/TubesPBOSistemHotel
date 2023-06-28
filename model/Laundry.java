package model;

public class Laundry {
    private String laundryName;
    private double price;

    public Laundry() {
    }

    public Laundry(String laundryName, double price) {
        this.laundryName = laundryName;
        this.price = price;
    }

    public String getLaundryName() {
        return this.laundryName;
    }

    public void setLaundryName(String laundryName) {
        this.laundryName = laundryName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                " laundryName='" + getLaundryName() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }
}