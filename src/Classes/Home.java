
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
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Home extends MyFrame implements Runnable,ActionListener{
    JPanel panel2;
    JLabel l_head1,l_head2,l_time,l_img,l_set,l_pro,l_summary,l_head;  
    JButton bu_income,bu_expense,bu_details;
    JComboBox cb_opt;
    JScrollPane sp_table;
    DefaultTableModel tableModel;
    
    Thread time=new Thread(this,"Time");
    Thread img=new Thread(this,"Image");
    Thread table=new Thread(this,"Table");
    String Id;
    public Home() {
        addComponents();
    }
    
    @Override
    public void addComponents() {
        
        time.start();       //thread to show time
        img.start();        //thread to show image
        table.start();
        
        l_head=new JLabel("Money Tracker");
        l_head.setBounds(60,50,390,70);
        l_head.setHorizontalAlignment(0);
        l_head.setForeground(Color.darkGray);
        l_head.setFont(new Font("Monotype Corsiva",Font.ITALIC,60));
        
        l_head1=new JLabel("<html>Hey, <b>"+User.getName()+"</b>,</html>");
        l_head1.setBounds(50,145,300,30);
        l_head1.setFont(new Font("Monotype Cursiva",Font.PLAIN,18));
        l_head1.setForeground(Color.darkGray);
        
        l_head2=new JLabel("<html>Your current balance is <b>Rs. "+User.getBalance()+"<b></html>");
        l_head2.setBounds(75,175,300,30);
        l_head2.setFont(new Font("Monotype Cursiva",Font.PLAIN,18));
        l_head2.setForeground(Color.darkGray);
             
        bu_income=new JButton("<html><u>Add Income</u></html>");
        bu_income.setBounds(64, 480, 125, 30);
        bu_income.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_income.setToolTipText("Click here to Add Income");
        bu_income.setBackground(new Color(153,153,255));
        bu_income.setMnemonic(KeyEvent.VK_I);
        bu_income.addActionListener(this);
        
        bu_expense=new JButton("<html><u>Add Expense</u></html>");
        bu_expense.setBounds(213,480, 125, 30);
        bu_expense.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_expense.setToolTipText("Click here to Add Expensee");
        bu_expense.setBackground(new Color(153,153,255));
        bu_expense.setMnemonic(KeyEvent.VK_E);
        bu_expense.addActionListener(this);
        
        bu_details=new JButton("<html><u>View Details</u></html>");
        bu_details.setBounds(362,480, 125, 30);
        bu_details.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_details.setToolTipText("Click here to View Summary");
        bu_details.setBackground(new Color(153,153,255));
        bu_details.setMnemonic(KeyEvent.VK_S);
        bu_details.addActionListener(this);
        
        addCompo(l_head1,l_head2,bu_income,bu_expense,bu_details,l_head);
    } 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cb_opt){
                    if(cb_opt.getSelectedItem()=="View Profile"){
                        System.out.println("View profile Selected");
                        frame.dispose();
                        new Profile();
                    }
                    if(cb_opt.getSelectedItem()=="Home"){
                        System.out.println("Home selected");
                    }
                    if(cb_opt.getSelectedItem()=="Log out"){
                        int i=JOptionPane.showConfirmDialog(panel,"Are you sure to Log out ?","Log out Confirmation",JOptionPane.YES_NO_OPTION);
                        if(i==0){
                            try {
                                con=createConnection();
                                Statement st=con.createStatement();
                                st.executeUpdate("UPDATE users SET login_status=0 WHERE id like \""+User.getId()+"\"");
                                User.clear();
                                frame.dispose();
                                new Login();
                            } catch (SQLException ex) {
                                cb_opt.setSelectedIndex(0);
                                JOptionPane.showMessageDialog(panel, "An error has occured while logging out");
                            }
                        }
                        else
                            cb_opt.setSelectedIndex(0);
                    }
         }
        if(e.getSource()==bu_income){
                frame.dispose();
                new Income();
        }
        if(e.getSource()==bu_expense){
                frame.dispose();
                new Expense();
        }
        if(e.getSource()==bu_details){
                frame.dispose();
                new Details();
        }
    }
    
    @Override
    public void run() {
        if(Thread.currentThread()==time)
            showTime();     //This thread runs to infinity , to add any other functionality use if statements to run other thread
        else if(Thread.currentThread()==img)
            showImage();
        else
            showSummary();
    }

    public void showTime() {
        GregorianCalendar gc=new GregorianCalendar();
        l_time=new JLabel();
        l_time.setBounds(panel.getWidth()-100,panel.getHeight()-70,70, 30);
        l_time.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(l_time);
        for(;;){
            gc.setTime(new GregorianCalendar().getTime());
            String am_pm=gc.get(Calendar.AM_PM)==0?"AM":"PM";
            String time="<html>"+gc.get(Calendar.DATE)
                              +"/"+gc.get(Calendar.MONTH)
                              +"/"+gc.get(Calendar.YEAR)
                              +"<br>"+gc.get(Calendar.HOUR)
                              +":"+gc.get(Calendar.MINUTE)
                              +":"+gc.get(Calendar.SECOND)
                              +" "+am_pm
                        +"</html>";
            l_time.setText(time);
            
            try{  Thread.sleep(1000);   }catch(InterruptedException e){}

        }
    }
    
    public void showImage(){
        System.out.println("Image is shown by thread : "+Thread.currentThread().getName());
        l_img=new JLabel();
        l_img.setBounds(470,10,60,80);
        panel.add(l_img);
        if(User.getImage()==null){
            String path="";
            if(User.getGender().equals("Male"))
                 path=Address.sourcePath+"male.png";
            else
                path=Address.sourcePath+"female.png";
            ImageIcon icon=new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(l_img.getWidth(),l_img.getHeight(),Image.SCALE_SMOOTH));
            l_img.setIcon(icon);
        }
        else{
            ImageIcon icon=new ImageIcon(new ImageIcon(User.getImage()).getImage().getScaledInstance(l_img.getWidth(),l_img.getHeight(),Image.SCALE_SMOOTH));
            l_img.setIcon(icon);
        }
        
        cb_opt=new JComboBox();
        cb_opt.setBounds(460,93,80,20);
        panel.add(cb_opt);
        cb_opt.addItem("Home");
        cb_opt.addItem("View Profile");
        cb_opt.addItem("Log out");
        cb_opt.setBackground(new Color(153,153,255));
        cb_opt.setCursor(new Cursor(12));
        cb_opt.addActionListener(this);
        
        try{  Thread.sleep(5000);   }catch(InterruptedException e){}
    }
    
    public void showSummary(){
        l_summary=new JLabel("<html><u>Summary</u></html>");
        l_summary.setBounds(0, 220, 550, 40);
        l_summary.setHorizontalAlignment(0);
        l_summary.setFont(new Font("Monotype Corsiva",Font.ITALIC,40));
        panel.add(l_summary);
               
        panel2=new JPanel();
        panel2.setBounds(50, 270, 450,170);
        panel2.setBackground(new Color(153,153,255));
        panel2.setLayout(null);
        panel.add(panel2);
        
        Object rowData[][]=new Object[3][4];
        rowData[0][0]="<html><b>Today"; 
        rowData[1][0]="<html><b>Last 7 Days";
        rowData[2][0]="<html><b>Last 30 days" ;
        
        rowData[0][1]=addIncome((byte)0);
        rowData[1][1]=addIncome((byte)6);
        rowData[2][1]=addIncome((byte)29);
        
        rowData[0][2]=addExpense((byte)0);
        rowData[1][2]=addExpense((byte)6);
        rowData[2][2]=addExpense((byte)29);
        
        rowData[0][3]=(Double)rowData[0][1]-(Double)rowData[0][2];
        rowData[1][3]=(Double)rowData[1][1]-(Double)rowData[1][2];
        rowData[2][3]=(Double)rowData[2][1]-(Double)rowData[2][2];
        String columnNames[]={  "",
                                "<html><b>Income</b></html>",
                                "<html><b>Expense</b></html>",
                                "<html><b>Net Effect</b></html>"
                            };
        Class[] columnClassTypes = new Class [] { String.class, Double.class, Double.class, Double.class };
        
        tableModel=new DefaultTableModel(columnNames,0){
            @Override
            public Class getColumnClass(int columnIndex) {
                return columnClassTypes [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };
        
        JTable table=new JTable(tableModel);
        table.setRowSelectionAllowed(false);
        table.setRowHeight(35);
        for(Object[] row:rowData)
            tableModel.addRow(row);  
        
        sp_table=new JScrollPane(table);
        sp_table.setBounds(20, 20,410, 128);
        panel2.add(sp_table);    
    }
 
    private Double addIncome(byte days){
        try{
            con=createConnection();
            PreparedStatement st=con.prepareStatement("select sum(amount) from income where id = ?  and dod Between ? and ?");
            st.setString(1,User.getId());
            st.setDate(2, new java.sql.Date(new Date().getTime()-(1000l*60*60*24*days)));
            st.setDate(3, new java.sql.Date(new Date().getTime()));
            ResultSet rs=st.executeQuery();
            rs.next();
            return rs.getDouble(1);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Exception has occured while adding income\nException : "+ex);
        }
        return 0.0;
    }
    
    private Double addExpense(byte days){
        try{
            con=createConnection();
            PreparedStatement st=con.prepareStatement("select sum(amount) from expense where id = ?  and dod Between ? and ?");
            st.setString(1,User.getId());
            st.setDate(2, new java.sql.Date(new Date().getTime()-(1000l*60*60*24*days)));
            st.setDate(3, new java.sql.Date(new Date().getTime()));
            ResultSet rs=st.executeQuery();
            rs.next();
            return rs.getDouble(1);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(panel, "Exception has occured while adding Expense\nException : "+ex);
        }
        return 0.0;
    }
    
    public static void main(String[] args) {
        new Home();
    }
}