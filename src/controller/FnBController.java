package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import model.FnBMenu;
import model.FnBOrder;
import model.FnBTransaction;
import model.Order;
import model.OrderStatus;
import model.PaymentMethod;
import model.RoomTransaction;
import model.SingletonProfile;
import model.User;

public class FnBController {
    private DatabaseController dbController;
    private User user;

    public FnBController() {
        dbController = new DatabaseController();
        user = SingletonProfile.getInstance().getUser();
    }

    public String getFnBMenu() {
        ArrayList<FnBMenu> menuList = new DatabaseController().getAllFnBMenu();

        StringBuilder sb = new StringBuilder();
        sb.append("Food & Beverages List :\n\n");

        int c = 1;
        for (FnBMenu menu : menuList) {
            sb.append(c).append(". ").append(menu.getMenuName()).append(" : ").append(menu.getPrice());
            c++;
        }

        return sb.toString();
    }

    private ArrayList<FnBOrder> castingOrder(ArrayList<Order> orderList) {
        ArrayList<FnBOrder> fnbOrders = new ArrayList<>();

        for (Order order : orderList) {
            if (order instanceof FnBOrder) {
                FnBOrder fnbOrder = (FnBOrder) order;
                fnbOrders.add(fnbOrder);
            }
        }

        return fnbOrders;
    }

    private double countTotalPrice(ArrayList<FnBOrder> orderList) {
        double totalPrice = 0;

        for (Order order : orderList) {
            totalPrice += order.getOrderPrice() * order.getQuantity();
        }

        return totalPrice;
    }

    public String generateTransactionId() {
        LocalDateTime now = LocalDateTime.now();

        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("ddMMyyHHmmss"));

        Random random = new Random();
        int randomNumber = random.nextInt((int) Math.pow(10, 5));

        return "FNB_" + formattedDateTime + "_" + String.format("%0" + 5 + "d", randomNumber);
    }

    public void createFnBTransaction(int roomNumber, PaymentMethod paymentMethod, ArrayList<Order> orderList) {
        ArrayList<FnBOrder> fnbOrders = castingOrder(orderList);
        String id = generateTransactionId();

        FnBTransaction transaction = new FnBTransaction(
                id,
                user,
                roomNumber,
                OrderStatus.WAITING,
                paymentMethod,
                orderList,
                LocalDate.now(),
                countTotalPrice(fnbOrders));

        dbController.insertFnBTransaction(transaction);
    }

    private RoomTransaction findTransaction() {
        RoomTransaction selected = dbController.getRoomTransaction(user.getUsername());

        return selected;
    }

    public boolean isUserCheckIn() {
        RoomTransaction transaction = findTransaction();

        if (transaction.getDateCheckIn() != null) {
            return true;
        }

        return false;
    }

    public ArrayList<Integer> getUserRooms() {
        ArrayList<Integer> roomList = dbController.getRoomNumberOrdered(user.getUsername());

        return roomList;
    }
}