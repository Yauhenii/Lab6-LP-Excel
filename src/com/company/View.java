package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class View extends JFrame {

    public static final int SCREEN_WIDTH=500;
    public static final int SCREEN_HEIGHT=400;

    JPanel mainPanel;
    JScrollPane scrollPane;
    JTable table;
    JMenuBar menuBar;
    JMenu menuFormat;
    JMenuItem menuItemSetFormat;

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
        table.setRowHeight(60);
        //menu
        menuBar=new JMenuBar();
        menuFormat=new JMenu("Date format");
        menuItemSetFormat=new JMenuItem("Set date format...");
        menuFormat.add(menuItemSetFormat);
        menuBar.add(menuFormat);
        setJMenuBar(menuBar);
        //
        //scroll pane
        scrollPane= new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //window
        mainPanel.add(scrollPane,BorderLayout.CENTER);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        pack();
    }

}
