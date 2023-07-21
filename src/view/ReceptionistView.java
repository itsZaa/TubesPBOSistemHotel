package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Staff;
import model.SingletonProfile;

public class ReceptionistView {
    private JFrame frame;
    private Staff staff;

    public ReceptionistView() {
        this.staff = (Staff) SingletonProfile.getInstance().getUser();
        initComponent();
    }

    private void initComponent() {
        frame = new GlobalView().frame();

        JLabel label = new GlobalView().labelHeader("Receptionist Menu");
        frame.add(label);

        JLabel imageLabel = new JLabel();
        String imagePath = "/images/Hotel.jpeg";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(150, 58, 200, 260);
        frame.add(imageLabel);

        JButton checkInButton = new JButton("Check-In");
        checkInButton.setBounds(50, 350, 150, 50);
        checkInButton.addActionListener(e -> navigateToCheckInView());
        frame.add(checkInButton);

        JButton checkOutButton = new JButton("Check-Out");
        checkOutButton.setBounds(300, 350, 150, 50);
        checkOutButton.addActionListener(e -> navigateToCheckOutView());
        frame.add(checkOutButton);

        frame.setVisible(true);
    }

    private void navigateToCheckInView() {
        // CheckInView checkInView = new CheckInView();
        // checkInView.show();
    }

    private void navigateToCheckOutView() {
        // CheckOutView checkOutView = new CheckOutView();
        // checkOutView.show();
    }

    public static void main(String[] args) {

    }
}