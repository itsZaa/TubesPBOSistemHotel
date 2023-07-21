package view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalTime;

import controller.DatabaseController;
import controller.FnBController;
import model.FnBMenu;
import model.FnBOrder;
import model.FnBTransaction;
import model.GenderType;
import model.Order;
import model.OrderStatus;
import model.SingletonProfile;
import model.User;
import model.UserType;
import observer.PaymentObserver;

import java.util.ArrayList;

public class FnBMenuView implements PaymentObserver {
    private JFrame frame;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private FnBTransaction transaction;
    private ArrayList<FnBMenu> menuList;
    private JTextField[] qtyFields;
    private ArrayList<Order> orderList;

    public FnBMenuView() {
        this.transaction = new FnBTransaction();
        this.menuList = new DatabaseController().getAllFnBMenu();
        this.qtyFields = new JTextField[menuList.size()];
        this.orderList = new ArrayList<>();
        initComponent();
    }

    public void initComponent() {
        // JAM OPERASIONAL 06:00 - 21:00
        if (LocalTime.now().isAfter(LocalTime.of(0, 0)) && LocalTime.now().isBefore(LocalTime.of(23, 59))) {
            frame = new JFrame("Aplikasi Sistem Hotel");
            frame.setSize(400, 400);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout(10, 10));

            JLabel title = new GlobalView().labelHeader("Food & Beverages");
            title.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            frame.add(title, BorderLayout.NORTH);

            formPanel = new JPanel();
            formPanel.setLayout(new GridBagLayout());

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 10, 5, 10);
            gbc.anchor = GridBagConstraints.WEST;

            int c = 0;
            for (FnBMenu menu : menuList) {
                gbc.gridy = c;
                gbc.gridx = 0;
                gbc.weightx = 1;
                JLabel menuLabel = new JLabel((c + 1) + ") " + menu.getMenuName());
                formPanel.add(menuLabel, gbc);

                gbc.gridx = 1;
                gbc.weightx = 0;
                String formattedPrice = "Rp" + String.format("%,.0f", menu.getPrice()).replace(",", ".");
                JLabel priceLabel = new JLabel(formattedPrice);
                formPanel.add(priceLabel, gbc);

                gbc.gridx = 2;
                qtyFields[c] = new JTextField("0");
                qtyFields[c].setPreferredSize(new Dimension(50, qtyFields[c].getPreferredSize().height));
                formPanel.add(qtyFields[c], gbc);

                c++;
            }

            JScrollPane scrollPane = new JScrollPane(formPanel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            frame.add(scrollPane);

            buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridBagLayout());
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.EAST;
            JRadioButton dineInButton = new JRadioButton("Dine-in");
            buttonPanel.add(dineInButton, gbc);

            gbc.gridx = 1;
            JRadioButton roomDeliveryButton = new JRadioButton("Room delivery");
            buttonPanel.add(roomDeliveryButton, gbc);

            ButtonGroup orderTypeGroup = new ButtonGroup();
            orderTypeGroup.add(dineInButton);
            orderTypeGroup.add(roomDeliveryButton);

            gbc.gridx = 2;
            JTextField roomDeliveryField = new JTextField();
            roomDeliveryField.setPreferredSize(new Dimension(50, roomDeliveryField.getPreferredSize().height));
            roomDeliveryField.setEnabled(false);
            buttonPanel.add(roomDeliveryField, gbc);

            roomDeliveryButton.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        roomDeliveryField.setEnabled(true);
                    } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                        roomDeliveryField.setEnabled(false);
                    }
                }
            });

            gbc.gridx = 1;
            gbc.gridy = 1;
            JButton cancelButton = new JButton("Cancel");
            buttonPanel.add(cancelButton, gbc);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new GlobalView().notif("Order cancelled.");

                    // back to customer menu
                }
            });

            gbc.gridx = 2;
            JButton orderButton = new JButton("Order");
            buttonPanel.add(orderButton, gbc);

            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double totalPrice = 0;
                    for (int i = 0; i < menuList.size(); i++) {
                        int qty = Integer.parseInt(qtyFields[i].getText());
                        if (qty > 0) {
                            FnBMenu menu = menuList.get(i);
                            FnBOrder order = new FnBOrder(qty, menu);
                            totalPrice += menu.getPrice() * qty;
                            orderList.add(order);
                        }
                    }

                    int roomNumber = 0; // default
                    if (roomDeliveryButton.isSelected()) {
                        roomNumber = Integer.parseInt(roomDeliveryField.getText());
                    }

                    transaction.setTransactionId(new FnBController().generateTransactionId());

                    // TODO
                    User user = new User("Username1", "fullName", "123", GenderType.MALE, "085xxxxxx", "email@gmail.com", UserType.CUSTOMER);
                    transaction.setUser(user);

                    // transaction.setUser(SingletonProfile.getInstance().getUser());
                    transaction.setRoomNumber(roomNumber);
                    transaction.setStatus(OrderStatus.WAITING);
                    transaction.setOrderList(orderList);
                    transaction.setTotalPrice(totalPrice);

                    PaymentView paymentView = new PaymentView();
                    paymentView.setPaymentObserver(FnBMenuView.this);

                    boolean succeed = paymentView.payment(transaction);
                    if (succeed) {
                        System.out.println("Success");
                    } else {
                        orderList = new ArrayList<>();
                    }

                    // back to customer menu
                }
            });
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        } else {
            new GlobalView().notif("Layanan FnB sudah tutup. \nJam Operasional: 06.00 - 21.00");
        }
    }

    @Override
    public void onPaymentSuccess() {
        frame.dispose();
    }

    public static void main(String[] args) {
        new FnBMenuView();
    }
}