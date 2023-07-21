package model;

import java.time.LocalDate;

public class RoomOrder extends Order {
    private RoomType roomType;
    private String roomNumber;
    private LocalDate date;

    public RoomOrder(){
        
    }

    public RoomOrder(int quantity, RoomType roomType, LocalDate date) {
        super(quantity, roomType.getPrice() * quantity);
        this.roomType = roomType;
        this.date = date;
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


    public LocalDate getDate() {
        return date;
    }


    public void setDate(LocalDate date) {
        this.date = date;
    }

}