package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainMenuCustomer {
    public MainMenuCustomer(){
        JFrame frame = new GlobalView().frame();
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Set padding here


        JPanel headerPanel = new JPanel();
        headerPanel.setSize(500, 80);

        JLabel title = new GlobalView().labelHeader("Main Menu Customer");
        headerPanel.add(title);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 2, 20, 20));

        JButton pesanKamar = new JButton("Pesan kamar");
        JButton pesanFnB = new JButton("Pesan FnB");
        JButton pesanLaundry = new JButton("Pesan Laundry");
        JButton historyTransaction = new JButton("Riwayat Transaksi");
        JButton editProfile = new JButton("Edit Profile");

        pesanKamar.setFocusable(false);
        pesanFnB.setFocusable(false);
        pesanLaundry.setFocusable(false);
        historyTransaction.setFocusable(false);
        editProfile.setFocusable(false);

        menuPanel.add(pesanKamar);
        menuPanel.add(pesanFnB);
        menuPanel.add(pesanLaundry);
        menuPanel.add(historyTransaction);
        menuPanel.add(editProfile);

        contentPanel.add(headerPanel);
        contentPanel.add(menuPanel);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainMenuCustomer();
    }
}
