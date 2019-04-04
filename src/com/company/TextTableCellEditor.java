package com.company;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;

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
        String string = ((JTextField) textField).getText();
        if(FormulaUtil.isDate(string)){
            return FormulaUtil.cmpAsDate(string);
        }
        else if(FormulaUtil.isDatePlusConst(string)) {
            return FormulaUtil.cmpAsDatePlusConst(string);
        }
        else if(FormulaUtil.isDateMinConst(string)) {
            return FormulaUtil.cmpAsDateMinConst(string);
        }
        else if(FormulaUtil.isCellPlusConst(string)) {
            return FormulaUtil.cmpAsCellPlusConst(string);
        }
        else if(FormulaUtil.isCellMinConst(string)) {
            return FormulaUtil.cmpAsCellMinConst(string);
        }
        else if(FormulaUtil.isMin(string)) {
            return FormulaUtil.cmpAsMin(string);
        }
        else if(FormulaUtil.isMax(string)) {
            return FormulaUtil.cmpAsMax(string);
        }
        return "";
    }
}

//                JOptionPane.showMessageDialog(View.this,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
