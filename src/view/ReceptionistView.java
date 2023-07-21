package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


import controller.DatabaseController;
import controller.ReceptionistController;
import model.RoomTransaction;
import model.User;
import model.SingletonProfile;


public class ReceptionistView {
    private JFrame frame;
    private User user;

  
    public ReceptionistView() {
        this.user = SingletonProfile.getInstance().getUser();
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

        JButton refreshDay = new JButton("reset day");
        refreshDay.setBounds(175, 420, 150, 50);
        refreshDay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DatabaseController().resetDay();
                frame.dispose();
            }
        });
        frame.add(refreshDay);

        frame.setVisible(true);
    }

}