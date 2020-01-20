
package Classes;

import static Classes.MyFrame.con;
import static Classes.MyFrame.createConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class User {
    static private String id;
    static private String name;
    static private double balance;
    static private String securityQuestion;
    static private String answer;
    static private long no;
    static private String gender;
    static private byte[] image;
    static private byte login_status;
    static private String categories_e,categories_i;
    
    public static String getId() {
        if(id!=null)
            return id;
        return "Default id";
    }

    public static String getId(Long no) {
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select id from users where mobileNumber = ?");
            ps.setLong(1,no);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                id=rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching id with number : "+ex);
        }
        return id;
    }
    
    public static String getName() {
        if(name!=null)
            return name;
        return "Default Name";
    }

    public static double getBalance() {
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select balance from users where id like ?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                balance=rs.getDouble(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching balance : "+ex);
        }
        return balance;
    }

    public static String getSecurityQuestion(String id) {
      try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select question from users where id like ?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                securityQuestion=rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching question : "+ex);
        }
        return securityQuestion;
    }

    public static String getSecurityQuestion(long no) {
        securityQuestion="";
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select question from users where mobileNumber = ?");
            ps.setLong(1,no);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                securityQuestion=rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching question : "+ex);
        }
        return securityQuestion;
    }
    
    public static String getAnswer(String id) {
        answer="";
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select answer from users where id like ?");
            ps.setString(1,id);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                answer=rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching answer : "+ex);
        }
        return answer;
    }

    public static String getAnswer(long no) {
        answer="";
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("select answer from users where mobileNumber = ?");
            ps.setLong(1,no);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                answer=rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Exception has occured while fetching answer : "+ex);
        }
        return answer;
    }

    public static long getNo() {
        return no;
    }

    public static String getGender() {
        if(gender!=null)
            return gender;
        return "Default Male";
    }

    public static byte[] getImage() {
        try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select picture from users where id =\""+getId()+"\"");
            while(rs.next()){
                return rs.getBytes(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while Fetching Image\nException : "+ex);
        }
        return null;
    }

    public static String getCategories_e() {
    try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select expense from categories where id  =\""+getId()+"\"");
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while fetching expense\nException : "+ex);
        }
        return categories_e;
    }

    public static String getCategories_i() {
        try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select income from categories where id  =\""+getId()+"\"");
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while Fetching income\nException : "+ex);
        }
        return categories_i;
    }

    public static String getPassword(){
        try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select password from users where id =\""+getId()+"\"");
            while(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while Fetching old password\nException : "+ex);
        }
        return "";
    }
    public static void clear()      //to be called when logout
    {
        id=name=securityQuestion=answer=gender=null;
        no=0l;
        balance=0.0f;
    }

    public static void setId(String id) {
        User.id = id;
    }

    public static void setName(String name) {
        User.name = name;
    }

    public static void setNo(long no) {
        User.no = no;
    }

    public static void setGender(String gender) {
        User.gender = gender;
    }

    public static void setBalance(double balance) {
        try{
            con=createConnection();
            PreparedStatement ps=con.prepareStatement("Update users set balance=? where id like ?");
            ps.setDouble(1, balance);
            ps.setString(2, id);
            ps.executeUpdate();
            
            User.balance = balance;
        } catch (SQLException ex) {
            System.out.println("Error occured while updating balance "+ex);
        }
        
    }
    
    public static void setPassword(String pass){
        try{
            con=createConnection();
            Statement st = con.createStatement();
            st.executeUpdate("update users set password = \""+pass+"\" where id = \""+getId()+"\"");
    }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception occured while Updating Password\nException : "+ex);
        }
    }

    public static void setImage(String path) throws FileNotFoundException {
        InputStream im=new FileInputStream(new File(path));
        try{
            con=createConnection();
            PreparedStatement st = con.prepareStatement("update users set picture = ? where id = ?");
            st.setBlob(1, im);
            st.setString(2, getId());
            st.executeUpdate();
    }   catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception occured while Updating Image\nException : "+ex);
        }
        User.image = getImage();
    }

    public static void setLogin_status(byte login_status) {
        User.login_status = login_status;
    }

    public static void setCategories_e(String categories_e) {
        try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select id,expense from categories where id =\""+getId()+"\"");
            while(rs.next()){
                st.executeUpdate("update categories set expense = \""+rs.getString(2)+categories_e+",\" where id = \""+getId()+"\"");
                return ;
            }
            st.executeUpdate("Insert into categories(Id,Expense) values (\""+getId()+"\",\""+categories_e+",\")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while updating expense categories\nException : "+ex);
        }
    }

    public static void setCategories_i(String categories_i) {
         try{
            con = createConnection();
            Statement st=con.createStatement();
            ResultSet rs = st.executeQuery("select id,income from categories where id =\""+getId()+"\"");
            while(rs.next()){
                st.executeUpdate("update categories set income = \""+rs.getString(2)+categories_i+",\" where id = \""+getId()+"\"");
                return ;
            }
            st.executeUpdate("Insert into categories(id,income) values (\""+getId()+"\",\""+categories_i+",\")");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Exception has occured while updating income categories\nException : "+ex);
        }
    }
    
    
    
    public static boolean checkLogin(){ 
        boolean flag=false;
        try {
            con=createConnection();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("SELECT id,name,gender,mobileNumber,balance FROM `users` WHERE `login_status` = 1 ");
            while(rs.next())
            {
                System.out.println(rs.getString(1));
                id=rs.getString(1);                                    //add code to initialize the values of id,name,gender,balance,no;
                name=rs.getString(2);
                gender=rs.getString(3);
                no=rs.getLong(4);
                balance=rs.getDouble(5);
                flag=true;
                break;
            }
            System.out.println("login status  = "+flag);
        } catch (SQLException ex) {
            System.out.println("exception has occured while checking tables "+ ex);
        }
        return flag;
    }
}