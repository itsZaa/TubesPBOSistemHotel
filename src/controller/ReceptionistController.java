package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import model.RoomOrder;
import model.RoomTransaction;
import model.RoomType;

public class ReceptionistController {
    public LocalDate adjustDate() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();
        if (currentTime.isBefore(LocalTime.of(14, 0))) {
            currentDateTime = currentDateTime.minusDays(1);
        }
        return currentDateTime.toLocalDate();
    }

    public ArrayList<Integer> getNewRoom(RoomTransaction transaction) {
        ArrayList<Integer> room = new ArrayList<>();
        ArrayList<RoomOrder> order = null;
        order = new DatabaseController().getRoomOrder(transaction.getTransactionId());
        ArrayList<RoomType> roomType = new ArrayList<>();

        if (order != null) {
            for (RoomOrder roomOrder : order) {
                RoomType type = roomOrder.getRoomType();
                boolean exist = false;
                for (RoomType roomTypes : roomType) {
                    if (roomTypes.getTypeName().equals(type.getTypeName())) {
                        exist = true;
                    }
                }
                if (!exist) {
                    roomType.add(type);
                    for (int i = 0; i < roomOrder.getQuantity(); i++) {
                        int roomNumber = new DatabaseController().searchRoom(type);
                        new DatabaseController().updateRoom(roomNumber, transaction.getDuration());
                        room.add(roomNumber);
                    }
                }
            }
        }
        return room;
    }
}
