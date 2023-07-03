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
        JFrame form = new GlobalView().frame();
        Font headerFont = new GlobalView().headerFont();

        JLabel labelHeader = new GlobalView().labelHeader("Sistem");
        form.add(labelHeader);

        JLabel labelBody = new GlobalView().labelBody("input", 100, 150, 100, 20);
        form.add(labelBody);

        JTextField input1 = new JTextField();
        input1.setBounds(250, 50, 100, 20);
        // form.add(input1);

        form.setVisible(true); // HARUS DI PALING BAWAH
    }


}
