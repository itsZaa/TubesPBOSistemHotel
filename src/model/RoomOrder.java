package model;

public class RoomOrder extends Order {
    private RoomType roomType;
    private String roomNumber;
    private int duration;

    public RoomOrder(int quantity, RoomType roomType, int duration) {
        super(quantity * duration, roomType.getPrice() * quantity * duration);
        this.roomType = roomType;
        this.duration = duration;
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


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

}