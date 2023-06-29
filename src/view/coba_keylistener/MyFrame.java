package view.coba_keylistener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.Color;

public class MyFrame extends JFrame implements KeyListener{
    
    JLabel label;
    
    MyFrame(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setLayout(null);
        this.addKeyListener(this);

        this.label = new JLabel();
        this.label.setBounds(0,0,100, 100);
        this.label.setBackground(Color.red);
        this.label.setOpaque(true);

        this.add(label);
        this.setVisible(true);
    }

    //karena class ini implement KeyListener, maka harus override the implemented method nya
    @Override
    public void keyTyped(KeyEvent e){
        //keytyped = invoked when a key is typed. Uses keychar, char output
        switch(e.getKeyChar()){
            case 'a':
                label.setLocation(label.getX()-10, label.getY());
                break;
            case 'w':
                label.setLocation(label.getX(), label.getY()-10);
                break;
            case 's':
                label.setLocation(label.getX(), label.getY()+10);
                break;
            case 'd':
                label.setLocation(label.getX()+10, label.getY());
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        //keyPressed = invoked when a physical key is pressed down. Uses keyCode, int output
        switch(e.getKeyCode()){
            case 37:
                label.setLocation(label.getX()-10, label.getY());
                break;
            case 38:
                label.setLocation(label.getX(), label.getY()-10);
                break;
            case 40:
                label.setLocation(label.getX(), label.getY()+10);
                break;
            case 39:
                label.setLocation(label.getX()+10, label.getY());
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){
        //keyReleased = called whenever a button is released
        System.out.println("You released key char: " + e.getKeyCode());
    }
}
