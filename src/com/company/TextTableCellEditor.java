package com.company;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    public static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd";

    private Component textField;
    private DateFormat dateFormat;

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    TextTableCellEditor(){
        textField = new JTextField();
        dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
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
