package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import controller.DatabaseController;
import controller.LaundryStaffController;
import model.User;
import model.LaundryTransaction;
import model.SingletonProfile;

public class LaundryStaffView {
    private User user;
    private Queue<LaundryTransaction> queueLaundryTransaction;
    private JPanel textAreaPanel;

    public LaundryStaffView() {
        this.user = SingletonProfile.getInstance().getUser();
        this.queueLaundryTransaction = new LinkedList<>(new LaundryStaffController().getUnprocessedLaundryTransaction());

        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout()); // Use BorderLayout for the main panel

        JLabel title = new GlobalView().labelHeader("Unprocessed Laundry");
        title.setHorizontalAlignment(SwingConstants.CENTER); // Set the title alignment to center
        panel.add(title, BorderLayout.NORTH); // Add the title to the north region of the panel

        // Create a panel to hold the text areas
        this.textAreaPanel = new JPanel();
        this.textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        // Displaying items from the queue on the panel
        int transactionNumber = 1;

        for (LaundryTransaction transaction : queueLaundryTransaction) {
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

         // Add ActionListener to the processButton
         processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!queueLaundryTransaction.isEmpty()) {
                    LaundryTransaction firstTransaction = queueLaundryTransaction.poll();
                    
                    boolean berhasil = new LaundryStaffController().updateLaundryTransaction(firstTransaction);

                    if(berhasil){
                        JOptionPane.showMessageDialog(null, "Pesanan " + firstTransaction.getTransactionId() + " berhasil diproses");
                    }else{
                        JOptionPane.showMessageDialog(null, "Pesanan " + firstTransaction.getTransactionId() + " gagal diproses");
                    }

                    // Remove the processed transaction from the queue
                    // The firstTransaction is already removed using poll() method
                    // You don't need to explicitly remove it from the queue

                    // Update the displayed transactions on the panel
                    updateTransactionPanel();
                } else {
                    JOptionPane.showMessageDialog(null, "Tidak ada transaksi untuk diproses");
                }
            }
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

    private void updateTransactionPanel() {
        // Clear the text area panel
        textAreaPanel.removeAll();

        // Displaying items from the updated queue on the panel
        int transactionNumber = 1;

        for (LaundryTransaction transaction : queueLaundryTransaction) {
            String transactionInfo = transaction.toString();
            String indentedTransactionInfo = addIndentation(transactionInfo, "    ");

            JTextArea textArea = new JTextArea(transactionNumber + "." + indentedTransactionInfo);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            textAreaPanel.add(textArea);
            transactionNumber++;
        }

        // Repaint the panel to reflect the changes
        textAreaPanel.revalidate();
        textAreaPanel.repaint();
    }

    // public static void main(String[] args) {
    //     User user = new DatabaseController().getUser("staff_laundry");
    //     new LaundryStaffView(user);
    // }
}
