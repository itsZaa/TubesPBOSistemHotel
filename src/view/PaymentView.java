package view;

import controller.PaymentController;
import model.Order;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JLabel totalTransaksiLabel = new JLabel(
                "Total Transaction: Rp " + new PaymentController().countTotalTransaction(transaction));
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
                boolean proceed = showConfirmationDialog("Are you sure you want to proceed the payment?");
                if (proceed) {
                    showNotification("Payment successful!", JOptionPane.INFORMATION_MESSAGE);

                    // add query

                    showNotification("Transaction completed!", JOptionPane.INFORMATION_MESSAGE);
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
    }

    private boolean showConfirmationDialog(String message) {
        int confirmation = JOptionPane.showConfirmDialog(null, message, "Confirmation", JOptionPane.YES_NO_OPTION);
        return confirmation == JOptionPane.YES_OPTION;
    }

    private void showNotification(String message, int messageType) {
        JOptionPane.showMessageDialog(null, message, "Notification", messageType);
    }

}