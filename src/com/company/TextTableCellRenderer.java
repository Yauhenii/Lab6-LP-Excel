package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TextTableCellRenderer extends DefaultTableCellRenderer {

    public static final String DEFAULT_DATE_FORMAT="yyyy.MM.dd";

    private Font font = new Font("helvitica", Font.BOLD, 18);
    private DateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

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
