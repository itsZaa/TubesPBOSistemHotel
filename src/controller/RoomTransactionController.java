package controller;

import java.util.Random;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RoomTransactionController {

    public String generateTransactionID() {

        LocalDateTime now = LocalDateTime.now();

        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("ddMMyyHHmmss"));

        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 5));

        return "ROOM_" + formattedDateTime + "_" + String.format("%0" + 5 + "d", randomNumber);
    }

    public boolean checkRoomAvailability () {
        
    }

}
