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

public class WelcomeScreen {
public static void main(String[] args) {
    new WelcomeScreen();
}
JFrame frame = new JFrame("Sistem Hotel Harapan Bangsa");

public WelcomeScreen(){
    frame.setSize(600, 380);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font1 = new Font("Serif", Font.PLAIN, 20);

        //label judul
        JLabel judul = new JLabel("Sistem Hotel HB");
        judul.setBounds(175, 5, 500, 60);
        judul.setFont(new Font("Serif", Font.BOLD, 35));
        
        JButton SignIn = new JButton("Sign In");
        SignIn.setBounds(200, 100, 180, 50);
        SignIn.setFont(font1);
        SignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SignIn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new SignInView();
            } 
        });
        JButton SignUp = new JButton("Sign Up");
        SignUp.setBounds(200, 160, 180, 50);
        SignUp.setFont(font1);
        SignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SignUp.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new SignUpView();
            } 
        });
        frame.add(judul);
        frame.add(SignIn);
        frame.add(SignUp);
        frame.setLayout(null);
        frame.setVisible(true);
}
        
}
