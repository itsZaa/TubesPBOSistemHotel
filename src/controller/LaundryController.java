package controller;

import model.Customer;
import model.RoomTransaction;
import model.Transaction;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LaundryController {
    private Customer customer;

    public LaundryController(Customer customer) {
        this.customer = customer;
    }

    private RoomTransaction findTransaction() {
        RoomTransaction selected = new DatabaseController().getRoomTransaction(customer.getUsername());

        // for (Transaction transaction : customer.getTransactionList()) {
        //     if (transaction instanceof RoomTransaction) {
        //         RoomTransaction tr = (RoomTransaction) transaction;
        //         if ((LocalDate.now().isEqual(tr.getDateCheckIn()) || LocalDate.now().isAfter(tr.getDateCheckIn())) && (LocalDate.now().isEqual(tr.getDateCheckOut()) || LocalDate.now().isBefore(tr.getDateCheckOut()))) {
        //             selected = tr;
        //             break;
        //         }
        //     }
        // }

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
}
