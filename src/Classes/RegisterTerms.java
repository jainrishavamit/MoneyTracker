package Classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class RegisterTerms extends MyFrame implements ActionListener {
    JLabel l_head,l_main;
    JButton bu_back,bu_next;

    public RegisterTerms() {
        addComponents();
    }
    
    @Override
    public void addComponents() {
        l_head=new JLabel("<html><u>Terms & Condition to get Registered</html></u>");
        l_head.setBounds(25,25,500,50);
        l_head.setFont(new Font("Monotype Cursiva",Font.BOLD|Font.ITALIC,27));
        l_head.setForeground(Color.darkGray);
        
        l_main=new JLabel("<html>"
                        + "<ol>"
                                + "<li>User ID once opted can't be changed again.<br>"
                                + "<li>One Mobile number can be used to register only one user.<br>"
                                + "<li>You have to choose your own Security Question with its answer, this will be essential when you forget your ID or"
                                        + " Password to verify your identity.<br>"
                                + "<li>In case you forget your ID, you can recover it by using mobile number and security question.<br>"
                                + "<li>In case you forget your Password, you can recover it by using your ID and security Question.<br>"
                                + "<li>In case you forget your Mobile number, there is no way to recover it.<br>"
                                + "<li>In case you forget your ID along with mobile number, your data will be lost and can't be recovered."
                                + "<li>In case you forget your answer of security question, thr there is no way to recover your ID and Password."
                        + "</ol>"
                        + "</html>");
        l_main.setFont(new Font("Times New Roman",Font.PLAIN,20));
        l_main.setBounds(10,70,510, 410);
        
        bu_next=new JButton("<html><u>Next</u></html>");
        bu_next.setBounds(300, 530, 100, 30);
        bu_next.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_next.setToolTipText("Click here to Accept Terms and Condition and Get Registered");
        bu_next.setBackground(new Color(153,153,255));
        bu_next.setMnemonic(KeyEvent.VK_ENTER);
        bu_next.setActionCommand("next");
        bu_next.addActionListener(this);
        
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_back.setBounds(410, 530, 100, 30);
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_back.setToolTipText("Click here to go back to log In page");
        bu_back.setBackground(new Color(153,153,255));
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        
        addCompo(l_head,l_main,bu_back,bu_next);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if("back".equals(e.getActionCommand()))
        {
            System.out.println("BACK button pressed");
            frame.dispose();
            new Login();
        }
        if("next".equals(e.getActionCommand())){
            System.out.println("BACK button pressed");
            frame.dispose(); 
            new Register();           
        }
    }
    
    public static void main(String[] args) {
        new RegisterTerms();
    }
   
}
