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
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    public class TextTableCellRenderer extends DefaultTableCellRenderer {

        Font font = new Font("helvitica", Font.BOLD, 18);

        @Override
        public Component getTableCellRendererComponent(JTable table,
                                                       Object value,
                                                       boolean isSelected,
                                                       boolean hasFocus,
                                                       int row,
                                                       int column){
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                    table,
                    value,
                    isSelected,
                    hasFocus,
                    row,
                    column);
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            if(value instanceof Date) {
                Date date = (Date) value;
                label.setText(dateFormat.format(date));
            }
            else{
                label.setText("");
            }
            return label;
        }
    }
    class TextTableCellEditor extends AbstractCellEditor implements TableCellEditor {

        Component textField;

        TextTableCellEditor(){
            textField = new JTextField();
        }
        public Component getTableCellEditorComponent(JTable table,
                                                     Object value,
                                                     boolean isSelected,
                                                     int row,
                                                     int column){
            if(value instanceof Date) {
                Date date = (Date) value;
                ((JTextField) textField).setText(dateFormat.format(date));
            } else{
                ((JTextField) textField).setText("");
            }
            return textField;
        }
        public Object getCellEditorValue() {
            try{
                return dateFormat.parse(((JTextField) textField).getText());
            } catch (ParseException e){
//                JOptionPane.showMessageDialog(View.this,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
                return "";
            }
        }
    }
}
