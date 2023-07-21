package controller;

import java.util.ArrayList;

import model.FnBTransaction;

public class FnBStaffController {
    private DatabaseController dbController;

    public FnBStaffController() {
        dbController = new DatabaseController();
    }

    public ArrayList<FnBTransaction> getUnprocessedFnBTransactions() {
        return dbController.getUnprocessedFnBTransactions();
    }

    public boolean updateFnBTransaction(FnBTransaction transaction) {
        return dbController.updateFnBTransaction(transaction);
    }
}