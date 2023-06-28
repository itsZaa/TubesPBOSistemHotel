package model;

public class RoomType {
    private int roomTypeId;
    private String typeName;
    private double price;

    public RoomType() {
    }

    public RoomType(int roomTypeId, String typeName, double price) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.price = price;
    }

    public int getRoomTypeId() {
        return this.roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
                " roomTypeId='" + getRoomTypeId() + "'" +
                ", typeName='" + getTypeName() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }
}