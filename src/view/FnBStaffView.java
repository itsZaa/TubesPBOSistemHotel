package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import controller.FnBStaffController;
import model.FnBTransaction;

public class FnBStaffView {
    private Queue<FnBTransaction> queue;
    private JPanel textAreaPanel;

    public FnBStaffView() {
        this.queue = new LinkedList<>(new FnBStaffController().getUnprocessedFnBTransactions());
        initComponent();
    }

    private void initComponent() {
        JFrame frame = new GlobalView().frame();

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel title = new GlobalView().labelHeader("Unprocessed FnB");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title, BorderLayout.NORTH);

        textAreaPanel = new JPanel();
        textAreaPanel.setLayout(new BoxLayout(textAreaPanel, BoxLayout.Y_AXIS));

        int transactionNumber = 1;
        for (FnBTransaction transaction : queue) {
            String transactionInfo = transaction.toString();
            String indentedTransactionInfo = addIndentation(transactionInfo, "    ");

            JTextArea textArea = new JTextArea(transactionNumber + "." + indentedTransactionInfo);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setEditable(false);
            this.textAreaPanel.add(textArea);
            transactionNumber++;
        }

        JScrollPane scrollPane = new JScrollPane(textAreaPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel();
        JButton logOutButton = new JButton("Log out");
        footerPanel.add(logOutButton);

        JButton processButton = new JButton("Process");
        footerPanel.add(processButton);

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new WelcomeScreen();
            }
        });

        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!queue.isEmpty()) {
                    FnBTransaction firstTransaction = queue.poll();

                    boolean isSucceed = new FnBStaffController().updateFnBTransaction(firstTransaction);

                    if (isSucceed) {
                        new GlobalView().notif(firstTransaction.getTransactionId() + " successfully processed.");
                    } else {
                        new GlobalView().error(firstTransaction.getTransactionId() + " failed to process.");
                    }

                    updateTransactionPanel();
                } else {
                    new GlobalView().warning("There are no transactions to process");
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
        textAreaPanel.removeAll();

        int transactionNumber = 1;

        for (FnBTransaction transaction : queue) {
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

    public static void main(String[] args) {
        new FnBStaffView();
    }
}