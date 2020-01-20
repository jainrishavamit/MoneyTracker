package Classes;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

public class Details extends MyFrame implements ActionListener,Runnable
        
{
    JLabel l_head,l_subHead,l_duration,l_from,l_to,l_type,l_sort,l_fetch;
    JButton bu_back,bu_fetch;
    JRadioButton rb_income,rb_expense,rb_both;
    JPanel panel2;
    ButtonGroup bg_type;
    JXDatePicker dp_from,dp_to;
    JComboBox cb_sort;
    SimpleDateFormat dateFormat;
    DefaultTableModel tableModel;
    JTable table;
    JScrollPane sp_table;
    Thread th_panel2=new Thread(this);
    Details(){
        th_panel2.start();
        addComponents();
    }
    
    @Override
    public void addComponents(){
        byte lb=60;
        Font f=new Font("Times New Roman",Font.ITALIC,20);
        dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        
        l_head=new JLabel("Money Tracker");
        l_head.setBounds(0,20,550,50);
        l_head.setForeground(Color.darkGray);
        l_head.setHorizontalAlignment(0);
        l_head.setVerticalAlignment(SwingConstants.BOTTOM);
        l_head.setFont(new Font("Monotype Corsiva",Font.BOLD|Font.ITALIC,50));
      
        l_subHead=new JLabel("<html><u>View Details</u> :</html>");
        l_subHead.setBounds(20,100,250,35);
        l_subHead.setFont(new Font("Monotype Cursiva",Font.ITALIC,30));
        l_subHead.setForeground(Color.darkGray);
        
        l_duration=new JLabel("Duration :");
        l_duration.setBounds(lb,175,100,30);
        l_duration.setFont(f);
        
        l_from=new JLabel("From");
        l_from.setBounds(lb+120,175,50,30);
        l_from.setFont(f);
        
        dp_from=new JXDatePicker(new Date());
        dp_from.setBounds(lb+180, 175,110, 30);
        dp_from.setFormats(dateFormat);
        
        l_to=new JLabel("To");
        l_to.setBounds(lb+310,175,30,30);
        l_to.setFont(f);
        
        dp_to=new JXDatePicker(new Date());
        dp_to.setBounds(lb+350,175,110,30);
        dp_to.setFormats(dateFormat);
        
        l_type=new JLabel("Type        :");
        l_type.setBounds(lb,215,100,30);
        l_type.setFont(f);
        
        rb_income=new JRadioButton("Income");
        rb_income.setBounds(lb+120,215,100,30);
        rb_income.setBackground(new Color(153,153,255));
        rb_expense=new JRadioButton("Expense");
        rb_expense.setBounds(lb+240,215,100,30);
        rb_expense.setBackground(new Color(153,153,255));
        rb_both=new JRadioButton("Both",true);
        rb_both.setBounds(lb+360,215,100,30);
        rb_both.setBackground(new Color(153,153,255));
        bg_type=new ButtonGroup();
        bg_type.add(rb_both);
        bg_type.add(rb_expense);
        bg_type.add(rb_income);
        
        l_sort=new JLabel("Sort by    :");
        l_sort.setBounds(lb,255,100,30);
        l_sort.setFont(f);
        
        cb_sort=new JComboBox(new String[]{"Date Ascending",
                                           "Date Descending",
                                           "Amount Ascending",
                                           "Amount Descendinng"
                                           });
        cb_sort.setBounds(lb+120,255,150,30);
        cb_sort.setBackground(new Color(153,153,255));
        
        bu_fetch=new JButton("Fetch Details");
        bu_fetch.setBounds(200, 290, 150, 30);
        bu_fetch.setCursor(new Cursor(12));
        bu_fetch.setBackground(new Color(153,153,255));
        bu_fetch.setToolTipText("Click here to fetch Details");
        bu_fetch.addActionListener(this);
        
        bu_back=new JButton("<html><u>Go Back</u></html>");
        bu_back.setBounds(410, 530, 100, 30);
        bu_back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bu_back.setToolTipText("Click here to go back to Home");
        bu_back.setBackground(new Color(153,153,255));
        bu_back.setMnemonic(KeyEvent.VK_BACK_SPACE);
        bu_back.setActionCommand("back");
        bu_back.addActionListener(this);
        
        addCompo(l_head,l_subHead,l_duration,bu_back,l_from,l_to,dp_from,dp_to,l_type,
                rb_expense,rb_income,rb_both,l_sort,cb_sort,bu_fetch);
    }
    
    public void addPanel()
    {
        panel2=new JPanel(null);
        panel2.setBounds(20, 335, 510, 185);
        panel2.setBackground(new Color(153,153,255));
        addCompo(panel2);
        
        l_fetch=new JLabel("<html>Click on the Button to fetch Details</html>");
        l_fetch.setBounds(0,0,510,180);
        l_fetch.setForeground(Color.darkGray);
        l_fetch.setFont(new Font("Chiller",Font.ITALIC,40));
        l_fetch.setHorizontalAlignment(0);
        l_fetch.setVerticalAlignment(0);
        
        String columnNames[]=new String[]{"Date","Type","Category","Description","Amount"};
        tableModel=new DefaultTableModel(columnNames, 0){
            @Override
            public boolean isCellEditable(int a,int b)
                {
                    return false;
                }
            public Class getColumnClass(int columnIndex) {
                Class[] types =new Class[]{String.class,String.class,String.class,String.class,Float.class};
                return types [columnIndex];
            }
        };
        
        table =new JTable(tableModel);
        
        table.getColumnModel().getColumn(0).setMinWidth(15);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setMinWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setMaxWidth(70);
        table.getColumnModel().getColumn(2).setMinWidth(15);
        table.getColumnModel().getColumn(2).setPreferredWidth(90);
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(3).setMinWidth(15);  //no preferred width.....it will automatically occupy the remaining space
        table.getColumnModel().getColumn(4).setMinWidth(15);
        table.getColumnModel().getColumn(4).setPreferredWidth(85);
        table.getColumnModel().getColumn(4).setMaxWidth(100);
        
        
        sp_table=new JScrollPane();
        sp_table.setViewportView(table);
        sp_table.setBounds(20,20,470,140);
        
        l_fetch.setVisible(true);
        sp_table.setVisible(false);
        panel2.add(l_fetch);
        panel2.add(sp_table);
    }
    
    @Override
    public void run(){
        addPanel();
    }
    
    private boolean checkInputs(){
        if(dp_from.getDate()==null){
        JOptionPane.showMessageDialog(panel,"From date is required");   
        return false;
        }
        if(dp_to.getDate()==null){
        JOptionPane.showMessageDialog(panel,"To date is required");   
        return false;
        }
        if(dp_from.getDate().after((new Date()))||dp_to.getDate().after((new Date()))){
        JOptionPane.showMessageDialog(panel,"Future dates are not allowed in From or To Date");   
        return false;
        }
        if(dp_from.getDate().after((dp_to.getDate()))){
        JOptionPane.showMessageDialog(panel,"From date can not be after To date");   
        return false;
        }
        return true;
    }
    
    private boolean fetchTableData(){
        String query="",sort ="",type="";
        boolean ret=false;
        if(cb_sort.getSelectedItem().equals("Date Ascending")){
            sort = "dod";
            System.out.println("Date Ascending");
        }
        else if(cb_sort.getSelectedItem().equals("Date Descending")){
            sort = "dod DESC";

            System.out.println("Date ugvcending");
        }
        else if(cb_sort.getSelectedItem().equals("Amount Ascending"))
            sort="amount";
        else        
            sort="amount DESC";

        if(rb_income.isSelected()){
            type="Income";
            query="select dod,category,description,amount from income where id = ? && dod between ? and ?  order by "+sort;
        }
        else if(rb_expense.isSelected()){
            type="expense";
            query="select dod,category,description,amount from expense where id = ? && dod between ? and ?  order by "+sort;
        }
        else{
            System.out.println("display function call made");
            return displayBoth(sort);
        }
        try {
            con=createConnection();
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,User.getId());
            java.sql.Date sqlDate=new java.sql.Date(dp_from.getDate().getTime());
            ps.setDate(2, sqlDate );
            sqlDate.setTime(dp_to.getDate().getTime()); 
            ps.setDate(3, sqlDate);
            System.out.println("value od sort is "+sort);
           // ps.setString(4,sort);
            ResultSet rs=ps.executeQuery();
            boolean hasValues=false;
            tableModel.setRowCount(0);
            while(rs.next())
            {
                hasValues=true;
                String date=rs.getDate(1).toString();
                String cat=rs.getString(2);
                String desc=rs.getString(3);
                Double amt=rs.getDouble(4);
                tableModel.addRow(new Object[]{date,type,cat,desc,amt});
            }
            if(hasValues)
                return true;
            l_fetch.setText("No record Found For your fetched Query");
        } catch (SQLException ex) {
            System.out.println("Exception occured while fetching data for table\nException : "+ex);
            return false;
        }
        return false;
    }
    
    private boolean displayBoth(String sort){
        System.out.println("display function called");
        try{
            con=createConnection();
            System.out.println("*********************");
            Statement st=con.createStatement();
            int executeUpdate = st.executeUpdate("CREATE TABLE IF NOT EXISTS bothTab(dod date not null,type varchar(7) not null ,category varchar(25) not null,Description varchar(25) not null,amount double not null);");
            PreparedStatement ps;
            ps=con.prepareStatement("select * from income where id like ? and dod between ? and ?");
            ps.setString(1,User.getId());
            ps.setDate(2, new java.sql.Date(dp_from.getDate().getTime()));
            ps.setDate(3,  new java.sql.Date(dp_to.getDate().getTime()));
            ResultSet rs=ps.executeQuery();
            while(rs.next())
            {
                ps=con.prepareStatement("insert into bothTab values(?,?,?,?,?)");
                ps.setDate(1, rs.getDate(2));
                ps.setString(2,"Income");
                ps.setString(3,rs.getString(3));
                ps.setString(4,rs.getString(4));
                ps.setDouble(5,rs.getDouble(5));
                ps.executeUpdate();
            }
            
            ps=con.prepareStatement("select * from expense where id like ? and dod between ? and ?");
            ps.setString(1,User.getId());
            ps.setDate(2, new java.sql.Date(dp_from.getDate().getTime()));
            ps.setDate(3,  new java.sql.Date(dp_to.getDate().getTime()));
            rs=ps.executeQuery();
            while(rs.next())
            {
                ps=con.prepareStatement("insert into bothTab values(?,?,?,?,?)");
                ps.setDate(1, rs.getDate(2));
                ps.setString(2,"Expense");
                ps.setString(3,rs.getString(3));
                ps.setString(4,rs.getString(4));
                ps.setDouble(5,rs.getDouble(5));
                ps.executeUpdate();
            }
            
            rs=st.executeQuery("select * from bothTab order by "+sort);
            tableModel.setRowCount(0);
            boolean hasValues=false;
            while(rs.next())
            {
                hasValues=true;
                String date=rs.getDate(1).toString();
                String type=rs.getString(2);
                String cat=rs.getString(3);
                String desc=rs.getString(4);
                Double amt=rs.getDouble(5);
                tableModel.addRow(new Object[]{date,type,cat,desc,amt});
            }
            st.executeUpdate("drop table bothTab");
            if(hasValues)
                return true;
            l_fetch.setText("No record Found For your fetched Query");
        }catch(SQLException ex){
            System.out.println("Exception occured while fetching data for table\nException : "+ex);
        }
        return false;
    }
    
    public static void main(String[] args) {
        new Details();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bu_back){
            frame.dispose();
            new Home();
        }
        if(e.getSource()==bu_fetch)
        {
            if(checkInputs()){
                if(fetchTableData())
                {
                    l_fetch.setVisible(false);
                    sp_table.setVisible(true);
                }
                else{
                    l_fetch.setVisible(true);
                    sp_table.setVisible(false);
                }  
            }
        }
    }
}