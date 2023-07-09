package view;

import com.toedter.calendar.JDateChooser;

import controller.DatabaseController;
import controller.RoomTransactionController;
import model.GenderType;
import model.Order;
import model.RoomOrder;
import model.RoomTransaction;
import model.RoomType;
import model.User;
import model.UserType;
import observer.PaymentObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;

public class RoomTransactionView implements PaymentObserver {
    private JFrame frame;
    private RoomTransaction transaction;
    private ArrayList<RoomType> roomTypes;
    private boolean succeed;

    public RoomTransactionView(User user) {
        initComponents(user);
    }

    private void initComponents(User user) {
        frame = new GlobalView().frame();

        transaction = new RoomTransaction(user);
        roomTypes = new DatabaseController().getRoom();
        JLabel title = new JLabel("Room Booking Form");
        frame.add(title);

        JLabel checkInLabel = new JLabel("Check-in Date:");
        JDateChooser checkInDateChooser = new JDateChooser();
        checkInDateChooser.setCalendar(Calendar.getInstance());
        checkInLabel.setBounds(10, 55, 120, 25);
        checkInDateChooser.setBounds(140, 55, 200, 25);
        frame.add(checkInLabel);
        frame.add(checkInDateChooser);

        JLabel stayDurationLabel = new JLabel("Stay Duration:");
        JTextField stayDurationField = new JTextField("0");
        stayDurationLabel.setBounds(10, 85, 120, 25);
        stayDurationField.setBounds(140, 85, 200, 25);
        frame.add(stayDurationLabel);
        frame.add(stayDurationField);

        JLabel label = new JLabel("Input your order :");
        label.setBounds(10, 145, 400, 25);
        frame.add(label);

        int x = 175;
        ArrayList<JTextField> roomFields = new ArrayList<>();
        for (RoomType room : roomTypes) {
            JLabel roomLabel = new JLabel(room.getTypeName());
            JTextField roomField = new JTextField("0");
            roomLabel.setBounds(10, x, 120, 25);
            roomField.setBounds(140, x, 200, 25);
            x += 30;
            frame.add(roomLabel);
            frame.add(roomField);
            roomFields.add(roomField);
        }

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(300, 420, 90, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Transaction cancelled");
                frame.dispose();
            }
        });
        frame.add(cancelButton);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(400, 420, 70, 25);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean available = true;
                String transactionId = new RoomTransactionController().generateTransactionID();
                LocalDate checkInDate = checkInDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();

                int stayDuration = Integer.parseInt(stayDurationField.getText());
                ArrayList<Order> orderList = new ArrayList<>();

                for (RoomType room : roomTypes) {
                    int quantity = Integer.parseInt(roomFields.get(roomTypes.indexOf(room)).getText());
                    // available = new RoomTransactionController().checkRoom(checkInDate,
                    // stayDuration, room, quantity);
                    if (available) {
                        RoomOrder roomOrder = new RoomOrder(quantity, room, stayDuration);
                        orderList.add(roomOrder);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Sorry, the room " + room.getTypeName() + " is full");
                    }
                }

                // if (available) {
                // boolean paid = new
                transaction.setOrderList(orderList);
                PaymentView paymentView = new PaymentView();
                paymentView.setPaymentObserver(RoomTransactionView.this); // Set the observer
                succeed = paymentView.payment(transaction);

                if (succeed) {
                    frame.dispose();
                }
            }
        });

        frame.add(payButton);

        frame.setVisible(true);
    }

    public void handlePaymentSuccess() {
        frame.dispose();
    }

    @Override
    public void onPaymentSuccess() {
        handlePaymentSuccess();
    }

    public static void main(String[] args) {
        User user = new User("Username1", "fullName", "123", GenderType.MALE, "085xxxxxx", "email@gmail.com",
                UserType.CUSTOMER);

        new RoomTransactionView(user);
    }
}
