package Classes;

import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

public class Income extends IncExp{
    
    String defaultCategories="Salary,Interest & Dividend,";
    
    public Income() {
        setHeading("Add Income");
        if(User.getCategories_i()==(null))
            setCategory(defaultCategories);   
        else
            setCategory(defaultCategories+User.getCategories_i());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==bu_add)
        {
                if(addIncome()){
                    frame.dispose();
                    new Home();
                }
        }
        if(e.getSource()==bu_addMore)
        {
                if(addIncome())
                    resetIncomeFrame();
        }
        if(e.getSource()==bu_addCategory)
        {
                String s=JOptionPane.showInputDialog(panel, "Enter new Income Category to be added :", "Add Income Category", 1);
                if(s!=null&&!s.equals("")){
                        clearCategory();
                        User.setCategories_i(s);
                        setCategory(defaultCategories+User.getCategories_i());
                }
        }
        if(e.getSource()==bu_cancel){
                frame.dispose();
                new Home();
        }
    }
    
    private boolean checkInputs(){
        if(dp_date.getDate().after(new Date())){
            JOptionPane.showMessageDialog(panel,"No Future date can be used to add Income");
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
    
    public boolean addIncome(){
        if(checkInputs()){
               try {
                java.sql.Date sqldate=new java.sql.Date(dp_date.getDate().getTime());
                Double amount=Double.parseDouble(tf_amount.getText());
                
                con=createConnection();
                PreparedStatement ps=con.prepareStatement("insert into income values (?,?,?,?,?)");
                ps.setString(1,User.getId());
                ps.setDate(2,sqldate);
                ps.setString(3,cb_category.getSelectedItem().toString());
                ps.setString(4, tf_desc.getText());
                ps.setDouble(5, amount);
                System.out.println("************");
                ps.executeUpdate();
                   System.out.println("************");
                User.setBalance(User.getBalance()+amount);
                
                JOptionPane.showMessageDialog(panel, "Income has been added succesfully");
                return true;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(panel, "An error has occured while adding Income");
            } 
        }     
        return false;
    }
    
    public void resetIncomeFrame(){
        System.out.println("resetting Frame");
        tf_amount.setText("");
        tf_desc.setText("");
    }
    
    public static void main(String[] args) {
        new Income();
    }
    
}
