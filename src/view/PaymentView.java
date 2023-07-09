package view;

import controller.DatabaseController;
import controller.PaymentController;
import model.Order;
import model.PaymentMethod;
import model.RoomOrder;
import model.FnBTransaction;
import model.PaymentMethod;
import model.RoomTransaction;
import model.Transaction;
import observer.PaymentObserver;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PaymentView {
    private PaymentMethod payment;
    private boolean success = false;
    private PaymentObserver paymentObserver;

    public void setPaymentObserver(PaymentObserver paymentObserver) {
        this.paymentObserver = paymentObserver;
    }

    public boolean payment(Transaction transaction) {
        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JTextArea transaksiTextArea = new JTextArea();
        transaksiTextArea.setEditable(false);
        transaksiTextArea.setFont(new GlobalView().bodyFont());
        transaksiTextArea.setText(new PaymentController().getTransactionListAsString(transaction.getOrderList()));

        JScrollPane scrollPane = new JScrollPane(transaksiTextArea);
        scrollPane.setBounds(10, 10, 467, 250);
        panel.add(scrollPane);

        JLabel pilihanMetodeBayar = new JLabel("Choose payment method: ");
        pilihanMetodeBayar.setFont(new GlobalView().bodyFont());
        pilihanMetodeBayar.setBounds(10, 310, 300, 25);
        panel.add(pilihanMetodeBayar);

        ArrayList<PaymentMethod> paymentMethods = new DatabaseController().getAllPaymentMethod();
        ButtonGroup buttonGroup = new ButtonGroup();
        int x = 10;
        int defaultIndex = 0;

        for (int i = 0; i < paymentMethods.size(); i++) {
            PaymentMethod paymentMethod = paymentMethods.get(i);
            String name = paymentMethod.getName();
            JRadioButton radioButton = new JRadioButton(name);
            radioButton.setActionCommand(name);
            radioButton.setBounds(x, 340, 70, 25);
            panel.add(radioButton);
            buttonGroup.add(radioButton);

            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    payment = paymentMethod;
                }
            });

            if (i == defaultIndex) {
                radioButton.setSelected(true);
                payment = paymentMethod;
            }

            x += 75;
        }

        JLabel totalTransaksiLabel = new JLabel( "Total Transaction: Rp " + new PaymentController().countTotalTransaction(transaction.getOrderList()));

        totalTransaksiLabel.setFont(new GlobalView().bodyFontBold());
        totalTransaksiLabel.setBounds(10, 420, 300, 25);
        panel.add(totalTransaksiLabel);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(300, 420, 90, 25);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Payment Cancelled");
                frame.dispose();
            }
        });
        panel.add(cancelButton);

        JButton payButton = new JButton("Pay");
        payButton.setBounds(400, 420, 70, 25);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean proceed = showConfirmationDialog("Are you sure you want to proceed with the payment?");
                if (proceed) {
                    if (transaction instanceof RoomTransaction) {
                        RoomTransaction roomTransaction = (RoomTransaction) transaction;
                        roomTransaction.setPaymentMethod(payment);
                        success = new DatabaseController().insertRoomTransaction(roomTransaction);
                        if (success) {
                            // for (Order order : transaction.getOrderList()) {
                            //     RoomOrder roomOrder = (RoomOrder) order;
                            //     if (new DatabaseController().insertRoomOrder(transaction.getTransactionId(), roomOrder)) {
                            //     } else {
                            //         JOptionPane.showMessageDialog(null, "Something went wrong", null, JOptionPane.ERROR_MESSAGE);
                            //     }
                            // }
                        }
                    }
                    showNotification("Payment successful!", JOptionPane.INFORMATION_MESSAGE);
                    showNotification("Transaction completed!", JOptionPane.INFORMATION_MESSAGE);
                    if (paymentObserver != null) {
                        paymentObserver.onPaymentSuccess();
                    }
                } else {
                    showNotification("Payment canceled.", JOptionPane.WARNING_MESSAGE);
                }
                frame.dispose();
            }
        });
        panel.add(payButton);

        frame.setContentPane(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return success;
    }

    private boolean showConfirmationDialog(String message) {
        int confirmation = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return confirmation == JOptionPane.YES_OPTION;
    }

    private void showNotification(String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, "Notification", messageType);
    }
}
