package view;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainView {
    public static void main(String[] args) {
        //container
        JFrame form = new JFrame("Aplikasi Sistem Hotel");
        form.setSize(500,500);
        form.setLayout(null);
        form.setLocationRelativeTo(null);

        JLabel labelTitle = new JLabel("Main Menu");
        labelTitle.setSize(120, 20);
        labelTitle.setLocation(190, 10);

        Font font = new Font("Arial", Font.BOLD, 20);
        labelTitle.setFont(font);

        labelTitle.setForeground(Color.RED);

        form.add(labelTitle);

        // JTextArea inputArea = new JTextArea("Alamat", 5,10);
        // inputArea.setSize(100, 20);
        // inputArea.setLocation(100, 30);
        // inputArea.setForeground(Color.BLUE);
        // inputArea.setLineWrap(true); // Enable line wrapping for long text
        // inputArea.setWrapStyleWord(true); // Wrap at word boundaries
        // form.add(inputArea);
        
        JLabel label1 = new JLabel("Nama :");
        label1.setBounds(100, 50, 100, 20);
        form.add(label1);
        JTextField input1 = new JTextField();
        input1.setBounds(250, 50, 100, 20);
        form.add(input1);

        JRadioButton radio1 = new JRadioButton("Radio1");
        radio1.setBounds(100, 80, 150, 20);
        form.add(radio1);

        JRadioButton radio2 = new JRadioButton("Radio2");
        radio2.setBounds(250, 80, 150, 20);
        form.add(radio2);
    
        JButton submit = new JButton("Submit");
        submit.setBounds(300, 400, 75, 20);
        form.add(submit);


        form.setVisible(true); //HARUS DI PALING BAWAH
    }


}
