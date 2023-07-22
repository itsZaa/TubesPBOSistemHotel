package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.UpdateFacilityController;
import model.HotelFacility;

public class UpdateFacilityView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private ArrayList<HotelFacility> facilities;

    public UpdateFacilityView() {
        facilities = new UpdateFacilityController().getAllFacilities();
        initComponent();
    }

    public void initComponent() {
        frame = new JFrame("Aplikasi Sistem Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new GlobalView().labelHeader("List Hotel Facility:");
        frame.add(title, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (HotelFacility facility : facilities) {
            gbc.gridy = y;
            gbc.gridx = 0;
            JLabel facilityLabel = new JLabel((y + 1) + ") " + facility.getFacilityName());
            mainPanel.add(facilityLabel, gbc);
            y++;
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        gbc.gridy = 0;
        gbc.gridx = 1;
        JLabel inputLabel = new JLabel("Input Facility Name:");
        buttonPanel.add(inputLabel, gbc);

        gbc.gridx = 2;
        JTextField inputField = new JTextField(10);
        buttonPanel.add(inputField, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton, gbc);

        gbc.gridx = 1;
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton, gbc);

        gbc.gridx = 2;
        JButton deleteButton = new JButton("Delete");
        buttonPanel.add(deleteButton, gbc);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerView();
                frame.dispose();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelFacility facility = new HotelFacility();
                facility.setFacilityName(inputField.getText());

                new UpdateFacilityController().insertNewFacility(facility);
                new ManagerView();
                new GlobalView().notif("Insert success");
                frame.dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDeleted = new UpdateFacilityController().deleteFacility(inputField.getText());
                if (isDeleted) {
                    new GlobalView().notif("Facility successfully deleted.");
                    new ManagerView();
                    frame.dispose();
                } else {
                    new GlobalView().error("Facility not found.");
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateFacilityView();
    }
}