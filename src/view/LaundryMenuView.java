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

import controller.DatabaseController;
import model.Laundry;
import model.Order;

public class LaundryMenuView {
    private ArrayList<Laundry> menuList;
    private ArrayList<Order> orderList;

    public LaundryMenuView() {
        menuList = new DatabaseController().getAllLaundry();
        orderList = new ArrayList<>();

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new GlobalView().labelHeader("Laundry List");
        panel.add(title);

        JRadioButton buttonStandard = new JRadioButton("Standard");
        buttonStandard.setBounds(10, 40, 100, 30);

        JRadioButton buttonExpress = new JRadioButton("Express");
        buttonExpress.setBounds(115, 40, 100, 30);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(buttonStandard);
        buttonGroup.add(buttonExpress);

        panel.add(buttonStandard);
        panel.add(buttonExpress);

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
        new LaundryMenuView();
    }
}
