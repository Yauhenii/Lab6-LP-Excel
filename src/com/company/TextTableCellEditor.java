package com.company;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.ParseException;
import java.util.Date;

public class TextTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    private Component textField;

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
            ((JTextField) textField).setText(DateUtil.format(date));
        } else{
            ((JTextField) textField).setText("");
        }
        return textField;
    }
    public Object getCellEditorValue() {

        String value = ((JTextField) textField).getText();

        if(DateUtil.isDate(value)){
            return DateUtil.parse(value);
        } else if(DateUtil.isDatePlusConst(value)){
            String[] values = value.split("[=.+]");
            String dateString=DateUtil.formDateString(values[1],values[2],values[3]);
            Date date=(Date)DateUtil.parse(dateString);
            date=DateUtil.addDays(date,Integer.parseInt(values[4]));
            return date;
        }
        return null;
    }
}

//                JOptionPane.showMessageDialog(View.this,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
