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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import controller.UpdateStaffController;
import model.GenderType;
import model.Staff;
import model.StaffType;
import model.UserType;

public class FormAddUpdateStaff {
    private Staff staff;
    private String oldUsername;
    private GridBagConstraints gbc;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private JTextField usernameField;
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField passwordField;
    private JTextField addressField;
    private ButtonGroup genderGroup;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField phoneNumberField;
    private JTextField nikField;
    private JTextField salaryField;
    private JComboBox<String> staffTypeComboBox;

    public FormAddUpdateStaff(String username) {
        this.staff = new UpdateStaffController().getStaffByUsername(username);
        initComponent();
    }

    private void initComponent() {
        JFrame frame = new JFrame("Aplikasi Sistem Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Title
        JLabel title;
        if (staff == null) {
            title = new GlobalView().labelHeader("Add Staff");
            staff = new Staff();
        } else {
            title = new GlobalView().labelHeader("Update Staff");
        }
        title.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        frame.add(title, BorderLayout.NORTH);

        formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());

        gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 10);

        // Username
        createLabel("Username:", 0);
        getInputAttributes();
        this.oldUsername = staff.getUsername();
        usernameField = new JTextField(staff.getUsername());
        formPanel.add(usernameField, gbc);

        // Full name
        createLabel("Full Name:", 1);
        getInputAttributes();
        fullNameField = new JTextField(staff.getFullname());
        formPanel.add(fullNameField, gbc);

        // Email
        createLabel("Email:", 2);
        getInputAttributes();
        emailField = new JTextField(staff.getEmail());
        formPanel.add(emailField, gbc);

        // Password
        createLabel("Password:", 3);
        getInputAttributes();
        passwordField = new JTextField(staff.getPassword());
        formPanel.add(passwordField, gbc);

        // Address
        createLabel("Address:", 4);
        getInputAttributes();
        addressField = new JTextField(staff.getAddress());
        formPanel.add(addressField, gbc);

        // Gender
        createLabel("Gender:", 5);
        getInputAttributes();

        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");

        maleRadioButton.setActionCommand("MALE");
        femaleRadioButton.setActionCommand("FEMALE");

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        if (staff.getGender() != null) {
            if (staff.getGender().equals(GenderType.MALE)) {
                maleRadioButton.setSelected(true);
            }
            if (staff.getGender().equals(GenderType.FEMALE)) {
                femaleRadioButton.setSelected(true);
            }
        }

        JPanel genderPanel = new JPanel();
        genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        formPanel.add(genderPanel, gbc);

        // Phone number
        createLabel("Phone Number:", 6);
        getInputAttributes();
        phoneNumberField = new JTextField(staff.getPhoneNumber());
        formPanel.add(phoneNumberField, gbc);

        // NIK
        createLabel("NIK:", 7);
        getInputAttributes();
        nikField = new JTextField(staff.getNIK());
        formPanel.add(nikField, gbc);

        // Salary
        createLabel("Salary:", 8);
        getInputAttributes();
        salaryField = new JTextField(Double.toString(staff.getSalary()));
        formPanel.add(salaryField, gbc);

        // Staff type
        createLabel("Staff Type:", 9);
        getInputAttributes();

        String[] staffTypeOptions = { "RECEPTIONIST", "STAFF_FNB", "STAFF_LAUNDRY" };
        staffTypeComboBox = new JComboBox<>(staffTypeOptions);

        String defaultStaffType = (staff.getStaffType() != null) ? staff.getStaffType().name() : "";
        staffTypeComboBox.setSelectedItem(defaultStaffType);

        formPanel.add(staffTypeComboBox, gbc);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        frame.add(scrollPane);

        // Button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setPreferredSize(new Dimension(400, 40));

        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateStaffView();
                frame.dispose();
            }
        });

        gbc.gridx = 1;
        if (staff.getUsername() == null) {
            JButton addButton = new JButton("Add Staff");
            buttonPanel.add(addButton);

            addButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Staff s = createStaff();

                    if (validation(s)) {
                        boolean isInserted = new UpdateStaffController().insertNewStaff(s);
                        if (isInserted) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Insert staff success.",
                                    "Aplikasi Sistem Hotel",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new UpdateStaffView();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Insert staff failed.",
                                    "Aplikasi Sistem Hotel",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please complete all fields.",
                                "Aplikasi Sistem Hotel",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            });
        } else {
            JButton updateButton = new JButton("Update Staff");
            buttonPanel.add(updateButton);

            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Staff s = createStaff();

                    boolean isUpdated = new UpdateStaffController().updateStaff(s, oldUsername);
                    if (validation(s)) {
                        if (isUpdated) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Update staff success.",
                                    "Aplikasi Sistem Hotel",
                                    JOptionPane.INFORMATION_MESSAGE);
                            new UpdateStaffView();
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Update staff failed.",
                                    "Aplikasi Sistem Hotel",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Please complete all fields.",
                                "Aplikasi Sistem Hotel",
                                JOptionPane.WARNING_MESSAGE);
                    }

                }
            });
        }

        frame.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        frame.setVisible(true);
    }

    private Staff createStaff() {
        String genderCommand = genderGroup.getSelection() != null ? genderGroup.getSelection().getActionCommand() : null;

        Staff s = new Staff(0,
                nikField.getText(),
                Double.parseDouble(salaryField.getText()),
                StaffType.valueOf((String) staffTypeComboBox.getSelectedItem()),
                usernameField.getText(),
                fullNameField.getText(),
                passwordField.getText(),
                genderCommand != null ? GenderType.valueOf(genderCommand) : null,
                phoneNumberField.getText(),
                emailField.getText(),
                UserType.STAFF);

        return s;
    }

    private boolean validation(Staff staff) {
        if (staff.getUsername().isEmpty() || staff.getFullname().isEmpty() ||
                staff.getEmail().isEmpty() || staff.getPassword().isEmpty() ||
                staff.getAddress().isEmpty() || staff.getPhoneNumber().isEmpty() ||
                staff.getNIK().isEmpty() || staff.getSalary() <= 0 ||
                staff.getStaffType() == null) {
            return false;
        }
        return true;
    }

    private void createLabel(String text, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0.0;
        JLabel label = new JLabel(text);
        formPanel.add(label, gbc);
    }

    private void getInputAttributes() {
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
    }

    public static void main(String[] args) {
        new FormAddUpdateStaff(null);
    }
}