package Classes;

import SetUp.Address;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Profile extends MyFrame implements ActionListener,MouseListener{
    JLabel lb_proPic,lb_id,lb_clear,lb_name,lb_nameVal,lb_gender,lb_genderVal,lb_mobile,lb_mobileVal,lb_chngPass,lb_deletAC;
    JButton bu_chngPic,bu_back;
    Profile(){
        System.out.println("jkbbjjkhjdhjkdhjdhj");
        addComponents();
    }

    @Override
    public void addComponents(){
        byte lb=127;
        Font f=new Font("Times New Roman",2,20);
        
        lb_proPic=new JLabel();
        lb_proPic.setBounds(245,45,60,80);
        if(User.getImage()==null){
            String path="";
            if(User.getGender().equals("Male"))
                 path=Address.sourcePath+"male.png";
            else
                path=Address.sourcePath+"female.png";
                ImageIcon icon=new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(lb_proPic.getWidth(),lb_proPic.getHeight(),Image.SCALE_SMOOTH));
                lb_proPic.setIcon(icon);
        }
        else{
            ImageIcon icon=new ImageIcon(new ImageIcon(User.getImage()).getImage().getScaledInstance(lb_proPic.getWidth(),lb_proPic.getHeight(),Image.SCALE_SMOOTH));
            lb_proPic.setIcon(icon);
        }
        lb_proPic.setToolTipText("Click here to change Profile Picture");
        lb_proPic.setCursor(new Cursor(12));
        lb_proPic.addMouseListener(this);
        
        bu_chngPic=new JButton("Change Picture");
        bu_chngPic.setBounds(227,127,95,15);
        bu_chngPic.setBackground(new Color(153,153,255));
        bu_chngPic.setFont(new Font("Times New Roman", Font.PLAIN,10));
        bu_chngPic.setCursor(new Cursor(12));
        bu_chngPic.setToolTipText("Click here to change Profile Picture");
        bu_chngPic.addMouseListener(this);
        
        lb_id=new JLabel(User.getId());
        lb_id.setBounds(0,160,550,40);
        lb_id.setFont(new Font("Bradley Hand ITC",Font.ITALIC|Font.BOLD,35));
        lb_id.setHorizontalAlignment(0);
        lb_id.setVerticalAlignment(0);
        lb_id.setForeground(Color.DARK_GRAY);
        
        lb_name=new JLabel("Name");
        lb_name.setBounds(lb-20,250,100,30);
        lb_name.setFont(f);
        
        lb_nameVal=new JLabel(User.getName());
        lb_nameVal.setBounds(lb+150,250,550-(lb+150),30);
        lb_nameVal.setFont(f);

        
        lb_gender=new JLabel("Gender");
        lb_gender.setBounds(lb-20,300,100,30);
        lb_gender.setFont(f);
        
        lb_genderVal=new JLabel(User.getGender());
        lb_genderVal.setBounds(lb+150,300,100,30);
        lb_genderVal.setFont(f);
        
        lb_mobile=new JLabel("Mobile Number");
        lb_mobile.setBounds(lb-20,350,130,30);
        lb_mobile.setFont(f);

        lb_mobileVal=new JLabel(String.valueOf(User.getNo()));
        lb_mobileVal.setBounds(lb+150,350,100,30);
        lb_mobileVal.setFont(f);

        lb_clear=new JLabel("<HTML><u>Click here to clear all record</u></html>");
        lb_clear.setBounds(0, 425, 550, 30);
        lb_clear.setFont(f);
        lb_clear.setCursor(new Cursor(12));
        lb_clear.setHorizontalAlignment(0);
        lb_clear.setForeground(Color.BLUE);
        lb_clear.addMouseListener(this);
        
        lb_chngPass=new JLabel("<HTML><u>Click here to change your Password</u></html>");
        lb_chngPass.setBounds(0, 460, 550, 30);
        lb_chngPass.setFont(f);
        lb_chngPass.setCursor(new Cursor(12));
        lb_chngPass.setHorizontalAlignment(0);
        lb_chngPass.setForeground(Color.BLUE);
        lb_chngPass.addMouseListener(this);

        lb_deletAC=new JLabel("<HTML><u>Click here to delete Your Account</u></html>");
        lb_deletAC.setBounds(0, 495, 550, 30);
        lb_deletAC.setFont(f);
        lb_deletAC.setCursor(new Cursor(12));
        lb_deletAC.setHorizontalAlignment(0);
        lb_deletAC.setForeground(Color.BLUE);
        lb_deletAC.addMouseListener(this);
        
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_back.setBounds(410, 530, 100, 30);
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_back.setToolTipText("Click here to go back to Home");
        bu_back.setBackground(new Color(153,153,255));
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        
        addCompo(lb_proPic,bu_chngPic,lb_clear,lb_id,lb_name,lb_nameVal,lb_gender,lb_genderVal,lb_mobile,lb_mobileVal,bu_back,lb_chngPass,lb_deletAC);
    }
    
    public static void main(String[] args) {
        new Profile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu_back)
        {
            frame.dispose();
            new Home();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==lb_chngPass)
        {
            frame.dispose();
            new ChangePass();
        }
        if(e.getSource()==lb_proPic||e.getSource()==bu_chngPic)
        {
            try {
                changePic();
            } catch (FileNotFoundException ex) {
            }
        }
        if(e.getSource()==lb_clear)
        {
            try{
            con=createConnection();
            Statement st=con.createStatement();
            st.execute("delete from income where id = '"+User.getId()+"'");
            st.execute("delete from expense where id = '"+User.getId()+"'");
            st.execute("update users set balance = 0 where id='"+User.getId()+"'");
            st.execute("delete from categories where id = '"+User.getId()+"'");
            JOptionPane.showMessageDialog(panel, "All records cleared succesfuuly\n Your new balance is Rs. 0");
            }catch(SQLException ex){
                
            JOptionPane.showMessageDialog(panel, "Error occured while clearing the records"+ex);
            }
        }
        if(e.getSource()==lb_deletAC)
        {
            int i=JOptionPane.showConfirmDialog(panel, "Do you really want to Delete your account ?\n\nAfter deleting your account you will no longer\nbe able to access your data.\n  ","Account Deletion Confirmation",JOptionPane.YES_NO_OPTION);
            if (i==0) {
                try{
                    con=createConnection();
                    Statement st=con.createStatement();
                    int ret=st.executeUpdate("delete from income where id = \""+User.getId()+"\"");
                    ret=st.executeUpdate("delete from expense where id = \""+User.getId()+"\"");
                    ret=st.executeUpdate("delete from categories where id = \""+User.getId()+"\"");
                    ret=st.executeUpdate("delete from users where id = \""+User.getId()+"\"");
                    JOptionPane.showMessageDialog(panel, "Your Account is deleted succesfully...\n Thank You for using Money Tracker");
                    frame.dispose();
                    new Login();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(panel, "Exception Occured while deleting account\nException : "+ex);
                }
            }
            else
                JOptionPane.showMessageDialog(panel, "Your account is not deleted as per your Confirmation");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    public void changePic() throws FileNotFoundException{
        System.out.println("Profile pic is being changed");
        JFileChooser dialog=new JFileChooser();
        FileNameExtensionFilter filter=new FileNameExtensionFilter("Images.*","jpg","gif","png","jpeg","bmp");
        dialog.setFileFilter(filter);
        dialog.setAcceptAllFileFilterUsed(false);
        int i=dialog.showSaveDialog(panel);
        if(i==0)
        {
            File f=dialog.getSelectedFile();
            System.out.println("Selected File Location is : "+f);
            String s=f.getAbsolutePath();
            System.out.println("Selected File Location is : "+s);
            if(f.exists()){
                     lb_proPic.setIcon(new ImageIcon(new ImageIcon(s).getImage().getScaledInstance(lb_proPic.getWidth(), lb_proPic.getHeight(),Image.SCALE_SMOOTH)));
                     User.setImage(s);
            }
        }
    }
}
