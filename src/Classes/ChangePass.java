package Classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;


public class ChangePass extends MyFrame implements ActionListener{

    JLabel l_head,l_oldPass,l_newPass,l_conPass;
    JPasswordField tf_oldPass,tf_newPass,tf_conPass;
    JButton bu_back,bu_changePass;
    public ChangePass() {
        addComponents();
    }

    
    @Override
    public void addComponents() {
        int lb=95;
        Font f=new Font("Times New Roman",Font.PLAIN,16);
        
        l_head=new JLabel("<html><u>Fill up the details to change Password</html></u>");
        l_head.setBounds(0,35,550,50);
        l_head.setHorizontalAlignment(SwingConstants.CENTER);
        l_head.setFont(new Font("Monotype Cursiva",Font.BOLD|Font.ITALIC,28));
        l_head.setForeground(Color.darkGray);
        
        l_oldPass=new JLabel("Old Password");
        l_oldPass.setBounds(lb,160,100, 30);
        l_oldPass.setFont(f);
        
        tf_oldPass=new JPasswordField();
        tf_oldPass.setBounds(lb+160, 160, 200, 30);
        
        l_newPass=new JLabel("New Password");
        l_newPass.setBounds(lb,210,100, 30);
        l_newPass.setFont(f);
        
        tf_newPass=new JPasswordField();
        tf_newPass.setBounds(lb+160, 210, 200, 30);
        
        l_conPass=new JLabel("Confirm Password");
        l_conPass.setBounds(lb,260,140, 30);
        l_conPass.setFont(f);
        
        tf_conPass=new JPasswordField();
        tf_conPass.setBounds(lb+160, 260, 200, 30);
        
        bu_changePass=new JButton("<html><u>Change Password</u></html>");
        bu_changePass.setBounds(lb+85, 330, 150, 30);
        bu_changePass.setBackground(new Color(153,153,255));
        bu_changePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_changePass.setToolTipText("Click here to change your Password");
        bu_changePass.setActionCommand("changePass");
        bu_changePass.addActionListener(this);
        
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_back.setBounds(410, 530, 100, 30);
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_back.setToolTipText("Click here to go back to Settings ");
        bu_back.setBackground(new Color(153,153,255));
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        
        addCompo(l_head,l_oldPass,l_newPass,l_conPass,tf_oldPass,tf_newPass,tf_conPass,bu_back,bu_changePass);
    }
    
    public static void main(String[] args) {
        new ChangePass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("changePass".equals(e.getActionCommand()))
        {
            if(checkInputs()){
                if(User.getPassword().equals(String.valueOf(tf_oldPass.getPassword()))){
                    User.setPassword(String.valueOf(tf_newPass.getPassword()));
                    JOptionPane.showMessageDialog(panel, "Password Changed Successfully");
                    frame.dispose();
                    new Profile();
                }
                else
                    JOptionPane.showMessageDialog(panel, "Old Password is Incorrect");
            }
        }
        if("back".equals(e.getActionCommand()))
        {
            frame.dispose();
            new Profile();
        }  
    }
    private boolean checkInputs(){
        if(String.valueOf(tf_oldPass.getPassword()).length()==0){
            JOptionPane.showMessageDialog(panel,"Old Password is required");
            return false;
        }
        
        if(String.valueOf(tf_newPass.getPassword()).length()==0){
            JOptionPane.showMessageDialog(panel,"New Password is required");
            return false;
        }
        
        if(String.valueOf(tf_newPass.getPassword()).length()<4 ||String.valueOf(tf_newPass.getPassword()).length()>25){
            JOptionPane.showMessageDialog(panel,"New Password is not valid. Please select within the length between 4 to 25");
            return false;
        }
        
        if(String.valueOf(tf_conPass.getPassword()).length()==0){
            JOptionPane.showMessageDialog(panel,"Confirmation Password is required");
            return false;
        }
        
        if(!String.valueOf(tf_conPass.getPassword()).equals(String.valueOf(tf_newPass.getPassword()))){
            JOptionPane.showMessageDialog(panel,"Confiramation Password do not match");
            return false;
        }
        
        if(String.valueOf(tf_conPass.getPassword()).length()<4 ||String.valueOf(tf_conPass.getPassword()).length()>25){
            JOptionPane.showMessageDialog(panel,"Confirm Password is not valid. Please select within the length between 4 to 25");
            return false;
        }
        return true;
    }
    
}
