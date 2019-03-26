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
        if(value instanceof Formula) {
            Formula formula = (Formula) value;
            ((JTextField) textField).setText(formula.getFormulaString());
        } else{
            ((JTextField) textField).setText("");
        }
        return textField;
    }
    public Object getCellEditorValue() {
        String value = ((JTextField) textField).getText();
        if(FormulaUtil.isDate(value)){
            return FormulaUtil.cmpAsDate(value);
        } else if(FormulaUtil.isOpPlusConst(value)){
            return FormulaUtil.cmpAsCellPlusConst(value);
        }
        return "";
    }
}

//                JOptionPane.showMessageDialog(View.this,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
