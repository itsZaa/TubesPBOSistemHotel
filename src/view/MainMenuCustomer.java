package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import model.SingletonProfile;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Cursor;

public class MainMenuCustomer {
    public MainMenuCustomer() {
        Font font1 = new Font("Serif", Font.PLAIN, 20);
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
        JButton lihatFasilitas = new JButton("Lihat Fasilitas");

        pesanKamar.setFocusable(false);
        pesanFnB.setFocusable(false);
        pesanLaundry.setFocusable(false);
        historyTransaction.setFocusable(false);
        editProfile.setFocusable(false);
        lihatFasilitas.setFocusable(false);

        menuPanel.add(pesanKamar);
        menuPanel.add(pesanFnB);
        menuPanel.add(pesanLaundry);
        menuPanel.add(historyTransaction);
        menuPanel.add(editProfile);
        menuPanel.add(lihatFasilitas);

        contentPanel.add(headerPanel);
        contentPanel.add(menuPanel);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);

        pesanKamar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new RoomTransactionView();
            }
        });

        pesanFnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new FnBMenuView();
            }
        });

            
        pesanLaundry.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new LaundryMenuView();
            }
        });
        historyTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new HistoryTransactionView();
            }
        });
        editProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new Profile();
            }
        });
    }

    JButton SignUp = new JButton("Sign Up");

    // public static void main(String[] args) {
    //     new MainMenuCustomer();
    // }
}
