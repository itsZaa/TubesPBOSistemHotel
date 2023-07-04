package model;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Favorite> favList;
    private ArrayList<Transaction> transactionList;

    public Customer() {
    }

    public Customer(String username, String fullname, String password, GenderType gender, String phoneNumber, String email, UserType type, ArrayList<Favorite> favList, ArrayList<Transaction> transactionList) {
        super(username, fullname, password, gender, phoneNumber, email, type);
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
        return super.toString() + " {" +
            " favList='" + getFavList() + "'" +
            ", transactionList='" + getTransactionList() + "'" +
            "}";
    }
}