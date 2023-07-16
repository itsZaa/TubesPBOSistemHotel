package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LaundryTransaction extends Transaction {
    private int roomNumber;
    private double totalPrice;
    private OrderStatus orderStatus;
    private LocalDate dateOrder;
    private LocalDate dateDelivered;
    private Laundry laundry;
    private PaymentMethod paymentMethod;

    public LaundryTransaction() {
    }

    public LaundryTransaction(String transactionId, User user, int roomNumber, double totalPrice, OrderStatus orderStatus, LocalDate dateOrder, LocalDate dateDelivered, Laundry laundry, PaymentMethod paymentMethod) {
        super(transactionId, user);
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.dateOrder = dateOrder;
        this.dateDelivered = dateDelivered;
        this.laundry = laundry;
        this.paymentMethod = paymentMethod;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    public void setDateDelivered(LocalDate dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public Laundry getLaundry() {
        return laundry;
    }

    public void setLaundry(Laundry laundry) {
        this.laundry = laundry;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public ArrayList<Order> getOrderList() {
        return null;
    }

    @Override
    public String toString() {
        return "Transaction ID: \t" + super.getTransactionId() +
               "\n   Username: \t" + super.getUser().getUsername() +
               "\n   Room Number: \t" + roomNumber +
               "\n   Status: \t\t" + orderStatus +
               "\n   Total Price: \t" + totalPrice +
               "\n   Payment Method: \t" + paymentMethod.getName() +
               "\n   Date Order: \t" + dateOrder +
               "\n   Laundry Type: \t" + laundry.getLaundryName() + "\n";
    }
    
}