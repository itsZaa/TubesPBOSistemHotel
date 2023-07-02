package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GlobalView {


    public JFrame frame () {
        JFrame form = new JFrame("Aplikasi Sistem Hotel");
        form.setSize(500,500);
        form.setLayout(null);
        form.setLocationRelativeTo(null);

        return form;
    }

    public Font headerFont () {
        Font font  = new Font(Font.SANS_SERIF, Font.BOLD, 22);
        return font;
    }

    public Font bodyFont () {
        Font font  = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        return font;
    }

    public JLabel labelHeader(String text) {
        JLabel label = new JLabel(text);
        label.setBounds(0, 20, 500, 30);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(headerFont());

        return label;
    }

    public JLabel labelBody(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(bodyFont());

        return label;
    }

    // public JTextField input () {
        
    // }
}