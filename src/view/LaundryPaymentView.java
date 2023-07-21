package view;

import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.User;
import model.Laundry;
import model.PaymentMethod;

import controller.LaundryController;
import controller.DatabaseController;

public class LaundryPaymentView {
    // private String selectedPaymentMethod;
    private PaymentMethod selectedPaymentMethod;
    private boolean isPay;

    public LaundryPaymentView() {
        this.selectedPaymentMethod = null;
        this.isPay = false;
    }

    private void setIsPayToTrue() {
        this.isPay = true;
    }

    public boolean pay(User user, Laundry laundry, double beratLaundry, int roomNumber) {

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        JLabel title = new GlobalView().labelHeader("Pembayaran Laundry");
        panel.add(title);

        JLabel totalPrice = new GlobalView().labelBody(
                "Total harga: " + new LaundryController().countTotalPrice(laundry, beratLaundry), 10, 100, 200, 30);
        panel.add(totalPrice);

        JLabel pilihanMetodeBayar = new GlobalView().labelBody("Pilih metode pembayaran: ", 10, 130, 200, 30);
        panel.add(pilihanMetodeBayar);

        ArrayList<PaymentMethod> paymentMethods = new DatabaseController().getAllPaymentMethod();

        ButtonGroup buttonGroup = new ButtonGroup();

        for (PaymentMethod paymentMethod : paymentMethods) {

            String name = paymentMethod.getName();
            JRadioButton radioButton = new JRadioButton(name);
            radioButton.setActionCommand(name); // Set the action command to the payment method name
            panel.add(radioButton);
            buttonGroup.add(radioButton);

            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectedPaymentMethod = paymentMethod;
                }
            });
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, 470, 200);
        frame.add(scrollPane);

        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedPaymentMethod != null) {

                    // Display a confirmation dialog with Yes/No options
                    int result = JOptionPane.showConfirmDialog(null,
                            "Sudah yakin dengan pesanan Anda dan ingin melakukan pembayaran?",
                            "Confirmation", JOptionPane.YES_NO_OPTION);

                    // Check the user's choice
                    if (result == JOptionPane.YES_OPTION) {
                        // create object transaction
                        new LaundryController().createLaundryTransaction(user, beratLaundry, roomNumber, laundry,
                                selectedPaymentMethod);

                        // System.out.println("INI MASUK");
                        setIsPayToTrue();
                        frame.dispose(); // ini nanti balik ke menu utama!!!
                    } else {
                        frame.dispose(); // ini balik ke formulir pemesanan laundry!!!
                    }

                } else {
                    // No payment method selected
                    JOptionPane.showMessageDialog(frame, "Please select a payment method.", "Payment Method",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        panel.add(payButton);
        frame.setVisible(true);

        System.out.println(isPay);
        return isPay;
    }
}
