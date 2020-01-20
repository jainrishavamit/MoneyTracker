/*
    1. skip : check tables created or not 
    2. skip is not synchronized
*/
package Classes;


import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Welcome extends MyFrame implements Runnable{    
    JLabel heading1,heading2;
    boolean continueAnimation=true,flag;
    Thread t=new Thread(this, "Checker");
    JLabel skip = new JLabel("<html><u>SKIP</u></html>");
    
    Welcome(){
           addComponents(); 
    }
    
    public static void main(String[] args) {
        new Welcome();       
    }
    
    @Override
    public void addComponents(){
       Font f=new Font("Chiller",Font.BOLD|Font.ITALIC,70);
       String s="Welcome",s1="";
       
       skip.setBounds(frame.getWidth()-100,frame.getHeight()-100,80,50);
       skip.setFont(new Font("Times New Roman",Font.BOLD,18));
       skip.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       skip.setHorizontalAlignment(SwingConstants.CENTER);
       skip.setVisible(false);
       panel.add(skip);
       
       t.start();
      
       heading1 = new JLabel(s);
       heading1.setBounds(0,135,panel.getWidth(),100);
       heading1.setFont(f);
       heading2 = new JLabel(s1);
       heading2.setBounds(0,235,panel.getWidth(),100);
       heading2.setFont(f);
       heading1.setHorizontalAlignment(SwingConstants.CENTER);
       heading2.setHorizontalAlignment(SwingConstants.CENTER);
       
       skip.addMouseListener(new MouseListener()  {
           @Override
           public void mouseClicked(MouseEvent e) {
               System.out.println("skip is pressed");
               try {
                   t.join();
               } catch (InterruptedException ex) {
                   System.out.println("following exception has occured while waiting for thread : "+ex);
               }
               frame.dispose();
               System.out.println("run has become false");
               continueAnimation=false;
               if(flag==true)
                    new Home();
               else
                    new Login();
           }
           @Override
           public void mousePressed(MouseEvent e) {}
           @Override
           public void mouseReleased(MouseEvent e) {}
           @Override
           public void mouseEntered(MouseEvent e) {}
           @Override
           public void mouseExited(MouseEvent e) {}
       });
       
       panel.add(heading1);
       panel.add(heading2);
       heading1.setVisible(false);
       heading1.setVisible(true);
       
       try{  Thread.sleep(750);   } catch(Exception e){}
       s+=" to";
       heading1.setText(s);

       try{  Thread.sleep(750);   } catch(Exception e){}
       s+=" the";
       heading1.setText(s);
       
       try{  Thread.sleep(750);   } catch(Exception e){}
       s1+="Money";
       heading2.setText(s1);
       
       try{  Thread.sleep(750);   } catch(Exception e){}
       s1+=" Tracker";
       heading2.setText(s1);

       try{  Thread.sleep(750);   } catch(Exception e){}
       heading1.setVisible(false);   heading2.setVisible(false);

       while(continueAnimation)
       {
           try{  Thread.sleep(750);   } catch(Exception e){}
           heading1.setVisible(true);   heading2.setVisible(true);
           try{  Thread.sleep(750);   } catch(Exception e){}
           heading1.setVisible(false);   heading2.setVisible(false);
       }
    }
    
    @Override
    public void run() {
        System.out.println("Checking Tables and login status....");
        flag=checkTablesAndLoginStatus();
        System.out.println("Setting skip enabled");
        skip.setVisible(true);
     }
    
    public boolean checkTablesAndLoginStatus(){
        boolean ret=false;
        boolean isUsersTableCreated=false;
        try {
            con=createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("show tables");
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                if(rs.getString(1).equals("users"))
                {
                    isUsersTableCreated=true;
                    ret=true;
                    System.out.println("user table already exists");
                    break;
                }
            }
            if(!isUsersTableCreated)
            {
                System.out.println("creating User table :");
                st.execute("CREATE TABLE users " +
                                        "(" +
                                        "id VARCHAR(25) NOT NULL  PRIMARY KEY," +
                                        "name VARCHAR(25) NOT NULL , " +
                                        "gender VARCHAR(6) NOT NULL , " +
                                        "mobileNumber BIGINT(10) UNSIGNED NOT NULL , " +
                                        "balance DOUBLE NOT NULL , " +
                                        "question VARCHAR(25) NOT NULL , " +
                                        "answer VARCHAR(25) NOT NULL , " +
                                        "password VARCHAR(25) NOT NULL , " +
                                        "login_status BOOLEAN NOT NULL , " +
                                        "picture LONGBLOB NULL DEFAULT NULL" +
                                        ")"
                                    );
                System.out.println("User table created ");
                System.out.println("inserting values");
                st.executeUpdate("INSERT INTO `users`  VALUES(\"rj1611\", \"Rishav Jain\", \"Male\", 7046222706, 25.5, \"What is your Nick name?\", \"Monu\", 12345, 0,null)");
                st.executeUpdate("INSERT INTO `users`  VALUES(\"rj1612\", \"Virag Jain\", \"Male\", 9924979017, 255.5, \"What is your Nick name?\", \"Sonu\", 12345, 0,null)");
                st.executeUpdate("INSERT INTO `users`  VALUES(\"rj1613\", \"Rekha Jain\", \"Female\", 9411688327, 2555.5, \"What is your Mothers?\", \"Meera\", 12345, 0,null)");
                st.executeUpdate("INSERT INTO `users`  VALUES(\"Default id\", \"Rekha Jain\", \"Female\", 9411688327, 2555.5, \"Mothers native plce?\", \"Meera\", 12345, 0,null)");
                System.out.println("values inserted in User");
            }
            else{
                System.out.println("Checking login status....");
                ret=User.checkLogin();
                System.out.println("login checked");
            }
            
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Income (id varchar(25) not null References Users(id),dod date not null,category varchar(25) not null,Description varchar(25) not null,amount double not null,foreign key (id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE);");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS Expense (id varchar(25) not null References Users(id),dod date not null,category varchar(25) not null,Description varchar(25) not null,amount double not null,foreign key (id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE);");
            st.executeUpdate("CREATE TABLE IF NOT EXISTS categories (id varchar(25),Income vARCHAR (255) default '',Expense varchar(255) DEFAULT '' );");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel,"An error has occured while checking table and login status");
            ret=false;
        }
        return ret;
    } 
}