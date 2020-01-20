package Classes;

import SetUp.Address;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.*;

public abstract class MyFrame { 
    
    static JFrame frame;
    JLabel panel;
    public static Connection con;
    static int i;
    public MyFrame(){
        createFrame();
    }
    public abstract void addComponents();
        
    private void createFrame(){
        frame =new JFrame("Money Tracker");
        frame.setBounds(350,75, 550,600);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(3);
        frame.setIconImage(new ImageIcon(Address.sourcePath+"Frame_Icon.jpg").getImage().getScaledInstance(150, 150,Image.SCALE_SMOOTH));
        frame.setResizable(false);
      
        panel=new JLabel();
        panel.setBounds(0,0,frame.getWidth(),frame.getHeight());
        
        frame.add(panel);
        panel.setIcon(new ImageIcon(new ImageIcon("src\\Sources\\back.jpg").getImage().getScaledInstance(panel.getWidth(),panel.getHeight(),Image.SCALE_SMOOTH)));
    }
    
    public void addCompo(Component ...jc){
        for(Component j:jc)
            panel.add(j);
    }
    
    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if(i==0){
                System.out.println("checking and creating database");
                con=DriverManager.getConnection("jdbc:mysql://"+Address.server,Address.username,Address.password); 
                Statement st=con.createStatement();
                st.executeUpdate("create database if not exists moneytracker");
                i++;
            }
            con=DriverManager.getConnection("jdbc:mysql://"+Address.server+"/"+Address.database,Address.username,Address.password);    
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(frame,"Exception : "+ex+"\nConnection not established\nCheck your connection with database and press ok....this will retry connecting to the database");
            System.out.println("retrying to connect");
            i=0;
            con=createConnection();
        }
        System.out.println("connection established");
        return con;
    }
 
}