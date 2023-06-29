package model;

public class FnBOrder {
    private int orderId;
    private String menuName;
    private double orderPrice;
    private int quantity;

    public FnBOrder() {
    }

    public FnBOrder(int orderId, String menuName, double orderPrice, int quantity) {
        this.orderId = orderId;
        this.menuName = menuName;
        this.orderPrice = orderPrice;
        this.quantity = quantity;
    }

    public int getOrderId() {
        return this.orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public double getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                " orderId='" + getOrderId() + "'" +
                ", menuName='" + getMenuName() + "'" +
                ", orderPrice='" + getOrderPrice() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }
}