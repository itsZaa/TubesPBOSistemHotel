package view;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import model.RoomOrder;
import model.RoomTransaction;
import model.RoomType;

import javax.swing.*;

import controller.DatabaseController;
import controller.RoomTransactionController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

public class RoomTransactionView {

    private ArrayList<RoomOrder> transaction;
    private ArrayList<RoomType> roomType;

    public RoomTransactionView(ArrayList<RoomOrder> transaction) {
        this.transaction = transaction;
        initComponents();
    }

    public void initComponents() {
        JFrame frame = new GlobalView().frame();
        roomType = new DatabaseController().getRoom();

        JLabel title = new GlobalView().labelHeader("Room Booking Form");
        frame.add(title);

        // Create labels and input fields for check-in date, stay duration, and room types
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
        for (RoomType room : roomType) {
            JLabel roomType1Label = new JLabel(room.getTypeName());
            JTextField roomType1Field = new JTextField("0");
            roomType1Label.setBounds(10, x, 120, 25);
            roomType1Field.setBounds(140, x, 200, 25);
            x += 30;
            frame.add(roomType1Label);
        frame.add(roomType1Field);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<RoomOrder> transaction = new ArrayList<>();

        RoomType room1 = new RoomType(1, "Suite", 100000, 10);
        RoomType room2 = new RoomType(2, "Normal", 60000, 10);

        RoomOrder order1 = new RoomOrder(2, room1);
        RoomOrder order2 = new RoomOrder(1, room2);
        RoomOrder order3 = new RoomOrder(5, room1);

        transaction.add(order1);
        transaction.add(order2);
        transaction.add(order3);

        SwingUtilities.invokeLater(() -> new RoomTransactionView(transaction));
    }
}