package model;

public class RoomOrder {
    private int roomOrderId;
    private double orderPrice;

    public RoomOrder() {
    }

    public RoomOrder(int roomOrderId, double orderPrice) {
        this.roomOrderId = roomOrderId;
        this.orderPrice = orderPrice;
    }

    public int getRoomOrderId() {
        return this.roomOrderId;
    }

    public void setRoomOrderId(int roomOrderId) {
        this.roomOrderId = roomOrderId;
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
                " roomOrderId='" + getRoomOrderId() + "'" +
                ", orderPrice='" + getOrderPrice() + "'" +
                "}";
    }
}