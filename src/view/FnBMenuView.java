package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.DatabaseController;
import controller.FnBController;
import model.FnBMenu;
import model.Order;

import java.util.ArrayList;

public class FnBMenuView {
    private ArrayList<FnBMenu> menuList;
    private ArrayList<Order> orderList;

    public FnBMenuView() {
        menuList = new DatabaseController().getAllFnBMenu();
        orderList = new ArrayList<>();

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new GlobalView().labelHeader("Food & Beverages List");
        panel.add(title);

        int y = 85;
        for (FnBMenu menu : menuList) {
            JLabel nameLabel = new GlobalView().labelBody(menu.getMenuName(), 10, y, 120, 25);
            panel.add(nameLabel);

            JTextField input = new JTextField("0");
            input.setBounds(140, y, 50, 25);
            panel.add(input);

            String price = String.format("%.0f", menu.getPrice());
            JLabel priceLabel = new GlobalView().labelBody(price, 200, y, 120, 25);
            panel.add(priceLabel);

            // int qty = Integer.parseInt(input.getText());
            // Order order = new Order(qty, menu.getPrice());
            // orderList.add(order);

            y += 30;
        }

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

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 
                JOptionPane.showMessageDialog(null, "Pay button clicked!");
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FnBMenuView();
    }
}