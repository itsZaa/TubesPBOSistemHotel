package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomTransaction extends Transaction {
    private int roomTransactionId; // belom ada di class diagram
    private LocalDate dateBooked;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
    private PaymentMethod paymentMethod;
    private ArrayList<RoomOrder> orderList;
    private double totalPrice;

    public RoomTransaction() {
    }

    public RoomTransaction(int transactionId, User user, int roomTransactionId, LocalDate dateBooked,
            LocalDate dateCheckIn, LocalDate dateCheckOut, PaymentMethod paymentMethod, ArrayList<RoomOrder> orderList,
            double totalPrice) {
        super(transactionId, user);
        this.roomTransactionId = roomTransactionId;
        this.dateBooked = dateBooked;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.paymentMethod = paymentMethod;
        this.orderList = orderList;
        this.totalPrice = totalPrice;
    }

    public int getRoomTransactionId() {
        return roomTransactionId;
    }

    public void setRoomTransactionId(int roomTransactionId) {
        this.roomTransactionId = roomTransactionId;
    }

    public LocalDate getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(LocalDate dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public ArrayList<RoomOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<RoomOrder> orderList) {
        this.orderList = orderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "{" +
                " roomTransactionId='" + getRoomTransactionId() + "'" +
                ", dateBooked='" + getDateBooked() + "'" +
                ", dateCheckIn='" + getDateCheckIn() + "'" +
                ", dateCheckOut='" + getDateCheckOut() + "'" +
                ", paymentMethod='" + getPaymentMethod() + "'" +
                ", orderList='" + getOrderList() + "'" +
                ", totalPrice='" + getTotalPrice() + "'" +
                "}";
    }
}