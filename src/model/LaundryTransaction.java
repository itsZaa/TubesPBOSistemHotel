package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class LaundryTransaction extends Transaction {
    private int roomNumber;
    private double totalPrice;
    private OrderStatus orderStatus;
    private ArrayList<LaundryOrder> orderList;
    private LocalDate dateOrder;
    private LocalDate dateDelivered;

    public LaundryTransaction() {
    }

    public LaundryTransaction(int transactionId, User user, int roomNumber, double totalPrice, OrderStatus orderStatus,
            ArrayList<LaundryOrder> orderList, LocalDate dateOrder, LocalDate dateDelivered) {
        super(transactionId, user);
        this.roomNumber = roomNumber;
        this.totalPrice = totalPrice;
        this.orderStatus = orderStatus;
        this.orderList = orderList;
        this.dateOrder = dateOrder;
        this.dateDelivered = dateDelivered;
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

    public ArrayList<LaundryOrder> getOrderList() {
        return orderList;
    }

    public void setorderList(ArrayList<LaundryOrder> orderList) {
        this.orderList = orderList;
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

    @Override
    public String toString() {
        return "{" +
                " roomNumber='" + getRoomNumber() + "'" +
                ", totalPrice='" + getTotalPrice() + "'" +
                ", orderStatus='" + getOrderStatus() + "'" +
                ", orderList='" + getOrderList() + "'" +
                ", dateOrder='" + getDateOrder() + "'" +
                ", dateDelivered='" + getDateDelivered() + "'" +
                "}";
    }
}