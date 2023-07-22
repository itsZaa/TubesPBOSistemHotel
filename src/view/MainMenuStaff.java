package view;

import model.SingletonProfile;
import model.User;
import model.UserType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuStaff {
    private User user;

    public MainMenuStaff() {
        this.user = SingletonProfile.getInstance().getUser();

        JFrame frame = new GlobalView().frame();
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(3, 1));
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Set padding here

        JPanel headerPanel = new JPanel();
        headerPanel.setSize(500, 80);

        JLabel title = new GlobalView().labelHeader("Main Menu Staff");
        headerPanel.add(title);

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(2, 2, 20, 20));

        JButton button1 = null;
        // JButton button2 = null;
        // JButton button3 = null;

        contentPanel.add(headerPanel);
        contentPanel.add(menuPanel);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);

        if (user.getType() == UserType.RECEPTIONIST) {
            frame.dispose();
            new ReceptionistView();
        } else if (user.getType() == UserType.STAFF_FNB) {
            button1 = new JButton("Proses Pesanan F&B");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                    new FnBStaffView();
                }
            });
        } else if (user.getType() == UserType.STAFF_LAUNDRY) {
            button1 = new JButton("Proses Pesanan Laundry");
            button1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    frame.dispose();
                    new LaundryStaffView();
                }
            });
        } else if (user.getType() == UserType.MANAGER) {
            frame.dispose();
            new ManagerView();
        }

        if (button1 != null) {
            button1.setFocusable(false);
            menuPanel.add(button1);
        }

        // if (button2 != null) {
        //     button2.setFocusable(false);
        //     menuPanel.add(button2);
        // }

        // if (button3 != null) {
        //     button3.setFocusable(false);
        //     menuPanel.add(button3);
        // }
    }
}