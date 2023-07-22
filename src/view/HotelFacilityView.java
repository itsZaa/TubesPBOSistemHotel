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

import controller.UpdateFacilityController;
import model.HotelFacility;

public class HotelFacilityView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private ArrayList<HotelFacility> facilities;

    public HotelFacilityView() {
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
        JLabel title = new GlobalView().labelHeader("View Hotel Facility:");
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
            facilityLabel.setFont(facilityLabel.getFont().deriveFont(14.0f));
            mainPanel.add(facilityLabel, gbc);
            y++;
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        gbc.gridy = 0;
        gbc.gridx = 0;
        JButton backButton = new JButton("Back");
        buttonPanel.add(backButton, gbc);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MainMenuCustomer();
                frame.dispose();
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HotelFacilityView();
    }
}