package controller;

import model.Customer;
import model.Laundry;
import model.LaundryTransaction;
import model.OrderStatus;
import model.User;
import model.RoomTransaction;
import model.Transaction;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LaundryController {
    private DatabaseController databaseController;
    private User user;

    public LaundryController(User user) {
        this.databaseController = new DatabaseController();
        this.user = user;
    }

    private RoomTransaction findTransaction() {
        RoomTransaction selected = databaseController.getRoomTransaction(user.getUsername());

        return selected;
    }

    public int getLamaInap() {
        int lamaInap = 0;

        RoomTransaction transaction = findTransaction();

        LocalDate checkIn = transaction.getDateCheckIn();
        LocalDate checkOut = transaction.getDateCheckOut();

        lamaInap = (int) ChronoUnit.DAYS.between(checkIn, checkOut);

        return lamaInap;
    }

    public Laundry getLaundryType(String laundryName){
        Laundry result = databaseController.getLaundry(laundryName);
        return result;
    }

    private double countTotalPrice(Laundry laundry, double beratLaundry){
        double totalPrice = Math.ceil(beratLaundry)*laundry.getPrice(); 
        return totalPrice;
    }

    public void createLaundryTransaction(User user, double beratLaundry, int roomNumber, Laundry laundry){
        LaundryTransaction transaction = new LaundryTransaction(1, user, roomNumber, countTotalPrice(laundry, beratLaundry), OrderStatus.IN_PROCESS, LocalDate.now(), null, laundry);

        databaseController.insertLaundryTransaction(transaction);
    }
}
