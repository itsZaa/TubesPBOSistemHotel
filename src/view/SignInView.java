package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import controller.SignInController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.awt.Font;

public class SignInView {
       public static void main(String[] args) {
        new SignInView();
    }
    JFrame frame = new JFrame("Sign In");
 
    public SignInView() {
        SignInController controller = new SignInController();
        frame.setSize(600, 380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("Serif", Font.PLAIN, 20);

        //label judul
        JLabel judul = new JLabel("Sign In");
        judul.setBounds(240, 5, 500, 60);
        judul.setFont(new Font("Serif", Font.BOLD, 35));

        //label username
        JLabel userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(100, 100, 90, 40);
        userNameLabel.setFont(font1);

        //textfield username
        JTextField tfUserName = new JTextField();
        tfUserName.setBounds(240, 105, 240, 30);
        tfUserName.setFont(font1);

        //label password
        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(100, 140, 90, 40);
        passLabel.setFont(font1);

        //pass
        JPasswordField pass = new JPasswordField();
        pass.setBounds(240, 145, 240, 30);
        pass.setFont(font1);

        JButton submit = new JButton("Login");
        submit.setBounds(300, 200, 180, 50);
        submit.setFont(font1);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                String result = controller.SignInController(tfUserName.getText(),pass.getText());
                if(result.equals("customer")){
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,"Login Berhasil Sebagai Customer");
                    new MainView(); // GANTI JDI TUJUAN CUSTOMER!
                }else if(result.equals("receptionist")){
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,"Login Berhasil Sebagai Receptionist");
                    new MainView(); //Menu receptionist
                }else if(result.equals("manager")){
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,"Login Berhasil Sebagai Manager");
                    new MainView();//Menu manager
                }else if(result.equals("staff_fnb")){
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,"Login Berhasil Sebagai Staff FNB");
                    new MainView(); //menu staff_fnb
                }else if(result.equals("staff_laundry")){
                    frame.dispose();
                    JOptionPane.showMessageDialog(null,"Login Berhasil Sebagai Staff Laundry");
                    new MainView();//menu staff laundry
                }else if(result.equals("Password Salah!")){
                    JOptionPane.showMessageDialog(null,result);
                    pass.setText("");
                    pass.requestFocus();
                }else{
                    JOptionPane.showMessageDialog(null,result);
                    tfUserName.setText("");
                    pass.setText("");
                    tfUserName.requestFocus();
                }
            }  
        });

        JButton back = new JButton("Kembali");
        back.setBounds(100, 200, 180, 50);
        back.setFont(font1);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new WelcomeScreen();
            } 
        });

        frame.add(judul);
        frame.add(userNameLabel);
        frame.add(tfUserName);
        frame.add(passLabel);
        frame.add(pass);
        frame.add(back);
        frame.add(submit);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}
