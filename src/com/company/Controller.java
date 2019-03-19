package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
            model.addRow(new Object[]{Integer.toString(i),"","","",""});
        }
        //set renderer and editor for columns
        TextTableCellRenderer renderer=new TextTableCellRenderer();
        TextTableCellEditor editor=new TextTableCellEditor();
        DefaultTableColumnModel columnModel=(DefaultTableColumnModel)view.table.getColumnModel();
        for(int i=1;i<columnModel.getColumnCount();i++){
            columnModel.getColumn(i).setCellRenderer(renderer);
            columnModel.getColumn(i).setCellEditor(editor);
        }
        //set actions on menu
        view.menuItemSetFormat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
