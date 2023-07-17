package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import controller.DatabaseController;
import controller.FnBController;
import model.FnBMenu;
import model.FnBOrder;
import model.FnBTransaction;
import model.Order;
import model.User;

import java.util.ArrayList;

public class FnBMenuView {
    private User user;
    private FnBTransaction transaction;
    private ArrayList<FnBMenu> menuList;
    private ArrayList<Order> orderList;
    private JTextField[] qtyFields;

    public FnBMenuView(User user) {
        this.user = user;
        transaction = new FnBTransaction();
        menuList = new DatabaseController().getAllFnBMenu();
        orderList = new ArrayList<>();
        qtyFields = new JTextField[menuList.size()];

        if (LocalTime.now().isAfter(LocalTime.of(0, 0)) && LocalTime.now().isBefore(LocalTime.of(23, 59))) {
            JFrame frame = new JFrame();
            frame.setResizable(false);
            frame.setSize(500, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

            JLabel title = new GlobalView().labelHeader("Food & Beverages");
            titlePanel.add(title);

            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(null);

            int y = 10;
            int c = 0;
            for (FnBMenu menu : menuList) {
                JLabel nameLabel = new JLabel((c + 1) + ". " + menu.getMenuName());

                String price = String.format("%.0f", menu.getPrice());
                JLabel priceLabel = new JLabel("Rp." + price);

                JTextField input = new JTextField("0");

                nameLabel.setBounds(10, y, 220, 25);
                priceLabel.setBounds(240, y, 100, 25);
                input.setBounds(350, y, 50, 25);

                menuPanel.add(nameLabel);
                menuPanel.add(priceLabel);
                menuPanel.add(input);

                qtyFields[c] = input;
                y += 30;
                c++;
            }

            // Adjust the menuPanel size to accommodate all the components
            Dimension panelSize = new Dimension(300, y);
            menuPanel.setPreferredSize(panelSize);
            menuPanel.setMinimumSize(panelSize);

            JScrollPane scrollPane = new JScrollPane(menuPanel);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            frame.add(scrollPane);

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

            ButtonGroup buttonGroup = new ButtonGroup();

            JRadioButton dineIn = new JRadioButton("Dine-in");
            dineIn.setSelected(true); // default
            buttonGroup.add(dineIn);
            buttonPanel.add(dineIn);

            JRadioButton roomDelivery = new JRadioButton("Room delivery");
            buttonGroup.add(roomDelivery);
            buttonPanel.add(roomDelivery);

            ArrayList<Integer> optionList = new FnBController(user).getUserRooms();
            String[] options = new String[optionList.size()];

            for (int i = 0; i < optionList.size(); i++) {
                options[i] = String.valueOf(optionList.get(i));
            }

            JComboBox<String> dropdown = new JComboBox<>(options);
            buttonPanel.add(dropdown);
            dropdown.setEnabled(false);

            int roomNumber = 0; // default dine-in

            dineIn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dropdown.setEnabled(!dineIn.isSelected());
                }
            });

            roomDelivery.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dropdown.setEnabled(roomDelivery.isSelected());
                }
            });

            if (new FnBController(user).isUserCheckIn() == false) {
                roomDelivery.setEnabled(false);
            }

            JButton cancelButton = new JButton("Cancel");
            // cancelButton.setBounds(300, 420, 90, 25);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Transaction cancelled");
                    frame.dispose();
                }
            });
            buttonPanel.add(cancelButton);

            JButton orderButton = new JButton("Order");
            // orderButton.setBounds(400, 420, 70, 25);
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create orders based on the quantity fields
                    for (int i = 0; i < menuList.size(); i++) {
                        int qty = Integer.parseInt(qtyFields[i].getText());
                        if (qty > 0) {
                            FnBMenu menu = menuList.get(i);
                            FnBOrder order = new FnBOrder(qty, menu);
                            orderList.add(order);
                        }
                    }

                    transaction.setOrderList(orderList);
                    new PaymentView(transaction.getOrderList());

                    frame.dispose();
                }
            });
            buttonPanel.add(orderButton);

            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(titlePanel, BorderLayout.NORTH);
            contentPane.add(scrollPane, BorderLayout.CENTER);
            contentPane.add(buttonPanel, BorderLayout.SOUTH);

            frame.setContentPane(contentPane);
            frame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Layanan FnB sudah tutup. \nJam Buka: 06.00 - 21.00",
                    "Aplikasi Sistem Hotel",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        User userDummy = new DatabaseController().getUser("nico.js");
        new FnBMenuView(userDummy);
    }
}