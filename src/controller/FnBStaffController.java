package controller;

import java.util.ArrayList;

import model.FnBTransaction;

public class FnBStaffController {
    private DatabaseController dbController;

    public FnBStaffController(){
        dbController = new DatabaseController();
    }

     public ArrayList<FnBTransaction> getUnprocessedTransactions(){
        return dbController.getUnprocessedFnBTransactions();
    }

    public boolean updateLaundryTransaction(FnBTransaction transaction){
        return dbController.updateFnBTransaction(transaction);
    }   
}