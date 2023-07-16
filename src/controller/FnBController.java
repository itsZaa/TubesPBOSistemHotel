package controller;

import java.time.LocalDate;
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
import model.User;

public class FnBController {
    private DatabaseController dbController;
    private User user;

    public FnBController(User user) {
        dbController = new DatabaseController();
        this.user = user;
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
        String id = "FNB_";

        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = currentTime.format(dateFormatter);

        id += formattedDate + "_";

        Random random = new Random();
        int randomInt = random.nextInt(100000);
        String randomString = String.format("%05d", randomInt);

        id += randomString;

        return id;
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