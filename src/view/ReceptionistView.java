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

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and show the ReceptionistView
                new ReceptionistView();
            }
        });
    }

}