package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {

    public static final Object[] tableHeader = new String[]{"","A","B","C","D"};

    Controller(){
        Model model= new Model(tableHeader,0);
        View view=new View(model);
        view.setVisible(true);

        GregorianCalendar calendar=new GregorianCalendar(2000, Calendar.APRIL,15);
        for(int i=1;i<=10;i++){
//            model.addRow(new Object[]{Integer.toString(i),calendar.getTime(),calendar.getTime(),calendar.getTime(),calendar.getTime()});
            model.addRow(new Object[]{Integer.toString(i),"","","",""});
        }
    }
}
