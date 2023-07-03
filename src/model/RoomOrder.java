package model;

public class RoomOrder extends Order {
    private RoomType roomType;
    private String roomNumber;

    public RoomOrder(int quantity, RoomType roomType) {
        super(quantity, roomType.getPrice() * quantity);
        this.roomType = roomType;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

}