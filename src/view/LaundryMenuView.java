package view;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import controller.DatabaseController;
import controller.LaundryController;

import model.GenderType;
import model.Laundry;
import model.Order;
import model.RoomTransaction;
import model.User;
import model.Customer;
import model.UserType;
import model.Transaction;

public class LaundryMenuView {
    private ArrayList<Laundry> menuList;
    private ArrayList<Order> orderList;
    private Customer customer;

    public LaundryMenuView(Customer customer) {
        menuList = new DatabaseController().getAllLaundry();
        orderList = new ArrayList<>();
        this.customer = customer;

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new GlobalView().labelHeader("Laundry List");
        panel.add(title);

        if (new LaundryController(customer).getLamaInap() <= 1) {
            JRadioButton buttonExpress = new JRadioButton("Express");
            buttonExpress.setBounds(10, 40, 100, 30);

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(buttonExpress);
            panel.add(buttonExpress);
        } else {
            JRadioButton buttonStandard = new JRadioButton("Standard");
            buttonStandard.setBounds(10, 40, 100, 30);

            JRadioButton buttonExpress = new JRadioButton("Express");
            buttonExpress.setBounds(115, 40, 100, 30);

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(buttonStandard);
            buttonGroup.add(buttonExpress);

            panel.add(buttonStandard);
            panel.add(buttonExpress);
        }

        

        JLabel nameLabel = new GlobalView().labelBody("Berat pakaian (kg): ", 10, 85, 200, 25);
        panel.add(nameLabel);

        JTextField input = new JTextField("0");
        input.setBounds(230, 85, 50, 25);
        panel.add(input);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, 470, 200);
        frame.add(scrollPane);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Cancel button clicked!");
            }
        });
        cancelButton.setBounds(300, 420, 90, 25);
        frame.add(cancelButton);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JOptionPane.showMessageDialog(null, "Pay button clicked!");
            }
        });
        payButton.setBounds(400, 420, 70, 25);
        frame.add(payButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // dummy customer
        // ceritanya suatu customer yg udh check in ingin pesan laundry.

        ArrayList transaction = new ArrayList<Transaction>();

        RoomTransaction roomTransaction = new RoomTransaction(0, null, 0, null, LocalDate.of(2023, 7, 4), LocalDate.of(2023, 7, 5), null, null, 0);

        transaction.add(roomTransaction);

        Customer ucup = new Customer("ucup123", "Ucuuuuup", "siPalingGanteng", GenderType.MALE, "08123",
                "ucup@gmail.com", UserType.CUSTOMER, null, transaction);

        new LaundryMenuView(ucup);
    }
}
