//error in answer : "" is coming
package Classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForPass extends MyFrame implements ActionListener{
    JLabel l_head,l_id,l_que,l_ans,l_entPass,l_conPass;
    JButton bu_id,bu_fetQue,bu_verify,bu_resetPass,bu_back,bu_resetFields;
    JTextField tf_id,tf_que,tf_ans;
    JPasswordField tf_entPass,tf_conPass;
    
    public ForPass(){
        addComponents();
    }
    
    @Override
    public void addComponents() {
        l_head=new JLabel("<html><u>Forget Password</u> :</html>");
        l_id=new JLabel("Enter Your ID");
        l_que=new JLabel("Security Question");
        l_ans=new JLabel("Enter Answer");
        l_entPass=new JLabel("Enter New Password");
        l_conPass=new JLabel("Confirm Password");
        
        bu_id=new JButton("Forget ID");
        bu_fetQue=new JButton("Fetch Question");
        bu_verify=new JButton("Verify");
        bu_resetPass=new JButton("Reset Password");
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_resetFields=new JButton("<html><u>Reset Fields</u></html>");
        
        tf_id=new JTextField();
        tf_que=new JTextField();
        tf_ans=new JTextField();
        
        tf_entPass=new JPasswordField();
        tf_conPass=new JPasswordField();

        l_head.setBounds(25,40,250,50);
        l_id.setBounds(60,130,140,20);
        tf_id.setBounds(230,128,150,20);
        bu_id.setBounds(415, 128, 85, 20);
        bu_fetQue.setBounds(185,170,130,30);
        l_que.setBounds(60,250,140,20);
        tf_que.setBounds(230,248,150,20);
        l_ans.setBounds(60,280,140,20);
        tf_ans.setBounds(230,278,150,20);
        bu_verify.setBounds(185,320,130,30);
        l_entPass.setBounds(60,400, 140, 20);
        tf_entPass.setBounds(230,398,150,20);
        l_conPass.setBounds(60,430,140,20);
        tf_conPass.setBounds(230,428,150,20);
        bu_resetPass.setBounds(115,470,130,30);
        bu_resetFields.setBounds(285, 470, 130, 30);
        bu_back.setBounds(410, 530, 100, 30);
        
        tf_que.setEditable(false);
        tf_ans.setEditable(false);
        tf_conPass.setEditable(false);
        tf_entPass.setEditable(false);
        bu_verify.setEnabled(false);
        bu_resetPass.setEnabled(false);
        
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_fetQue.setCursor(new Cursor(12));
        bu_id.setCursor(Cursor.getPredefinedCursor(12));
        bu_resetPass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bu_verify.setCursor(new Cursor(12));
        bu_resetFields.setCursor(new Cursor(12));

        bu_back.setToolTipText("Click here to go back to log In page");
        bu_id.setToolTipText("Click here to Get your ID");
        bu_fetQue.setToolTipText("Click here to get your Security Question");
        bu_verify.setToolTipText("Click here to submit your answer and verify it to reset password");
        bu_resetPass.setToolTipText("Click here to reset Password");
        bu_resetFields.setToolTipText("Click here to reset all fields");
        
        l_id.setHorizontalAlignment(SwingConstants.RIGHT);
        l_ans.setHorizontalAlignment(SwingConstants.RIGHT);
        l_conPass.setHorizontalAlignment(SwingConstants.RIGHT);
        l_que.setHorizontalAlignment(SwingConstants.RIGHT);
        
        l_head.setFont(new Font("Monotype Corsiva",Font.BOLD|Font.ITALIC,30));
        l_head.setForeground(Color.darkGray);
        Font f=new Font("Times New Roman",Font.BOLD,15);
        l_id.setFont(f);
        l_ans.setFont(f);
        l_conPass.setFont(f);
        l_que.setFont(f);
        l_entPass.setFont(f);
        
        bu_back.setBackground(new Color(153,153,255));
        bu_fetQue.setBackground(new Color(153,153,255));
        bu_id.setBackground(new Color(153,153,255));
        bu_resetPass.setBackground(new Color(153,153,255));
        bu_verify.setBackground(new Color(153,153,255));
        bu_resetFields.setBackground(new Color(153,153,255));
        
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        
        addCompo(l_head,l_id,l_que,l_ans,l_entPass,l_conPass,bu_id,bu_fetQue,bu_verify,bu_resetPass,bu_back,tf_id,tf_que,tf_ans,tf_entPass,bu_resetFields,tf_conPass);
        
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        bu_fetQue.addActionListener(this);
        bu_verify.addActionListener(this);
        bu_resetFields.addActionListener(this);
        bu_resetPass.addActionListener(this);
        bu_id.addActionListener(this);
    }
    public static void main(String[] args) {
        new ForPass();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("back".equals(e.getActionCommand()))
        {
                System.out.println("BACK button pressed");
                frame.dispose();
                new Login();
        }
         if(e.getSource()==bu_id)
        {
                System.out.println("Forget ID button pressed");
                frame.dispose();
                new ForId();
        }
         if(e.getSource()==bu_fetQue)
         {
                 String que=User.getSecurityQuestion(tf_id.getText());
                 System.out.println("question is "+que+" for id= "+tf_id.getText());
                 if(que!=null){
                    tf_que.setText(que);
                    tf_id.setEditable(false);
                    bu_fetQue.setEnabled(false);
                    tf_ans.setEditable(true);
                    bu_verify.setEnabled(true);
                 }
                 else
                     JOptionPane.showMessageDialog(panel, "Invalid Id");
         }
         if(e.getSource()==bu_verify)
         {
                String ans=User.getAnswer(tf_id.getText());
                System.out.println("answer is "+ans+" for id= "+tf_id.getText());
                if(ans.equals(tf_ans.getText())){
                    tf_entPass.setEditable(true);
                    tf_conPass.setEditable(true);
                    bu_resetPass.setEnabled(true);
                }
                else{
                    JOptionPane.showMessageDialog(panel, "Incorrect Answer");
                    tf_ans.setText("");
                }
         }
         if(e.getSource()==bu_resetPass)
         {
                String pass=String.valueOf(tf_entPass.getPassword());
                System.out.println("pass "+pass);
                if(Arrays.equals(tf_entPass.getPassword(), (tf_conPass.getPassword())))
                {
                    try {
                        con=createConnection();
                        PreparedStatement ps=con.prepareStatement("Update users set password=? where id like ?");
                        ps.setString(1, pass);
                        ps.setString(2, tf_id.getText());
                        int i=ps.executeUpdate();
                        System.out.println(i+" rows update");
                        JOptionPane.showMessageDialog(panel, "Password for id : "+tf_id.getText()+" has been updated successfully.\n\nYou will be redirected to log in page to log in with new password\n ");
                        frame.dispose();
                        new Login();
                    } catch (SQLException ex) {
                        Logger.getLogger(ForPass.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else
                    JOptionPane.showMessageDialog(panel, "Passwords do not match");
         }
         if(e.getSource()==bu_resetFields)
        {
                tf_id.setText("");
                tf_id.setEditable(true);
                bu_fetQue.setEnabled(true);
                tf_que.setText("");
                tf_ans.setText("");
                tf_ans.setEditable(false);
                bu_verify.setEnabled(false);
                tf_conPass.setText("");
                tf_conPass.setEditable(false);
                tf_entPass.setText("");
                tf_entPass.setEditable(false);
                bu_resetPass.setEnabled(false);
        }
    }
}
