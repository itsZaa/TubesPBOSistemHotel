package view;

import controller.ProfileController;
import main.Main;
import model.*;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Profile {
    public Profile() {
        User user = SingletonProfile.getInstance().getUser();
        String oldUsername = user.getUsername();
        ProfileController controller = new ProfileController();
        JFrame frame = new JFrame("Profile");
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("Serif", Font.PLAIN, 20);

        //label judul
        JLabel judul = new JLabel("Profile");
        judul.setBounds(235, 5, 500, 60);
        judul.setFont(new Font("Serif", Font.BOLD, 35));

        //label nama
        JLabel namaLabel = new JLabel("Username");
        namaLabel.setBounds(100, 100, 90, 40);
        namaLabel.setFont(font1);

        //textfield nama
        JTextField tfNama = new JTextField();
        tfNama.setText(user.getUsername());
        tfNama.setEditable(false);
        tfNama.setBounds(240, 105, 240, 30);
        tfNama.setFont(font1);

        //label username
        JLabel userNameLabel = new JLabel("Full_Name");
        userNameLabel.setBounds(100, 140, 90, 40);
        userNameLabel.setFont(font1);

        //textfield username
        JTextField tfUserName = new JTextField();
        tfUserName.setText(user.getFullname());
        tfUserName.setEditable(false);
        tfUserName.setBounds(240, 145, 240, 30);
        tfUserName.setFont(font1);

        //label alamat
        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(100, 185, 90, 40);
        alamatLabel.setFont(font1);
        
        JTextField tfAlamat = new JTextField();
        tfAlamat.setText(user.getAddress());
        tfAlamat.setEditable(false);
        tfAlamat.setBounds(240, 195, 240, 30);
        tfAlamat.setFont(font1);

        //button edit
        JButton submit = new JButton("Edit");
        submit.setBounds(300, 510, 180, 50);
        submit.setFont(font1);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //button update
        JButton update = new JButton("Update");
        update.setBounds(300, 510, 180, 50);
        update.setFont(font1);
        update.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        //button back
        JButton backProfile = new JButton("Kembali");
        backProfile.setBounds(100, 510, 180, 50);
        backProfile.setFont(font1);
        backProfile.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton backUpdate = new JButton("Kembali");
        backUpdate.setBounds(100, 510, 180, 50);
        backUpdate.setFont(font1);
        backUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                tfNama.setEditable(true);
                tfUserName.setEditable(true);
                tfAlamat.setEditable(true);
                submit.setVisible(false);
                backProfile.setVisible(false);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                user.setFullname(tfNama.getText());
                user.setUsername(tfUserName.getText());
                user.setAddress(tfAlamat.getText());
                controller.updateUser(user, oldUsername);
                new GlobalView().notif("Update berhasil");
                frame.dispose();
                new Profile();
            }

        });
        backUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new Profile();
            }
        });
        backProfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new MainMenuCustomer();
            }
        });
        //button log out
        JButton logout = new JButton("Log Out");
        logout.setBounds(10,10,100,50);
        logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logout.setFont(new Font("Serif",Font.PLAIN,15));
        logout.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SingletonProfile.getInstance().reset();
                new WelcomeScreen();
            }
        });

        frame.add(judul);
        frame.add(namaLabel);
        frame.add(tfNama);
        frame.add(userNameLabel);
        frame.add(tfUserName);
        frame.add(alamatLabel);
        frame.add(tfAlamat);
        frame.add(submit);
        frame.add(backProfile);
        frame.add(update);
        frame.add(backUpdate);
        frame.add(logout);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}