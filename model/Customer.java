package model;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Favorite> favList;
    private ArrayList<Transaction> transactionList;

    public Customer() {
    }

    public Customer(ArrayList<Favorite> favList, ArrayList<Transaction> transactionList) {
        this.favList = favList;
        this.transactionList = transactionList;
    }

    public ArrayList<Favorite> getFavList() {
        return this.favList;
    }

    public void setFavList(ArrayList<Favorite> favList) {
        this.favList = favList;
    }

    public ArrayList<Transaction> getTransactionList() {
        return this.transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }


    @Override
    public String toString() {
        return "{" +
            " favList='" + getFavList() + "'" +
            ", transactionList='" + getTransactionList() + "'" +
            "}";
    }
}