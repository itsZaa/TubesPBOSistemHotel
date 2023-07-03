package model;

public class RoomType {
    private int roomTypeId;
    private String typeName;
    private double price;
    private int numberOfRoom;

    public RoomType() {
    }

    public RoomType(int roomTypeId, String typeName, double price, int room) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.price = price;
        this.numberOfRoom = room;
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

    public int getNumberOfRoom() {
        return this.numberOfRoom;
    }

    public void setNumberOfRoom(int room) {
        this.numberOfRoom = room;
    }

    @Override
    public String toString() {
        return "{" +
                " roomTypeId='" + getRoomTypeId() + "'" +
                ", typeName='" + getTypeName() + "'" +
                ", price='" + getPrice() + "'" +
                ", total room='" + getNumberOfRoom() + "'" +
                "}";
    }
}