package model;

import java.util.ArrayList;

public abstract class Transaction {
    private String transactionId;
    private User user;

    public Transaction() {
    }

    public Transaction(String transactionId, User user) {
        this.transactionId = transactionId;
        this.user = user;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public abstract ArrayList<Order> getOrderList();

    @Override
    public String toString() {
        return "{" +
                " transactionId='" + getTransactionId() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }
}