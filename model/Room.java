package model;

public class Room implements RoomStatus {
    private int roomNumber;
    private String roomType;
    private int status; // interface 0 = empty, 1 = full

    public Room() {
    }

    public Room(int roomNumber, String roomType, int status) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.status = status;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getRoomType() {
        return this.roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                " roomNumber='" + getRoomNumber() + "'" +
                ", roomType='" + getRoomType() + "'" +
                ", status='" + getStatus() + "'" +
                "}";
    }
}