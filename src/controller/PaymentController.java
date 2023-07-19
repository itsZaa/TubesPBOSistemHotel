package controller;

import java.util.ArrayList;

import model.FnBOrder;
import model.FnBTransaction;
import model.Order;
import model.PaymentMethod;
import model.RoomOrder;
import model.RoomTransaction;
import model.Transaction;

public class PaymentController {

    public double countTotalTransaction(ArrayList<Order> transaction) {
        double total = 0;
        for (Order order : transaction) {
            total += order.getOrderPrice();
        }
        return total;
    }

    public String getTransactionListAsString(ArrayList<Order> transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(" Transaction List :\n\n");

        for (Order order : transaction) {
            if (order instanceof RoomOrder) {
                RoomOrder roomOrder = (RoomOrder) order;
                sb.append(" ").append(roomOrder.getRoomType().getTypeName()).append(" x")
                        .append(roomOrder.getQuantity()).append(" : ")
                        .append(roomOrder.getOrderPrice()).append("\n");
            } else if (order instanceof FnBOrder) {
                FnBOrder fnbOrder = (FnBOrder) order;
                sb.append(" ").append(fnbOrder.getFood().getMenuName()).append(" x").append(fnbOrder.getQuantity())
                        .append(" : ").append(fnbOrder.getOrderPrice()).append("\n");
            }
        }

        return sb.toString();
    }

    public boolean insertRoomOrder(Transaction transaction, PaymentMethod payment) {
        RoomTransaction roomTransaction = (RoomTransaction) transaction;
        roomTransaction.setPaymentMethod(payment);
        boolean success = new DatabaseController().insertRoomTransaction(roomTransaction);
        for (Order order : transaction.getOrderList()) {
            RoomOrder roomOrder = (RoomOrder) order;
            success = new DatabaseController().insertRoomOrder(transaction.getTransactionId(),
                    roomOrder, roomOrder.getDate());
        }
        return success;
    }

    public boolean insertFnBOrder(Transaction transaction, PaymentMethod payment) {
        FnBTransaction fnbTransaction = (FnBTransaction) transaction;
        fnbTransaction.setPaymentMethod(payment);
        boolean success = new DatabaseController().insertFnBTransaction(fnbTransaction);
        for (Order order : transaction.getOrderList()) {
            FnBOrder fnbOrder = (FnBOrder) order;
            success = new DatabaseController().insertFnBOrder(transaction.getTransactionId(), fnbOrder);
        }
        return success;
    }
}