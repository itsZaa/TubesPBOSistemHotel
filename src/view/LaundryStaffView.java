package view;

import model.User;
import model.LaundryTransaction;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        panel.setLayout(null);

        JLabel title = new GlobalView().labelHeader("Laundry List");
        panel.add(title);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(10, 10, 470, 200);
        frame.add(scrollPane);

        
    }

}
