package view;

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

import java.time.LocalTime;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

import controller.LaundryController;
import model.Laundry;
import model.User;
import model.SingletonProfile;

public class LaundryMenuView {
    private LaundryController laundryController;
    private User user;
    private JFrame frame;

    // butuh utk insert laundry_transaction db
    private Laundry laundry;
    private double beratLaundry;
    private int roomNumber;

    public LaundryMenuView() {
        this.laundryController = new LaundryController(user);
        this.user = SingletonProfile.getInstance().getUser();
        this.laundry = new Laundry();

        if(new LaundryController(user).getLamaInap() == 0){
            frame = new GlobalView().frame();
            frame.dispose();
            new MainMenuCustomer();
        } else if (LocalTime.now().isAfter(LocalTime.of(6, 0)) && LocalTime.now().isBefore(LocalTime.of(18, 0))) {
            frame = new GlobalView().frame();

            JPanel panel = new JPanel();
            panel.setLayout(null);

            JLabel title = new GlobalView().labelHeader("Laundry List");
            panel.add(title);

            if (new LaundryController(user).getLamaInap() == 1) {
                JRadioButton buttonExpress = new JRadioButton("Express");
                buttonExpress.setBounds(10, 40, 100, 30);

                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(buttonExpress);
                panel.add(buttonExpress);

                this.laundry = this.laundryController.getLaundryType("Express");
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

                buttonExpress.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttonExpress.isSelected()) {
                            laundry = laundryController.getLaundryType("Express");
                        }
                    }
                });

                buttonStandard.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (buttonStandard.isSelected()) {
                            laundry = laundryController.getLaundryType("Standard");
                        }
                    }
                });
            }

            // input berat pakaian
            JLabel labelBerat = new GlobalView().labelBody("Berat pakaian (kg): ", 10, 85, 200, 25);
            panel.add(labelBerat);

            JTextField inputBerat = new JTextField("0");
            inputBerat.setBounds(230, 85, 50, 25);
            panel.add(inputBerat);

            inputBerat.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // Get the value as the user types in
                    String beratText = inputBerat.getText();
                    if (!beratText.isEmpty()) {
                        beratLaundry = Double.parseDouble(beratText);
                    }
                }
            });

            // input nomor kamar
            JLabel labelRoomNumber = new GlobalView().labelBody("Nomor Kamar: ", 10, 120, 200, 25);
            panel.add(labelRoomNumber);

            JTextField inputRoomNumber = new JTextField();
            inputRoomNumber.setBounds(230, 120, 50, 28);
            panel.add(inputRoomNumber);

            inputRoomNumber.addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    // Get the value as the user types in
                    String roomNumberText = inputRoomNumber.getText();
                    if (!roomNumberText.isEmpty()) {
                        roomNumber = Integer.parseInt(roomNumberText);
                    }
                }
            });

            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setBounds(10, 10, 470, 200);
            frame.add(scrollPane);

            // IF user cancel order
            JButton cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Pemesanan dibatalkan!");
                    new MainMenuCustomer();
                    frame.dispose();
                }
            });
            cancelButton.setBounds(300, 420, 90, 25);
            frame.add(cancelButton);

            // if user continue order
            JButton orderButton = new JButton("Order");
            orderButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    boolean paid = new LaundryPaymentView().pay(user, laundry, beratLaundry, roomNumber);

                    if (paid) {
                        frame.dispose();

                    }
                }
            });
            orderButton.setBounds(400, 420, 70, 25);
            frame.add(orderButton);

            frame.setVisible(true);
        } else {
            // Current time is outside the range
            JOptionPane.showMessageDialog(null, "Layanan Laundry sudah tutup. \nJam Buka: 06.00 - 18.00");
        }
    }

    // public static void main(String[] args) {
    // new LaundryMenuView();
    // }
}
