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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.UpdateStaffController;
import model.Staff;

public class UpdateStaffView {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private ArrayList<Staff> staffs;

    public UpdateStaffView() {
        staffs = new UpdateStaffController().getAllStaffs();
        initComponent();
    }

    private void initComponent() {
        frame = new JFrame("Aplikasi Sistem Hotel");
        frame.setSize(450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new GlobalView().labelHeader("List Staff");
        frame.add(title, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;
        for (Staff staff : staffs) {
            gbc.gridy = y;
            gbc.gridx = 0;
            JLabel username = new JLabel((y + 1) + ") " + staff.getUsername());
            mainPanel.add(username, gbc);

            gbc.gridx = 1;
            JLabel email = new JLabel(staff.getEmail());
            mainPanel.add(email, gbc);

            gbc.gridx = 2;
            JLabel type = new JLabel(staff.getType().toString());
            mainPanel.add(type, gbc);

            y++;
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        frame.add(scrollPane);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());

        gbc.gridy = 0;
        gbc.gridx = 2;
        JLabel inputLabel = new JLabel("Input username:");
        buttonPanel.add(inputLabel, gbc);

        gbc.gridx = 3;
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

        gbc.gridx = 3;
        JButton updateButton = new JButton("Update");
        buttonPanel.add(updateButton, gbc);

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
                new FormAddUpdateStaff(null);
                frame.dispose();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FormAddUpdateStaff(inputField.getText());
                frame.dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isDeleted = new UpdateStaffController().deleteStaff(inputField.getText());
                if (isDeleted) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Staff with username \"" + inputField.getText() + "\" successfully deleted.",
                            "Aplikasi Sistem Hotel",
                            JOptionPane.INFORMATION_MESSAGE);
                    new ManagerView();
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Staff with username \"" + inputField.getText() + "\" not found.",
                            "Aplikasi Sistem Hotel",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateStaffView();
    }
}