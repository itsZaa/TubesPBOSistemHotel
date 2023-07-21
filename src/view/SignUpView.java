package view;

import controller.SignUpController;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import model.Customer;

public class SignUpView {
    Customer data;

    public static void main(String[] args) {
        new SignUpView();
    }

    public SignUpView() {
        SignUpController controller = new SignUpController();
        JFrame frame = new JFrame("Form Sign Up");
        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("Serif", Font.PLAIN, 20);

        // label judul
        JLabel judul = new JLabel("Sign Up");
        judul.setBounds(225, 5, 500, 60);
        judul.setFont(new Font("Serif", Font.BOLD, 35));

        // label username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(100, 100, 90, 40);
        usernameLabel.setFont(font1);

        // textfield username
        JTextField tfUsername = new JTextField();
        tfUsername.setBounds(240, 105, 240, 30);
        tfUsername.setFont(font1);

        // label fullName
        JLabel fullNameLabel = new JLabel("Full Name");
        fullNameLabel.setBounds(100, 140, 90, 40);
        fullNameLabel.setFont(font1);

        // textfield fullname
        JTextField tfFullName = new JTextField();
        tfFullName.setBounds(240, 145, 240, 30);
        tfFullName.setFont(font1);

        // label email
        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(100, 180, 90, 40);
        emailLabel.setFont(font1);

        // textfield email
        JTextField tfEmail = new JTextField();
        tfEmail.setBounds(240, 185, 240, 30);
        tfEmail.setFont(font1);

        // label password
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(100, 220, 90, 40);
        passLabel.setFont(font1);

        // password
        JPasswordField pass = new JPasswordField();
        pass.setBounds(240, 225, 240, 30);
        pass.setFont(font1);

        // label alamat
        JLabel alamatLabel = new JLabel("Alamat");
        alamatLabel.setBounds(100, 260, 90, 40);
        alamatLabel.setFont(font1);

        // textfield alamat
        JTextField tfAlamat = new JTextField();
        tfAlamat.setBounds(240, 265, 240, 30);
        tfAlamat.setFont(font1);

        // label gender
        JLabel genderLabel = new JLabel("Gender");
        genderLabel.setBounds(100, 300, 90, 40);
        genderLabel.setFont(font1);

        // radioButton gender
        JRadioButton maleRadioButton = new JRadioButton("Laki-laki");
        maleRadioButton.setBounds(240, 305, 80, 30);

        JRadioButton femaleRadioButton = new JRadioButton("Perempuan");
        femaleRadioButton.setBounds(325, 305, 100, 30);

        // label notelp
        JLabel noTelpLabel = new JLabel("Nomor Telp");
        noTelpLabel.setBounds(100, 345, 100, 40);
        noTelpLabel.setFont(font1);

        // textfield notelp
        JTextField tfNoTelp = new JTextField();
        tfNoTelp.setBounds(240, 350, 240, 30);
        tfNoTelp.setFont(font1);
        // button submit
        JButton submit = new JButton("Sign Up");
        submit.setBounds(300, 400, 180, 50);
        submit.setFont(font1);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton back = new JButton("Kembali");
        back.setBounds(100, 400, 180, 50);
        back.setFont(font1);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        frame.add(judul);
        frame.add(usernameLabel);
        frame.add(tfUsername);
        frame.add(fullNameLabel);
        frame.add(tfFullName);
        frame.add(emailLabel);
        frame.add(tfEmail);
        frame.add(passLabel);
        frame.add(pass);
        frame.add(alamatLabel);
        frame.add(tfAlamat);
        frame.add(genderLabel);
        frame.add(maleRadioButton);
        frame.add(femaleRadioButton);
        frame.add(noTelpLabel);
        frame.add(tfNoTelp);
        frame.add(submit);
        frame.add(back);
        frame.setLayout(null);
        frame.setVisible(true);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String gender = null;
                if (maleRadioButton.isSelected()) {
                    gender = "Male";
                } else if (femaleRadioButton.isSelected()) {
                    gender = "Female";
                } else {
                    gender = null;
                }
                if (tfUsername.getText().isEmpty() || tfFullName.getText().isEmpty() || tfEmail.getText().isEmpty()
                        || pass.getPassword().equals("") || tfAlamat.getText().isEmpty()
                        || tfNoTelp.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Semua kolom wajib diisi!");
                } else {
                    String kondisi = controller.SignUp(tfUsername.getText(), tfFullName.getText(), tfEmail.getText(),
                            pass.getText(), tfAlamat.getText(), gender, tfNoTelp.getText());
                    JOptionPane.showMessageDialog(null, kondisi);
                    if (kondisi.equals("Berhasil melakukan registrasi")) {
                        frame.dispose();
                        new SignInView();
                    } else if (kondisi.equals("Username sudah digunakan")) {
                        tfUsername.setText("");
                        tfUsername.requestFocus();
                    } else {
                        tfNoTelp.setText("");
                        tfNoTelp.requestFocus();
                    }
                }
            }
        });
    }
}
