package model;

public abstract class Transaction {
    private int transactionId;
    private User user;

    public Transaction() {
    }

    public Transaction(int transactionId, User user) {
        this.transactionId = transactionId;
        this.user = user;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
                " transactionId='" + getTransactionId() + "'" +
                ", user='" + getUser() + "'" +
                "}";
    }
}