package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TutsDate {
    public static void main(String[] args) {
        Date date=new Date();
        System.out.println("Date is : "+date);
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        System.out.println("Date is : "+format.format(date));
        format.applyPattern("G y M d k H m s S E D F w W a h K z Z Y u X L");
        System.out.println("Date is : "+format.format(date));
    }
    
}
