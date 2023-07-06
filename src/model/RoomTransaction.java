package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class RoomTransaction extends Transaction {
    private Date dateCheckIn;
    private int duration;
    private TransactionStatus status;
    private ArrayList<Order> orderList;

    private Date dateBooked;
    private Date timeStampCheckIn;
    private Date timeStampCheckOut;

    private PaymentMethod paymentMethod;
    private double totalPrice;

    public RoomTransaction() {
    }
  
    public RoomTransaction(User user) {
        super("",user);
    }

    public Date getDateBooked() {
        return dateBooked;
    }

    public void setDateBooked(Date dateBooked) {
        this.dateBooked = dateBooked;
    }


    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

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

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(Date dateCheckIn) {
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

    @Override
    public String toString() {
        return "RoomTransaction [ dateCheckIn=" + dateCheckIn
                + ", duration=" + duration + ", orderList=" + orderList + ", paymentMethod=" + paymentMethod
                + ", totalPrice=" + totalPrice + ", dateBooked=" + dateBooked + ", timeStampCheckIn=" + timeStampCheckIn
                + ", timeStampCheckOut=" + timeStampCheckOut + "]";
    }

    
}