package model;

public class Order {
    private int quantity;
    private double orderPrice;

    public Order(){
        
    }

    public Order(int quantity, double orderPrice) {
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    @Override
    public String toString() {
        return "Order [quantity=" + quantity + ", orderPrice=" + orderPrice + "]";
    }
}