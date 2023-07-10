package view;

import model.User;
import model.LaundryTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Component;

import controller.DatabaseController;

public class LaundryStaffView {
    private User user;
    private Queue<LaundryTransaction> queueLaundryTransaction = new LinkedList<>();

    public LaundryStaffView(User user) {
        this.user = user;

        ArrayList<LaundryTransaction> listLaundryTransaction = new DatabaseController()
                .getUnprocessedLaundryTransactions();

        queueLaundryTransaction.addAll(listLaundryTransaction);

        JFrame frame = new GlobalView().frame();
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical layout

        JLabel title = new GlobalView().labelHeader("Unprocessed Laundry");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        // Displaying items from the queue on the panel
        int transactionNumber = 1;

        for (LaundryTransaction transaction : queueLaundryTransaction) {
            String transactionInfo = transaction.toString();
            String indentedTransactionInfo = addIndentation(transactionInfo, "    ");

            JTextArea textArea = new JTextArea(transactionNumber + "." + indentedTransactionInfo);
            textArea.setLineWrap(true); // Enable line wrapping
            textArea.setWrapStyleWord(true); // Wrap at word boundaries
            textArea.setEditable(false); // Make the text area read-only
            panel.add(textArea);
            transactionNumber++; // Increment transactionNumber
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, 470, 300);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);

        frame.setVisible(true);
    }

    // Method to add fixed indentation to each line of the string
    private String addIndentation(String input, String indentation) {
        StringBuilder indentedString = new StringBuilder();
        String[] lines = input.split("\n");
        for (String line : lines) {
            indentedString.append(indentation).append(line).append("\n");
        }
        return indentedString.toString();
    }

    public static void main(String[] args) {
        new LaundryStaffView(new DatabaseController().getUser("otong123"));
    }

}
