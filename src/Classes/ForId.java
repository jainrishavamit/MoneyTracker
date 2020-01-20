package Classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ForId extends MyFrame implements ActionListener{
    JLabel l_head,l_mobNo,l_que,l_ans,l_id;
    JButton bu_fetQue,bu_verify,bu_back,bu_reset;
    JTextField tf_mobNo,tf_que,tf_ans,tf_id;
    
    public ForId(){
        addComponents();
    }
    
    @Override
    public void addComponents() {
        l_head=new JLabel("<html><u>Forget ID</u> :</html>");
        l_mobNo=new JLabel("Enter Your Mobile Number");
        l_que=new JLabel("Security Question");
        l_ans=new JLabel("Enter Answer");
        l_id=new JLabel("Your ID is    ");
        
        bu_fetQue=new JButton("Fetch Question");
        bu_verify=new JButton("Verify");
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_reset=new JButton("<html><u>Reset</u></html>");
        
        tf_mobNo=new JTextField();
        tf_que=new JTextField();
        tf_ans=new JTextField();
        tf_id=new JTextField();
        
        l_head.setBounds(25,50,250,50);
        l_mobNo.setBounds(60,150,190,20);
        tf_mobNo.setBounds(270,148,170,20);
        bu_fetQue.setBounds(200,190,130,30);
        l_que.setBounds(100,270,140,20);
        tf_que.setBounds(270,268,170,20);
        l_ans.setBounds(100,310,140,20);
        tf_ans.setBounds(270,308,170,20);
        bu_verify.setBounds(200,355,130,30);
        l_id.setBounds(100,435, 140, 20);
        tf_id.setBounds(270,433,170,20);
        bu_reset.setBounds(200, 470, 130, 30);
        bu_back.setBounds(410, 530, 100, 30);
        
        tf_que.setEditable(false);
        tf_ans.setEditable(false);
        tf_id.setEditable(false);
        bu_verify.setEnabled(false);
        
        bu_back.setCursor(new Cursor(12));
        bu_fetQue.setCursor(new Cursor(12));
        bu_verify.setCursor(new Cursor(12));
        bu_reset.setCursor(new Cursor(12));

        l_mobNo.setHorizontalAlignment(SwingConstants.RIGHT);
        l_ans.setHorizontalAlignment(SwingConstants.RIGHT);
        l_que.setHorizontalAlignment(SwingConstants.RIGHT);
        l_id.setHorizontalAlignment(SwingConstants.RIGHT);
        
        l_head.setFont(new Font("Monotype Corsiva",Font.BOLD|Font.ITALIC,30));
        l_head.setForeground(Color.darkGray);
        Font f=new Font("Times New Roman",Font.BOLD,15);
        l_mobNo.setFont(f);
        l_ans.setFont(f);
        l_que.setFont(f);
        l_id.setFont(f);
        
        bu_back.setBackground(new Color(153,153,255));
        bu_fetQue.setBackground(new Color(153,153,255));
        bu_verify.setBackground(new Color(153,153,255));
        bu_reset.setBackground(new Color(153,153,255));
         
        bu_fetQue.setToolTipText("Click here to get your Security Question");
        bu_back.setToolTipText("Click here to go back to log In page");
        bu_verify.setToolTipText("Click here to submit your answer and verify it to reset password");
        bu_reset.setToolTipText("Click here to reset all fields");
        
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        
        addCompo(l_head,l_mobNo,l_que,l_ans,l_id,bu_fetQue,bu_verify,bu_back,tf_mobNo,tf_que,tf_ans,tf_id,bu_reset);
        
        bu_back.addActionListener(this);
        bu_verify.addActionListener(this);
        bu_reset.addActionListener(this);
        bu_fetQue.addActionListener(this);
    }
    public static void main(String[] args) {
        new ForId();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu_back)
        {
            System.out.println("BACK button pressed");
            frame.dispose();
            new Login();
        }
        if(e.getSource()==bu_fetQue)
         {
             try{
                 Long no=Long.parseLong(tf_mobNo.getText());
                 String que=User.getSecurityQuestion(no);
                 System.out.println("question is "+que+" for id= "+tf_mobNo.getText());
                 if(!que.equals("")){
                    tf_que.setText(que);
                    tf_ans.setEditable(true);
                    tf_mobNo.setEditable(false);
                    bu_verify.setEnabled(true);
                    bu_fetQue.setEnabled(false);
                 }
                 else if(no<7000000000l||no>9999999999l)
                     JOptionPane.showMessageDialog(panel, "Invalid Mobile Number");
                 else
                     JOptionPane.showMessageDialog(panel, "Mobile number not registered");
             }
             catch(NumberFormatException ex){
                 JOptionPane.showMessageDialog(panel, "Invalid Mobile Number");
             }
         }
        if(e.getSource()==bu_verify)
        {
            Long no=Long.parseLong(tf_mobNo.getText());
            String ans=User.getAnswer(no);
            System.out.println("answer is "+ans+" for id= "+tf_mobNo.getText());
            if(ans.equals(tf_ans.getText()))
                tf_id.setText(User.getId(no));
            else{
                JOptionPane.showMessageDialog(panel, "Incorrect Answer");
                tf_id.setText("");
                tf_ans.setText("");
            }
        }
        if(e.getSource()==bu_reset)
        {
            tf_mobNo.setText("");
            tf_mobNo.setEditable(true);
            bu_fetQue.setEnabled(true);
            tf_que.setText("");
            tf_ans.setText("");
            tf_ans.setEditable(false);
            bu_verify.setEnabled(false);
            tf_id.setText("");
            tf_id.setEditable(false);
        }
    }
}
