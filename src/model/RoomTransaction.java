package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class RoomTransaction extends Transaction {
    private LocalDate dateCheckIn;
    private int duration;
    private TransactionStatus status;
    private ArrayList<Order> orderList;

    private Date dateBooked;
    private Date timeStampCheckIn;
    private Date timeStampCheckOut;

    private double totalPrice;

    public RoomTransaction() {
    }

    public RoomTransaction(User user) {
        super("", user);
    }

    public RoomTransaction(LocalDate dateCheckIn, int duration, TransactionStatus status, ArrayList<Order> orderList,
            Date dateBooked, Date timeStampCheckIn, Date timeStampCheckOut, double totalPrice) {
        this.dateCheckIn = dateCheckIn;
        this.duration = duration;
        this.status = status;
        this.orderList = orderList;
        this.dateBooked = dateBooked;
        this.totalPrice = totalPrice;
    }

    public RoomTransaction(String transactionId, User user, LocalDate dateCheckIn, int duration,
            TransactionStatus status, ArrayList<Order> orderList, Date dateBooked, Date timeStampCheckIn,
            Date timeStampCheckOut, double totalPrice) {
        super(transactionId, user);
        this.dateCheckIn = dateCheckIn;
        this.duration = duration;
        this.status = status;
        this.orderList = orderList;
        this.dateBooked = dateBooked;
        this.timeStampCheckIn = timeStampCheckIn;
        this.timeStampCheckOut = timeStampCheckOut;
        this.totalPrice = totalPrice;
    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
    }

    @Override
    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getTimeStampCheckIn() {
        return timeStampCheckIn;
    }

    public void setTimeStampCheckIn(Date timeStampCheckIn) {
        this.timeStampCheckIn = timeStampCheckIn;
    }

    public Date getTimeStampCheckOut() {
        return timeStampCheckOut;
    }

    public void setTimeStampCheckOut(Date timeStampCheckOut) {
        this.timeStampCheckOut = timeStampCheckOut;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {

        if(super.getUser() == null){
            return " ";
        }

        return "Transaction ID: \t" + super.getTransactionId() +
                "\n   Username: \t" + super.getUser().getUsername() +
                "\n   Order List: \t" + orderList +
                "\n   Lama Inap: \t" + duration +
                "\n   Total Price: \t" + totalPrice +
                "\n   Tanggal Booking : \t" + dateBooked +
                "\n   Time CheckIn: \t" + timeStampCheckIn +
                "\n   Time CheckOut: \t" + timeStampCheckOut + "\n";
    }
}