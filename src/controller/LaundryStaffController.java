package controller;

import model.LaundryTransaction;
import java.util.ArrayList;

public class LaundryStaffController {
    public ArrayList<LaundryTransaction> getUnprocessedLaundryTransaction(){
        return new DatabaseController().getUnprocessedLaundryTransactions();
    }

    public boolean updateLaundryTransaction(LaundryTransaction transaction){
        boolean result = new DatabaseController().updateLaundryTransaction(transaction);
        return result;
    }
}
