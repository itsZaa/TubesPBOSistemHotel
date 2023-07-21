package view;

import model.SingletonProfile;
import model.User;
import model.Transaction;

import controller.HistoryTransactionController;

import javax.swing.*;
import java.awt.*;

public class HistoryTransactionView {
    private HistoryTransactionController controller;
    private JPanel textAreaPanel;
    private User user;

    public HistoryTransactionView() {
        this.user = SingletonProfile.getInstance().getUser();
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
        JButton backButton = new JButton("Back");
        footerPanel.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
        });


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

    public static void main(String[] args) {
        new HistoryTransactionView();
    }
}
