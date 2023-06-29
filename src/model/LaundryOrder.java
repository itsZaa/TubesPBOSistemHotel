package model;

public class LaundryOrder {
    private int laundryOrderId;
    private String laundryName;
    private int quantity;
    private double orderPrice;

    public LaundryOrder() {
    }

    public LaundryOrder(int laundryOrderId, String laundryName, int quantity, double orderPrice) {
        this.laundryOrderId = laundryOrderId;
        this.laundryName = laundryName;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public int getLaundryOrderId() {
        return this.laundryOrderId;
    }

    public void setLaundryOrderId(int laundryOrderId) {
        this.laundryOrderId = laundryOrderId;
    }

    public String getLaundryName() {
        return this.laundryName;
    }

    public void setLaundryName(String laundryName) {
        this.laundryName = laundryName;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "{" +
                " laundryOrderId='" + getLaundryOrderId() + "'" +
                ", laundryName='" + getLaundryName() + "'" +
                ", quantity='" + getQuantity() + "'" +
                ", orderPrice='" + getOrderPrice() + "'" +
                "}";
    }
}