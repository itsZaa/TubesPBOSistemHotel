package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PrintTransactionController;

public class PrintTransactionView {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<String> monthComboBox;
    private JTextField yearField;

    public PrintTransactionView() {
        initComponent();
    }

    private void initComponent() {
        frame = new JFrame("Aplikasi Sistem Hotel");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel label = new GlobalView().labelHeader("Print Transaction");
        panel.add(label, gbc);

        // Input month
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setFont(monthLabel.getFont().deriveFont(14.0f));
        panel.add(monthLabel, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December" };
        monthComboBox = new JComboBox<>(months);
        monthComboBox.setPreferredSize(new Dimension(monthComboBox.getPreferredSize().width, 24));
        panel.add(monthComboBox, gbc);

        // Input year
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setFont(yearLabel.getFont().deriveFont(14.0f));
        panel.add(yearLabel, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        yearField = new JTextField("2023");
        yearField.setPreferredSize(new Dimension(yearField.getPreferredSize().width, 24));
        panel.add(yearField, gbc);

        // Button back
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.NONE;
        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(80, 30));
        panel.add(backButton, gbc);

        // Button print
        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(80, 30));
        panel.add(submitButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ManagerView();
                frame.dispose();
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showTransaction();
            }
        });

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void showTransaction() {
        String selectedMonth = (String) monthComboBox.getSelectedItem();
        int selectedYear = Integer.parseInt(yearField.getText());

        double totalPrice = new PrintTransactionController().getAllTransactionsTotalPrice(selectedMonth, selectedYear);

        DecimalFormat decimalFormat = new DecimalFormat("#");
        String formattedPrice = decimalFormat.format(totalPrice);
        JOptionPane.showMessageDialog(null,
                "Total pendapatan pada bulan " + selectedMonth + " " + selectedYear + ": Rp." + formattedPrice);
    }

    public static void main(String[] args) {
        new PrintTransactionView();
    }
}