package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Staff;

public class CheckInView {
    JFrame frame;
    public CheckInView (Staff staff) {
        initComponent();
    }

    private void initComponent(){
        frame = new GlobalView().frame();

        JLabel title = new GlobalView().labelHeader("Check-In");
        frame.add(title);

        JLabel labelUsername = new JLabel("Username :");
        JTextField fieldUsername = new JTextField();
        labelUsername.setBounds(10, 55, 120, 25);
        fieldUsername.setBounds(140, 55, 200, 25);
        frame.add(labelUsername);
        frame.add(fieldUsername);

        // date if time < 2PM, date -1

        //get Transaction where date + OrderList

        //asumsi tidak dapat pilih kamar
        //check kamar kosong, kasih kamar value, kamar boolean filled
        //query update transaction, update kamar
    }
}
