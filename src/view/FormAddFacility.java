package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.UpdateFacilityController;
import model.HotelFacility;

public class FormAddFacility {
    private GridBagConstraints gbc;
    private JPanel formPanel;
    private JPanel buttonPanel;

    public FormAddFacility() {
        initComponent();
    }

    private void initComponent() {
        JFrame frame = new JFrame("Aplikasi Sistem Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Title
        JLabel title = new GlobalView().labelHeader("Add Hotel Facility");
        title.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        frame.add(title, BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Facility name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        JLabel facilityLabel = new JLabel("Facility Name:");
        formPanel.add(facilityLabel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        JTextField facilityField = new JTextField();
        formPanel.add(facilityField, gbc);

        frame.add(formPanel);

        // Button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setPreferredSize(new Dimension(400, 40));

        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateFacilityView();
                frame.dispose();
            }
        });

        JButton insertButton = new JButton("Insert");
        buttonPanel.add(insertButton);

        insertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelFacility facility = new HotelFacility();
                facility.setFacilityName(facilityField.getText());

                new UpdateFacilityController().insertNewFacility(facility);
                frame.dispose();
            }
        });

        frame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FormAddFacility();
    }
}