package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ManagerView {
    private JFrame frame;
    private JPanel panel;

    public ManagerView() {
        initComponent();
    }

    private void initComponent() {
        frame = new GlobalView().frame();
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Title
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new GlobalView().labelHeader("Manager Menu");
        panel.add(label, gbc);

        // Hotel image
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel imageLabel = new JLabel();
        String imagePath = "/images/Hotel.jpeg";
        ImageIcon imageIcon = new ImageIcon(getClass().getResource(imagePath));
        imageLabel.setIcon(imageIcon);
        panel.add(imageLabel, gbc);

        // Lihat pendapatan & transaksi
        gbc.gridwidth = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton printTransactionButton = new JButton("Print Transaction");
        Dimension buttonSize = new Dimension(printTransactionButton.getPreferredSize().width, 60);
        printTransactionButton.setPreferredSize(buttonSize);
        panel.add(printTransactionButton, gbc);

        // Update fasilitas
        gbc.gridx = 1;
        JButton updateFacilityButton = new JButton("Update Facility");
        updateFacilityButton.setPreferredSize(buttonSize);
        panel.add(updateFacilityButton, gbc);

        // Update staff
        gbc.gridx = 0;
        gbc.gridy = 3;
        JButton updateStaffButton = new JButton("Update Staff");
        updateStaffButton.setPreferredSize(buttonSize);
        panel.add(updateStaffButton, gbc);

        // Log out
        gbc.gridx = 1;
        JButton logOutButton = new JButton("Log Out");
        logOutButton.setPreferredSize(buttonSize);
        panel.add(logOutButton, gbc);

        printTransactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PrintTransactionView();
            }
        });

        updateFacilityButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        updateStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStaffView();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        frame.add(panel, BorderLayout.NORTH);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new ManagerView();
    }
}