package view;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class GlobalView {
    public JFrame frame() {
        JFrame form = new JFrame("Aplikasi Sistem Hotel");
        form.setSize(500, 500);
        form.setLayout(null);
        form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        form.setLocationRelativeTo(null);
        form.setResizable(false);

        return form;
    }

    public Font headerFont() {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 22);
        return font;
    }

    public Font bodyFont() {
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 14);
        return font;
    }

    public Font bodyFontBold() {
        Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);
        return font;
    }

    public JLabel labelHeader(String text) {
        JLabel label = new JLabel(text);
        label.setBounds(0, 10, 480, 30);
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

    public void notif(String text) {
        JOptionPane.showMessageDialog(null, text, "Aplikasi Sistem Hotel", JOptionPane.INFORMATION_MESSAGE);
    }

    public void warning(String text) {
        JOptionPane.showMessageDialog(null, text, "Aplikasi Sistem Hotel", JOptionPane.WARNING_MESSAGE);
    }

    public void error(String text) {
        JOptionPane.showMessageDialog(null, text, "Aplikasi Sistem Hotel", JOptionPane.ERROR_MESSAGE);
    }

    public boolean confirmation(String text) {
        int confirmation = JOptionPane.showConfirmDialog(null, text, "Confirmation", JOptionPane.YES_NO_OPTION);
        return confirmation == JOptionPane.YES_OPTION;
    }

    // public JTextField input () {

    // }
}