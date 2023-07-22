package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import controller.DatabaseController;
import controller.ReceptionistController;
import model.RoomTransaction;

public class CheckInView {
    private JFrame frame;

    public CheckInView() {
        initComponent();
    }

    private void initComponent() {
        frame = new GlobalView().frame();
        JLabel title = new GlobalView().labelHeader("Check-In");
        frame.add(title);

        JLabel labelID = new JLabel("Transaction ID:");
        JTextField fieldID = new JTextField();
        labelID.setBounds(10, 55, 120, 25);
        fieldID.setBounds(140, 55, 200, 25);
        frame.add(labelID);
        frame.add(fieldID);

        JButton checkInButton = new JButton("Check-In");
        checkInButton.setBounds(380, 420, 90, 25);
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ID = fieldID.getText();
                LocalDate date = new ReceptionistController().adjustDate();
                RoomTransaction ts = new DatabaseController().getRoomTransaction(ID, date);
                if (ts == null) {
                    new GlobalView().error("Transaction Not Found");
                } else {
                    ArrayList<Integer> room = new ReceptionistController().getNewRoom(ts);
                    new DatabaseController().updateCheckIn(ts.getTransactionId());
                    new GlobalView().notif(room.toString());
                }
                new ReceptionistView();
                frame.dispose();
            }
        });
        frame.add(checkInButton);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Run the GUI on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and show the CheckInView
                new CheckInView();
            }
        });
    }
}
