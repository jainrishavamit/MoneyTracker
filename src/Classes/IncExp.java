package Classes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

public abstract class IncExp extends MyFrame implements ActionListener{
    JLabel l_head,l_date,l_category,l_desc,l_amount;
    JXDatePicker dp_date ;
    JComboBox cb_category;
    JTextField tf_desc,tf_amount;
    JButton bu_add,bu_addMore,bu_cancel,bu_addCategory;

    public IncExp() {
        addComponents();
    }
    
  @Override
    public void addComponents() {
        byte lb=100;
        Font f=new Font("Times New Roman",Font.BOLD,20);
        
        l_head=new JLabel();
        l_head.setBounds(25,50,250,35);
        l_head.setFont(new Font("Monotype Cursiva",Font.BOLD|Font.ITALIC,30));
        l_head.setForeground(Color.darkGray);
        
        l_date=new JLabel("Date");
        l_date.setBounds(lb, 150, 100, 30);
        l_date.setFont(f);
        
        dp_date= new org.jdesktop.swingx.JXDatePicker();
        dp_date.setBounds(lb+175, 150,150, 30);
        dp_date.setFormats("dd/MM/yyyy");
        dp_date.setDate(new Date());
        
        l_category=new JLabel("Category");
        l_category.setBounds(lb,200 , 100, 30);
        l_category.setFont(f);
        
        cb_category=new JComboBox();
        cb_category.setBounds(lb+175,200,150,30);
        
        bu_addCategory=new JButton("Add Category");
        bu_addCategory.setBounds(lb+330,200,110,30);
        bu_addCategory.setCursor(new Cursor(12));
        bu_addCategory.setBackground(new Color(153,153,255));
        bu_addCategory.addActionListener(this);
        
        l_desc=new JLabel("Description");
        l_desc.setBounds(lb,250 , 100, 30);
        l_desc.setFont(f);
        
        tf_desc = new JTextField(3);
        tf_desc.setBounds(lb+175, 250, 150, 30);
        
        l_amount=new JLabel("Amount");
        l_amount.setBounds(lb,300, 100, 30);
        l_amount.setFont(f);
        
        tf_amount = new JTextField(3);
        tf_amount.setBounds(lb+175,300, 150, 30);
        
        bu_add=new JButton("Add");
        bu_add.setBounds(140,380,110,30);
        bu_add.setCursor(new Cursor(12));
        bu_add.setBackground(new Color(153,153,255));
        bu_add.addActionListener(this);
        
        bu_addMore=new JButton("Add More");
        bu_addMore.setBounds(300,380,110,30);
        bu_addMore.setCursor(new Cursor(12));
        bu_addMore.setBackground(new Color(153,153,255));
        bu_addMore.addActionListener(this);
        
        bu_cancel=new JButton("Cancel");
        bu_cancel.setBounds(220,430,110,30);
        bu_cancel.setCursor(new Cursor(12));
        bu_cancel.setBackground(new Color(153,153,255));
        bu_cancel.addActionListener(this);
        
        addCompo(l_head,l_date,l_category,l_desc,l_amount,dp_date,cb_category,tf_desc,tf_amount,bu_addCategory,bu_add,bu_addMore,bu_cancel);
    }
    
    public void setHeading(String head){
        l_head.setText("<html><u>"+head+"</u>"+" :</html>");
    }
    public void setCategory(String categories){
        String cat[]=categories.split(",");
        for(String c:cat)
            cb_category.addItem(c);
    }    
    public void clearCategory(){
        cb_category.removeAllItems();
    }
    
}
