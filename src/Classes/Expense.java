package Classes;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class Expense extends IncExp{
    
    String defaultCategories="Stationery,Clothing,Travel,Food,";
    public Expense() {
        setHeading("Add Expense");
        if(User.getCategories_e()==(null))
            setCategory(defaultCategories);   
        else
            setCategory(defaultCategories+User.getCategories_e());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu_add)
        {
                if(addExpense()){
                        frame.dispose();
                        new Home();
                }
        }
        if(e.getSource()==bu_addMore)
        {
                if(addExpense())
                    resetExpenseeFrame();
        }
        if(e.getSource()==bu_addCategory)
        {
            String s=JOptionPane.showInputDialog(panel, "Enter new Expense Category to be added :", "Add Expense Category", 1);
            if(s!=null&&!s.equals("")){
                clearCategory();
                User.setCategories_e(s);
                setCategory(defaultCategories+User.getCategories_e());
            }
        }
        if(e.getSource()==bu_cancel){
            frame.dispose();
            new Home();
        }
    }
    
    private boolean checkInputs(){
        if(dp_date.getDate().after(new Date())){
            JOptionPane.showMessageDialog(panel,"No Future date can be used to add Expense");
            return false;
        }
        if(tf_desc.getText().length()>25){
            JOptionPane.showMessageDialog(panel,"Description length more than 25 is not allowed");
            return false;
        }
        try{
            Double d=Double.parseDouble(tf_amount.getText());
            if(d<=0){
                JOptionPane.showMessageDialog(panel,"Invalid Amount, Amount can not be less than or equal to zero");
                return false;
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(panel,"Invalid Amount Format,Please Enter Correct Format for Amount");
            return false;
        }
        return true;
    } 
   
    public boolean addExpense(){
        if(checkInputs()){
               try {
                java.sql.Date sqldate=new java.sql.Date(dp_date.getDate().getTime());
                Double amount=Double.parseDouble(tf_amount.getText());
                
                con=createConnection();
                PreparedStatement ps=con.prepareStatement("insert into expense values (?,?,?,?,?)");
                ps.setString(1,User.getId());
                ps.setDate(2,sqldate);
                ps.setString(3,cb_category.getSelectedItem().toString());
                ps.setString(4, tf_desc.getText());
                ps.setDouble(5,amount );
                
                int i=ps.executeUpdate();
                
                User.setBalance(User.getBalance()-amount);
                
                JOptionPane.showMessageDialog(panel, "Expense has been added succesfully");
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "An error has occured while adding Expense");
                   System.out.println(ex.toString());
            } 
        }
        return false;
    }
    public void resetExpenseeFrame(){
        System.out.println("resetting Frame");
        tf_amount.setText("");
        tf_desc.setText("");
    }
    
    public static void main(String[] args) {
        new Expense();
    }
}