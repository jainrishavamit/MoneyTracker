package Classes;

import SetUp.Address;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Register extends MyFrame implements ActionListener{
    JLabel l_head,l_name,l_id,l_no,l_sq,l_ans,l_gen,l_init,l_pass,l_conpass;
    JTextField tf_name,tf_id,tf_no,tf_sq,tf_ans,tf_init;
    JPasswordField tf_pass,tf_conpass;
    JRadioButton rb_male,rb_female;
    ButtonGroup bg_gender;
    JButton bu_sub,bu_back,bu_chID,bu_inM;
    
    public Register() {
        addComponents();
    }

    @Override
    public void addComponents() {
       
        byte lb=50;
        Font f=new Font("Times New Roman",Font.BOLD,20);
        
        l_head=new JLabel("<html><u>Fill up the details to get Regisrered</u></html>");
        l_head.setBounds(0,20,550,35);
        l_head.setHorizontalAlignment(0);
        l_head.setFont(new Font("Monotype Cursiva",Font.BOLD|Font.ITALIC,30));
        l_head.setForeground(Color.darkGray);
        
        l_id=new JLabel("User ID");
        l_id.setFont(f);
        l_id.setBounds(lb,110, 155, 30);
        
        tf_id=new JTextField();
        tf_id.setBounds(lb+200,110,200,30);
        
        bu_chID=new JButton("<html>   Check Availibility</html>");
        bu_chID.setBounds(lb+405,108,80,35);
        bu_chID.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_chID.setToolTipText("Click here to Check Availibility of your ID");
        bu_chID.setBackground(new Color(153,153,255));
        bu_chID.setMnemonic(KeyEvent.VK_C);
        bu_chID.setActionCommand("check");
        bu_chID.addActionListener(this);
        
        l_name=new JLabel("Name");
        l_name.setFont(f);
        l_name.setBounds(lb,150 , 155, 30);
        
        tf_name=new JTextField();
        tf_name.setBounds(lb+200,150,200,30);
        
        l_gen=new JLabel("Gender");
        l_gen.setBounds(lb, 190, 155, 30);
        l_gen.setFont(f);
        
        rb_male=new JRadioButton("Male");
        rb_male.setBounds(lb+200, 190,100, 30);
        rb_male.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rb_male.setBackground(panel.getBackground());
        rb_male.setHorizontalAlignment(SwingConstants.CENTER);
        rb_female=new JRadioButton("Female");
        rb_female.setBounds(lb+300,190,100,30);
        rb_female.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rb_female.setBackground(panel.getBackground());
        rb_female.setHorizontalAlignment(SwingConstants.CENTER);
        bg_gender=new ButtonGroup();
        bg_gender.add(rb_male);
        bg_gender.add(rb_female);
        bg_gender.setSelected(rb_male.getModel(), true);
        
        l_no=new JLabel("Mobile Number");
        l_no.setBounds(lb, 230, 155, 30);
        l_no.setFont(f);

        tf_no=new JTextField();
        tf_no.setBounds(lb+200,230,200,30);
        tf_no.setToolTipText("This will be used to retrieve your ID if you forget it");
        
        bu_inM=new JButton();
        bu_inM.setBounds(lb+430, 230, 30, 30);
        bu_inM.setIcon(new ImageIcon(new ImageIcon(Address.sourcePath+"Info.png").getImage().getScaledInstance(bu_inM.getWidth(),bu_inM.getHeight(), Image.SCALE_SMOOTH)));
        bu_inM.addActionListener(this);
        
        l_init=new JLabel("Initial Amount");
        l_init.setBounds(lb, 270, 155, 30);
        l_init.setFont(f);
        
        tf_init=new JTextField();
        tf_init.setBounds(lb+200,270,200,30);
        
        l_sq=new JLabel("Security Question");
        l_sq.setFont(f);
        l_sq.setBounds(lb, 310, 155, 30);
        
        tf_sq=new JTextField();
        tf_sq.setBounds(lb+200,310,200,30);
        
        l_ans=new JLabel("Answer");
        l_ans.setFont(f);
        l_ans.setBounds(lb, 350, 155, 30);
        
        tf_ans=new JTextField();
        tf_ans.setBounds(lb+200,350,200,30);
        
        l_pass=new JLabel("Password");
        l_pass.setBounds(lb, 390, 155, 30);
        l_pass.setFont(f);
        
        tf_pass=new JPasswordField();
        tf_pass.setBounds(lb+200,390,200,30);
        
        l_conpass=new JLabel("Confirm Password");
        l_conpass.setBounds(lb, 430, 155, 30);
        l_conpass.setFont(f);
        
        tf_conpass=new JPasswordField();
        tf_conpass.setBounds(lb+200,430,200,30);
        
        bu_sub=new JButton("<html><u>Submit</u></html>");
        bu_sub.setBounds(210,500, 100, 30);
        bu_sub.setBackground(new Color(153,153,255));
        bu_sub.setCursor(Cursor.getPredefinedCursor(12));
        bu_sub.setToolTipText("Click here to get Verified and Registered");
        bu_sub.addActionListener(this);
        
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_back.setBounds(410, 530, 100, 30);
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_back.setToolTipText("Click here to go back to log In page");
        bu_back.setBackground(new Color(153,153,255));
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        
        addCompo(l_head,l_name,l_id,l_no,l_sq,l_ans,l_gen,l_init,l_pass,l_conpass,tf_name,tf_id,tf_no,tf_sq,tf_ans,tf_init,tf_pass,tf_conpass,rb_male,rb_female,bu_sub,bu_back,bu_chID,bu_inM);
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        if("back".equals(e.getActionCommand()))
        {
                System.out.println("BACK button pressed");
                frame.dispose();
                new RegisterTerms();
        }
         if(e.getSource()==bu_sub)
         {
                System.out.println("Submit Button Pressed");
                if(checkInputs())
                {
                    if(updateData()){
                        JOptionPane.showMessageDialog(panel,"You have been successfully registered. Log in with your Id and Password to continue");
                        frame.dispose();
                        new Login();  
                    }
                }
         }
         if(e.getSource()==bu_inM){
                JOptionPane.showMessageDialog(panel, "* Number must be 10 character long\n* This will be used to get your user ID","Number Hints", 1);
         }
         if(e.getSource()==bu_chID)
         {
                if(isIdAvailable())
                    JOptionPane.showMessageDialog(panel,tf_id.getText()+" is available, you may proceed with this Id");              
         }
    } 
    
    private boolean isIdAvailable(){
        if(tf_id.getText().contains(" ")){
            JOptionPane.showMessageDialog(panel," Id cannot contain space");
            return false;
        }
        if(tf_id.getText().length()==0){
            JOptionPane.showMessageDialog(panel," Id is Required");
            return false;
        }
        if(tf_id.getText().length()>25){
            JOptionPane.showMessageDialog(panel," Id selected is not valid. Please select Id within the length less than 25 characters");
            return false;
        }
        
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select id from users where id = ?");
            ps.setString(1,tf_id.getText());
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                    JOptionPane.showMessageDialog(panel,tf_id.getText()+" is not available, Please select another Id");
                    tf_id.setText("");
                    return false;
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
   }
    
    private boolean isNumberDuplicate(){
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select mobileNumber from users where mobileNumber = ?");
            ps.setLong(1,Long.parseLong(tf_no.getText()));
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                    System.out.println("number is "+ rs.getLong(1));
                    return true;
            }
                
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(panel,"Exception has occured while Checking Number Duplicacy\n Exception : "+ex);
        }
        return false;
   }
    
    private boolean checkInputs(){
        if(!isIdAvailable()){
            return false;
        }
        
        if(tf_name.getText().length()==0){
            JOptionPane.showMessageDialog(panel,"Name is required");
            return false;
        }
        if(tf_name.getText().length()>25){
            JOptionPane.showMessageDialog(panel,"Name selected is not valid. Please select name with length less than 25 characters");
            return false;
        }
        
        if("".equals(tf_no.getText())){
            JOptionPane.showMessageDialog(panel,"Mobile Number is required");
            return false;
        }
        
        try{
            long no=Long.parseLong(tf_no.getText());
            if(no<7000000000l || no >9999999999l){
                JOptionPane.showMessageDialog(panel,"Invalid Mobile Number");
                return false;
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(panel,"Invalid Mobile Number Format");
            return false;
        }
        
        if(isNumberDuplicate())
        {
            JOptionPane.showMessageDialog(panel,"Mobile Number "+Long.parseLong(tf_no.getText())+" is already registered");
            return false;
        }
        
        if("".equals(tf_init.getText())){
            JOptionPane.showMessageDialog(panel,"Initial Amount is required");
            return false;
        }
        
        try{
            Double.parseDouble(tf_init.getText());
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(panel,"Invalid Amount Format");
            return false;
        }
        
        if(tf_sq.getText().length()==0){
            JOptionPane.showMessageDialog(panel,"Security Question is required");
            return false;
        }
        
        if(tf_sq.getText().length()>25){
            JOptionPane.showMessageDialog(panel,"Security Question selected is not valid. Please select with length less than 25 characters");
            return false;
        }
        
        if(tf_ans.getText().length()==0){
            JOptionPane.showMessageDialog(panel,"Answer of Security Question is required");
            return false;
        }
        
        if(tf_ans.getText().length()>25){
            JOptionPane.showMessageDialog(panel,"Answer of Security Question selected is not valid. Please select with length less than 25 characters");
            return false;
        }
        
        if(String.valueOf(tf_pass.getPassword()).length()==0){
            JOptionPane.showMessageDialog(panel,"Password is required");
            return false;
        }
        
        if(String.valueOf(tf_pass.getPassword()).length()<4 ||String.valueOf(tf_pass.getPassword()).length()>25){
            JOptionPane.showMessageDialog(panel,"Password is not valid. Please select within the length between 4 to 25");
            return false;
        }
        
        if(String.valueOf(tf_conpass.getPassword()).length()==0){
            JOptionPane.showMessageDialog(panel,"Confirmation Password is required");
            return false;
        }
        
        if(!String.valueOf(tf_conpass.getPassword()).equals(String.valueOf(tf_pass.getPassword()))){
            JOptionPane.showMessageDialog(panel,"Passwords do not match");
            return false;
        }
        
        if(String.valueOf(tf_conpass.getPassword()).length()<4 ||String.valueOf(tf_conpass.getPassword()).length()>25){
            JOptionPane.showMessageDialog(panel,"Confirm Password is not valid. Please select within the length between 4 to 25");
            return false;
        }
        return true;
   }
    
    private boolean updateData(){
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("Insert into users values (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, tf_id.getText());
            ps.setString(2,tf_name.getText());
            ps.setString(3,getGender());
            ps.setLong(4,Long.parseLong(tf_no.getText()));
            ps.setDouble(5, Double.parseDouble(tf_init.getText()));
            ps.setString(6, tf_sq.getText());
            ps.setString(7,tf_ans.getText());
            ps.setString(8, String.valueOf(tf_pass.getPassword()));
            ps.setByte(9,(byte)0);
            ps.setBytes(10, null);
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Exception has occured while registering user\nException :"+ex);
            return false;
        }
        return true;
   }
    
    private String getGender(){
        if(rb_male.isSelected())
            return rb_male.getText();
        else
            return rb_female.getText();     
    }
   
    public static void main(String[] args) {
        new Register();
    }
}