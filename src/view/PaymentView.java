package view;

import controller.PaymentController;
import model.Order;
import model.RoomOrder;
import model.RoomType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaymentView {
    private ArrayList<Order> transaction;

    public PaymentView(ArrayList<Order> transaction) {
        this.transaction = transaction;
        initComponents();
    }

    private void initComponents() {
        GlobalView globalView = new GlobalView();
        JFrame frame = globalView.frame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextArea transaksiTextArea = new JTextArea();
        transaksiTextArea.setEditable(false);
        transaksiTextArea.setFont(new GlobalView().bodyFont());
        transaksiTextArea.setText(new PaymentController().getTransactionListAsString(transaction));

        JScrollPane scrollPane = new JScrollPane(transaksiTextArea);
        scrollPane.setBounds(10, 10, 467, 400);
        panel.add(scrollPane);

        JLabel totalTransaksiLabel = 
        new JLabel("Total Transaction: Rp " + new PaymentController().countTotalTransaction(transaction));
        totalTransaksiLabel.setFont(new GlobalView().bodyFontBold());
        totalTransaksiLabel.setBounds(10, 420, 300, 25);
        panel.add(totalTransaksiLabel);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(300, 420, 90, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        panel.add(cancelButton);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(400, 420, 70, 25);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle pay button action here
                // Add your payment logic or method call

                frame.dispose();
            }
        });
        panel.add(payButton);

        frame.setContentPane(panel); // Set the panel as the content pane
        frame.setLocationRelativeTo(null); // Centers the frame on the screen
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ArrayList<Order> transaksi = new ArrayList<>();

        RoomType room1 = new RoomType(1, "Suite", 100000, 10);
        RoomType room2 = new RoomType(2, "kos-kosan", 60000, 10);

        Order order1 = new RoomOrder(2, room1);
        Order order2 = new RoomOrder(1, room2);
        Order order3 = new RoomOrder(5, room1);

        transaksi.add(order1);
        transaksi.add(order2);
        transaksi.add(order3);

        new PaymentView(transaksi);
    }
}