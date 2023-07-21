package view;

import controller.LogOutController;
import controller.ProfileController;
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
        User user = new User();
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

        //label telepon
        JLabel teleponLabel = new JLabel("Telepon");
        teleponLabel.setBounds(100, 220, 90, 40);
        teleponLabel.setFont(font1);

        //textfield telepon
        JTextField tfTelepon = new JTextField();
        tfTelepon.setText(customer.getTelepon());
        tfTelepon.setEditable(false);
        tfTelepon.setBounds(240, 225, 240, 30);
        tfTelepon.setFont(font1);

        //label alamat
        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(100, 260, 90, 40);
        alamatLabel.setFont(font1);

        //textfield alamat
        JTextField alamat = new JTextField();
        alamat.setText(user.getAlamat().get(index).getAlamatLengkap());
        alamat.setEditable(false);
        alamat.setBounds(240, 265, 240, 30);
        alamat.setFont(font1);

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
        
        //button lihat riwayat pembelian
        JButton riwayatPembelian = new JButton("Lihat Riwayat");
        riwayatPembelian.setBounds(300, 570, 180, 50);
        riwayatPembelian.setFont(font1);
        riwayatPembelian.setCursor(new Cursor(Cursor.HAND_CURSOR));
        riwayatPembelian.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new RiwayatPembelian();
            }
        });
        
        
        //button lihat status pembelian
        JButton statusPembelian = new JButton("Lihat Status");
        statusPembelian.setBounds(100, 570, 180, 50);
        statusPembelian.setFont(font1);
        statusPembelian.setCursor(new Cursor(Cursor.HAND_CURSOR));
        statusPembelian.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new StatusPembelian();
            }
        });
        
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
                pass.setEditable(true);
                tfTelepon.setEditable(true);
                alamat.setEditable(true);
                tfKelurahan.setEditable(true);
                tfKecamatan.setEditable(true);
                tfKota.setEditable(true);
                tfProvinsi.setEditable(true);
                tfKodePos.setEditable(true);
                submit.setVisible(false);
                backProfile.setVisible(false);
            }
        });
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(null, controller.update(
                        tfNama.getText(),
                        tfUserName.getText(),
                        pass.getText(),
                        tfTelepon.getText(),
                        alamat.getText(),
                        tfKelurahan.getText(),
                        tfKecamatan.getText(),
                        tfKota.getText(),
                        tfProvinsi.getText(),
                        tfKodePos.getText()));
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
                new Etalase();
            }
        });
        //button show pass
        JCheckBox show = new JCheckBox("Show");
        show.setBounds(490, 184, 60, 30);
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));
        show.setFont(new Font("Serif", Font.PLAIN, 15));
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (show.isSelected()) {
                    pass.setEchoChar((char) 0);
                } else {
                    pass.setEchoChar('\u2022');
                }
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
                new LogOutController();
            }
        });

        frame.add(judul);
        frame.add(namaLabel);
        frame.add(tfNama);
        frame.add(userNameLabel);
        frame.add(tfUserName);
        frame.add(passLabel);
        frame.add(pass);
        frame.add(teleponLabel);
        frame.add(tfTelepon);
        frame.add(alamatLabel);
        frame.add(alamat);
        frame.add(kelurahanLabel);
        frame.add(tfKelurahan);
        frame.add(kecamatanLabel);
        frame.add(tfKecamatan);
        frame.add(kotaLabel);
        frame.add(tfKota);
        frame.add(provinsiLabel);
        frame.add(tfProvinsi);
        frame.add(kodePosLabel);
        frame.add(tfKodePos);
        frame.add(submit);
        frame.add(backProfile);
        frame.add(show);
        frame.add(update);
        frame.add(riwayatPembelian);
        frame.add(statusPembelian);
        frame.add(backUpdate);
        frame.add(logout);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}