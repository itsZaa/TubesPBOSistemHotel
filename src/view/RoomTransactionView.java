package view;

import com.toedter.calendar.JDateChooser;

import model.GenderType;
import model.Order;
import model.RoomOrder;
import model.RoomTransaction;
import model.RoomType;
import model.User;
import model.UserType;

import controller.DatabaseController;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomTransactionView {

    private RoomTransaction transaction;
    private ArrayList<RoomType> roomType;

    public RoomTransactionView(User user) {
        this.transaction = new RoomTransaction(user);
        this.roomType = new DatabaseController().getRoom();
        initComponents();
    }

    public void initComponents() {
        JFrame frame = new GlobalView().frame();

        JLabel title = new GlobalView().labelHeader("Room Booking Form");
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

        JLabel label = new GlobalView().labelBody("input your order :", 10, 145, 400, 25);
        frame.add(label);

        int x = 175;
        ArrayList<JTextField> roomFields = new ArrayList<>();
        for (RoomType room : roomType) {
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
                LocalDate checkInDate = checkInDateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int stayDuration = Integer.parseInt(stayDurationField.getText());
                ArrayList<Order> orderList = new ArrayList<>();

                for (RoomType room : roomType) {
                    //get quantity room
                    int quantity = Integer.parseInt(roomFields.get(roomType.indexOf(room)).getText());
                    RoomOrder roomOrder = new RoomOrder(quantity, room, stayDuration);
                    orderList.add(roomOrder);
                }
                transaction.setOrderList(orderList);
                new PaymentView(transaction.getOrderList());

                //query here

                frame.dispose();
            }
        });

        frame.add(payButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        User user = new User("Username1", "fullName", "123", GenderType.MALE, "085xxxxxx", "email@gmail.com",
                UserType.CUSTOMER);

        new RoomTransactionView(user);
    }
}