package view;

import model.SingletonUser;
import model.Staff;
import model.StaffType;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainMenuStaff {
    private Staff staff;

    public MainMenuStaff(Staff staff){
        this.staff = staff;

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
        JButton button2 = null;
        JButton button3 = null;

        if(staff.getStaffType() == StaffType.RECEPTIONIST){
            button1 = new JButton("Proses Check-In");
            button2 = new JButton("Proses Check-Out");
        }else if(staff.getStaffType() == StaffType.STAFF_FNB){
            button1 = new JButton("Proses Pesanan F&B");
        }else if(staff.getStaffType() == StaffType.STAFF_LAUNDRY){
            button1 = new JButton("Proses Pesanan Laundry");
        }else if(staff.getStaffType() == StaffType.MANAGER){
            button1 = new JButton("Lihat Semua Pendapatan & Transaksi");
            button2 = new JButton("Update Fitur");
            button3 = new JButton("Update Staff");
        }

    

        if(button1 != null){
            button1.setFocusable(false);
            menuPanel.add(button1);
        }

        if(button2 != null){
            button2.setFocusable(false);
            menuPanel.add(button2);
        }

        if(button3 != null){
            button3.setFocusable(false);
            menuPanel.add(button3);
        }

        contentPanel.add(headerPanel);
        contentPanel.add(menuPanel);
        frame.setContentPane(contentPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Staff dummy = new Staff(123, "123", 100000, StaffType.STAFF_LAUNDRY, null, null, null, null, null, null, null);

        new MainMenuStaff(dummy);
    }
}
