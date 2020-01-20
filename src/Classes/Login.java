
package Classes;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Login extends MyFrame implements MouseListener,ActionListener{
    JLabel heading,l_id,l_pass,l_reg,l_reg1,l_forId,l_forPass,l_showPassword;
    JTextField tf_id,tf_showPass;
    JButton bu_sub;
    JPasswordField tf_pass;
    JCheckBox cb_showPassword;
    
    public Login(){
        addComponents();    
    }
    
    @Override
    public void addComponents(){
        heading=new JLabel("<html><u>Please Login to Continue</u></html>");
        heading.setBounds(0, 90, 550,45);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setVerticalAlignment(SwingConstants.CENTER);    
        heading.setFont(new Font("Monotype Corsiva",Font.BOLD|Font.ITALIC,40));
        
        l_id=new JLabel("User ID    :");
        l_id.setFont(new Font("Times New Roman",Font.BOLD,18));
        l_id.setBounds(135,200,100,30);
       
        tf_id=new JTextField();
        tf_id.setBounds(255, 200, 150, 30);

        
        l_pass=new JLabel("Password  :");
        l_pass.setFont(new Font("Times New Roman",Font.BOLD,18));
        l_pass.setBounds(135,235,100,30);
        
        tf_pass=new JPasswordField();
        tf_pass.setBounds(255, 235, 150, 30);        
        tf_showPass=new JTextField();
        tf_showPass.setBounds(255,235,150,30);
        tf_showPass.setVisible(false);
        
        l_showPassword=new JLabel("Show Password");
        l_showPassword.setBounds(330,267,100,30);
        cb_showPassword=new JCheckBox();
        cb_showPassword.setBounds(310,275,18,15);
        cb_showPassword.setBackground(new Color(102,204,255));
        
        cb_showPassword.addItemListener((e) -> {
            if(cb_showPassword.isSelected()){
                tf_pass.setVisible(false);
                tf_showPass.setVisible(true);
                tf_showPass.setText(String.valueOf(tf_pass.getPassword()));
            }else{
                tf_pass.setVisible(true);
                tf_showPass.setVisible(false);
                tf_pass.setText(tf_showPass.getText());
            }
        });
        
        bu_sub=new JButton("<html><u>Submit</u></html>");
        bu_sub.setBounds(210, 305, 100, 30);
        bu_sub.setBackground(new Color(153,153,255));
        bu_sub.setCursor(Cursor.getPredefinedCursor(12));
        bu_sub.addActionListener(this);
        
        l_reg=new JLabel("Not yet Registered! ,");
        l_reg.setBounds(175, 380, 115, 30);
      
        l_reg1=new JLabel("<html><u>Get Registered</u></html>");
        l_reg1.setBounds(290, 380, 100, 30);
        l_reg1.setCursor(Cursor.getPredefinedCursor(12));
        l_reg1.setForeground(Color.red);
        l_reg1.setToolTipText("Click here to go to Registration Page");
        l_reg1.addMouseListener(this);
        
        l_forId=new JLabel("<html><u>Forget ID</u></html>");
        l_forId.setBounds(175, 430, 200, 30);
        l_forId.setHorizontalAlignment(0);          // 0 : SwingConstant.CENTER
        l_forId.setCursor(Cursor.getPredefinedCursor(12));
        l_forId.setForeground(Color.cyan);
        l_forId.setToolTipText("Click here to recover your Log In ID");
        l_forId.addMouseListener(this);
        
        l_forPass=new JLabel("<html><u>Forget Password</u></html>");
        l_forPass.setBounds(175,460,200, 30);
        l_forPass.setHorizontalAlignment(0); 
        l_forPass.setCursor(Cursor.getPredefinedCursor(12));
        l_forPass.setForeground(Color.CYAN);
        l_forPass.setToolTipText("Click here to recover your Password");
        l_forPass.addMouseListener(this);
        
        addCompo(heading,l_id,l_pass,l_reg,l_reg1,l_forId,l_forPass,tf_id,tf_pass,tf_showPass,bu_sub,cb_showPassword,l_showPassword);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==l_forId)
        {   
            frame.dispose();
            System.out.println("Forget Id Pressed");
            new ForId();
        }
        if(e.getSource()==l_forPass)
        {
            System.out.println("Forget Password pressed");
            frame.dispose();
            new ForPass();
        }
        if(e.getSource()==l_reg1)
        {
            System.out.println("Registration button Pressed");
            frame.dispose();
            new RegisterTerms();
        }   
    }
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu_sub)
        {
            try {
                String id=tf_id.getText();
                String pass=String.valueOf(tf_pass.getPassword());
                System.out.println("id entered is "+id+" password entered is "+pass);
                con=createConnection();
                PreparedStatement ps=con.prepareStatement("SELECT id,name,gender,mobileNumber,balance FROM `users` WHERE id like ? AND password like ?");
                ps.setString(1, id);
                ps.setString(2, pass);
                ResultSet rs=ps.executeQuery();
                boolean status=false;
                while(rs.next())
                {
                    System.out.println(rs.getString(1));
                    User.setId(rs.getString(1)); 
                    User.setName(rs.getString(2));
                    User.setGender(rs.getString(3));
                    User.setNo(rs.getLong(4));
                    User.setBalance(rs.getFloat(5));
                    status=true;
                    break;
                }
                if(status){
                    ps=con.prepareStatement("update users set login_status=1 where id like ?");
                    ps.setString(1, User.getId());
                    int no=ps.executeUpdate();
                    System.out.println("rows updated "+no);
                    frame.dispose();
                    new Home();
                }
                else
                    JOptionPane.showMessageDialog(panel, "Incorrect Username or Password");
            } catch (SQLException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
    
}
