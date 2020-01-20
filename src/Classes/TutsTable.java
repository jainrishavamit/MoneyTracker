package Classes;

import Classes.MyFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TutsTable extends MyFrame{

    public TutsTable() {
        addComponents();
    }
    
    JScrollPane jScrollPane1;
    javax.swing.JTable jTable1;
    DefaultTableModel model;
   
    @Override
    public void addComponents() {
        jScrollPane1 = new JScrollPane();
        
        Class[] types = new Class [] { String.class, Float.class, Float.class, Float.class };
        boolean[] canEdit = new boolean [] { true, true, false, false };
        String[] columnNames=new String [] {"", "Income", "Expense", "Net Effect"};
        Object[][] rowData= new Object [][] {
            {"Rishav" ,  25.25f , null ,  25.56f},
            {"hggv"   ,  266f   , null ,  65.0f},
            {"kj"     ,  65.0f  , null ,  66.0f}
        }; 
            
        model=new DefaultTableModel(rowData,columnNames){
            @Override
            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                //return false;
                return canEdit [columnIndex];
            }
        };
        jTable1=new JTable(model);
        jScrollPane1.setBounds(10, 10, 500, 200);
        jScrollPane1.setViewportView(jTable1);
        panel.add(jScrollPane1);
    }
    public static void main(String[] args) {
        new TutsTable();
    }
    
}
