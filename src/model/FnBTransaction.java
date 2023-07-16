package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class FnBTransaction extends Transaction{
    private int roomNumber;
    private OrderStatus status;
    private PaymentMethod paymentMethod;
    private ArrayList<Order> orderList;
    private LocalDate transactionDate;
    private double totalPrice;

    public FnBTransaction() {
    }

    public FnBTransaction(String transactionId, User user, int roomNumber, OrderStatus status, PaymentMethod paymentMethod,
            ArrayList<Order> orderList, LocalDate transactionDate, double totalPrice) {
        super(transactionId, user);
        this.roomNumber = roomNumber;
        this.status = status;
        this.orderList = orderList;
        this.transactionDate = transactionDate;
        this.totalPrice = totalPrice;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return this.paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public ArrayList<Order> getOrderList() {
        return this.orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public LocalDate getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {

        return "Transaction ID: \t" + super.getTransactionId() +
                "\n   Username: \t" + super.getUser().getUsername() +
                "\n   Room Number: \t" + roomNumber +
                "\n   Status: \t\t" + status +
                "\n   Total Price: \t" + totalPrice +
                "\n   Payment Method: \t" + getPaymentMethod() +
                "\n   Transaction Date: \t" + getTransactionDate() +
                "\n   Order List: \t" + getOrderList() + "\n";
    }
}