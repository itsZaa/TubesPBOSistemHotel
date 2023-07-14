package controller;

import model.User;
import model.Transaction;
import model.RoomTransaction;
import model.FnBTransaction;
import model.LaundryTransaction;

import java.util.ArrayList;

public class HistoryTransactionController {
    private User user;
    private ArrayList<Transaction> listTransaction;

    public HistoryTransactionController(User user){
        this.user = user;
        this.listTransaction = new ArrayList<Transaction>();
    }

    public ArrayList<Transaction> getListTransaction(){
        ArrayList<RoomTransaction> roomTransactions = new DatabaseController().getRoomTransactionList(user);

        ArrayList<FnBTransaction> fnbTransactions = new DatabaseController().getFnBTransactionList(user);

        ArrayList<LaundryTransaction> laundryTransactions = new DatabaseController().getLaundryTransactionList(user);

        if(roomTransactions != null){
            this.listTransaction.addAll(roomTransactions);
        }

        if(fnbTransactions != null){
            this.listTransaction.addAll(fnbTransactions);
        }

        if(laundryTransactions != null){
            this.listTransaction.addAll(laundryTransactions);
        }

        return this.listTransaction;
    }
}
