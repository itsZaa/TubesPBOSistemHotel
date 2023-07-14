package view;

import model.LaundryTransaction;
import model.User;
import model.Transaction;

import controller.HistoryTransactionController;
import controller.LaundryStaffController;
import controller.DatabaseController;

import javax.swing.*;
import java.awt.*;

public class HistoryTransactionView {
    private HistoryTransactionController controller;
    private JPanel textAreaPanel;

    public HistoryTransactionView(User user) {
        this.controller = new HistoryTransactionController(user);

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        JLabel title = new GlobalView().labelHeader("Riwayat Transaksi");
        title.setHorizontalAlignment(SwingConstants.CENTER); // Set the title alignment to center
        panel.add(title, BorderLayout.NORTH); // Add the title to the north region of the panel

        // Create a panel to hold the text areas
        this.textAreaPanel = new JPanel();
        this.textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        // Displaying items from the queue on the panel
        int transactionNumber = 1;

        for (Transaction transaction : controller.getListTransaction()) {
            String transactionInfo = transaction.toString();
            String indentedTransactionInfo = addIndentation(transactionInfo, "    ");

            JTextArea textArea = new JTextArea(transactionNumber + "." + indentedTransactionInfo);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            this.textAreaPanel.add(textArea);
            transactionNumber++;
        }

        // Create a scroll pane and add the text area panel to it
        JScrollPane scrollPane = new JScrollPane(textAreaPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center region of the panel

        JPanel footerPanel = new JPanel();
        JButton processButton = new JButton("Process");
        footerPanel.add(processButton);



        frame.setContentPane(panel);
        frame.add(footerPanel, BorderLayout.PAGE_END);

        frame.setVisible(true);
    }

    private String addIndentation(String input, String indentation) {
        StringBuilder indentedString = new StringBuilder();
        String[] lines = input.split("\n");
        for (String line : lines) {
            indentedString.append(indentation).append(line).append("\n");
        }
        return indentedString.toString();
    }

    // private void updateTransactionPanel() {
    //     // Clear the text area panel
    //     textAreaPanel.removeAll();

    //     // Displaying items from the updated queue on the panel
    //     int transactionNumber = 1;

    //     for (LaundryTransaction transaction : queueLaundryTransaction) {
    //         String transactionInfo = transaction.toString();
    //         String indentedTransactionInfo = addIndentation(transactionInfo, "    ");

    //         JTextArea textArea = new JTextArea(transactionNumber + "." + indentedTransactionInfo);
    //         textArea.setLineWrap(true);
    //         textArea.setWrapStyleWord(true);
    //         textArea.setEditable(false);
    //         textAreaPanel.add(textArea);
    //         transactionNumber++;
    //     }

    //     // Repaint the panel to reflect the changes
    //     textAreaPanel.revalidate();
    //     textAreaPanel.repaint();
    // }

    public static void main(String[] args) {
        new HistoryTransactionView(new DatabaseController().getUser("nico.js"));
    }
}
