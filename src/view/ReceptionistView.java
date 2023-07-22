package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.DatabaseController;

public class ReceptionistView {
    private JFrame frame;

    public ReceptionistView() {
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
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckInView();
                frame.dispose();
            }
        });
        frame.add(checkInButton);

        JButton checkOutButton = new JButton("Check-Out");
        checkOutButton.setBounds(300, 350, 150, 50);
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CheckOutView();
                frame.dispose();
            }
        });
        frame.add(checkOutButton);

        JButton refreshDay = new JButton("Reset Day");
        refreshDay.setBounds(50, 420, 150, 50);
        refreshDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DatabaseController().resetDay();
                frame.dispose();
            }
        });
        frame.add(refreshDay);

        JButton logOutButton = new JButton("Log Out");
        logOutButton.setBounds(300, 420, 150, 50);
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomeScreen();
                frame.dispose();
            }
        });
        frame.add(logOutButton);

        frame.setVisible(true);
    }

}