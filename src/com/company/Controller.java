package com.company;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.TopologicalOrderIterator;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Controller {

    Controller(){
        Model model= new Model();
        View view=new View(model);
        view.setVisible(true);
        GregorianCalendar calendar=new GregorianCalendar(2000, Calendar.APRIL,15);
        for(int i=1;i<=30;i++){
            model.addRow(new Object[]{Integer.toString(i),"","","",""});
        }
        //set renderer and editor for columns
        TextTableCellRenderer renderer=new TextTableCellRenderer();
        TextTableCellEditor editor=new TextTableCellEditor();
        DefaultTableColumnModel columnModel=(DefaultTableColumnModel)view.table.getColumnModel();
        for(int i=1;i<columnModel.getColumnCount();i++){
            columnModel.getColumn(i).setCellRenderer(renderer);
            columnModel.getColumn(i).setCellEditor(editor);
            columnModel.getColumn(i).setPreferredWidth(150);
        }
    }
}
