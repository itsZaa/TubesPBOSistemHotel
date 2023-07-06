package view;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
import model.Order;
import model.User;

import java.util.ArrayList;

public class FnBMenuView {
    private ArrayList<FnBMenu> menuList;
    private ArrayList<Order> orderList;
    private JTextField[] qtyFields;
    private JFrame frame;
    private User user;

    public FnBMenuView(User user) {
        this.user = user;
        menuList = new DatabaseController().getAllFnBMenu();
        orderList = new ArrayList<>();
        qtyFields = new JTextField[menuList.size()];

        if (LocalTime.now().isAfter(LocalTime.of(0, 0)) && LocalTime.now().isBefore(LocalTime.of(23, 59))) {
            frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setSize(500, 500);

            JPanel titlePanel = new JPanel();
            titlePanel.setLayout(null);
            titlePanel.setPreferredSize(new Dimension(frame.getWidth(), 50));

            JLabel title = new GlobalView().labelHeader("Food & Beverages");
            titlePanel.add(title);

            JPanel menuPanel = new JPanel();
            menuPanel.setLayout(null);

            int y = 10;
            int i = 0;
            for (FnBMenu menu : menuList) {
                JLabel nameLabel = new JLabel((i + 1) + ". " + menu.getMenuName());

                String price = String.format("%.0f", menu.getPrice());
                JLabel priceLabel = new JLabel("Rp." + price);

                JTextField input = new JTextField("0");

                nameLabel.setBounds(10, y, 120, 25);
                priceLabel.setBounds(200, y, 120, 25);
                input.setBounds(140, y, 50, 25);

                menuPanel.add(nameLabel);
                menuPanel.add(priceLabel);
                menuPanel.add(input);

                qtyFields[i] = input;
                y += 30;
                i++;
            }

            // Adjust the menuPanel size to accommodate all the components
            Dimension panelSize = new Dimension(300,y);
            menuPanel.setPreferredSize(panelSize);
            menuPanel.setMinimumSize(panelSize);

            JScrollPane scrollPane = new JScrollPane(menuPanel);
            scrollPane.setPreferredSize(new Dimension(200, 100));
            frame.add(scrollPane);

            // JRadioButton

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

            JButton cancelButton = new JButton("Cancel");
            // cancelButton.setBounds(300, 420, 90, 25);
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                }
            });
            buttonPanel.add(cancelButton);

            JButton orderButton = new JButton("Pay");
            // orderButton.setBounds(400, 420, 70, 25);
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create orders based on the quantity fields
                    for (int i = 0; i < menuList.size(); i++) {
                        int qty = Integer.parseInt(qtyFields[i].getText());
                        if (qty > 0) {
                            FnBMenu menu = menuList.get(i);
                            Order order = new Order(qty, menu.getPrice());
                            orderList.add(order);
                        }
                    }

                    // new FnBController().createFnBTransaction(user, orderList);
                }
            });
            buttonPanel.add(orderButton);

            JPanel contentPane = new JPanel(new BorderLayout());
            contentPane.add(titlePanel,BorderLayout.NORTH);
            contentPane.add(scrollPane,BorderLayout.CENTER);
            contentPane.add(buttonPanel,BorderLayout.SOUTH);

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
        User user = new DatabaseController().getUser("otong123");
        new FnBMenuView(user);
    }
}