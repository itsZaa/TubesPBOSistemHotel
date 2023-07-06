package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import model.FnBMenu;
import model.FnBOrder;
import model.FnBTransaction;
import model.Order;
import model.OrderStatus;
import model.PaymentMethod;
import model.User;

public class FnBController {
    private DatabaseController dbController;

    public FnBController() {
        dbController = new DatabaseController();
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

    public void createFnBTransaction(User user, int roomNumber, PaymentMethod paymentMethod, ArrayList<Order> orderList) {
        ArrayList<FnBOrder> fnbOrders = castingOrder(orderList);

        String id = "FNB_";

        LocalDate currentTime = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("ddMMyyyyHHmmss");
        String formattedDate = currentTime.format(dateFormatter);
        
        id += formattedDate + "_";

        Random random = new Random();
        int randomInt = random.nextInt(100000);
        String randomString = String.format("%05d", randomInt);

        id += randomString;

        FnBTransaction transaction = new FnBTransaction(
                id,
                user,
                roomNumber,
                OrderStatus.WAITING,
                paymentMethod,
                fnbOrders,
                LocalDate.now(),
                countTotalPrice(fnbOrders));

        dbController.insertFnBTransaction(transaction);
    }
}