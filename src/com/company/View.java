package com.company;

import com.sun.codemodel.internal.JOp;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class View extends JFrame {

    public static final int SCREEN_WIDTH=500;
    public static final int SCREEN_HEIGHT=400;

    JPanel mainPanel;
    JScrollPane scrollPane;
    JTable table;

    View(Model model){
        //main panel
        mainPanel=new JPanel(new BorderLayout());
        //table
        table=new JTable(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column==0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };
        table.setModel(model);
        table.setFillsViewportHeight(true);
        //set renderer and editor for columns
        TextTableCellRenderer renderer=new TextTableCellRenderer();
        TextTableCellEditor editor=new TextTableCellEditor();
        DefaultTableColumnModel columnModel=(DefaultTableColumnModel)table.getColumnModel();
        for(int i=1;i<columnModel.getColumnCount();i++){
            columnModel.getColumn(i).setCellRenderer(renderer);
            columnModel.getColumn(i).setCellEditor(editor);
        }
        table.setRowHeight(60);
        //
        //scroll pane
        scrollPane= new JScrollPane(table);
        //window
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
    }

}
