package com.company;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class TextTableCellEditor extends AbstractCellEditor implements TableCellEditor {

    public static final String DEFAULT_DATE_FORMAT="yyyy.MM.dd";
    public static final String FORMULA_REGEX="[=][M][I][N][(](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))([,]([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))*[)]";
    public static final String FORMULA_CONST_REGEX_P="[=](\\d+[.]\\d+[.]\\d+)[+]\\d+";
    public static final String FORMULA_CONST_REGEX_M="[=](\\d+[.]\\d+[.]\\d+)[-]\\d+";
    public static final String DATE_REGEX="(\\d+[.]\\d+[.]\\d+)";
    //    public static final String FORMULA_REGEX="[=][M][I][N][(]([A-Z]+\\d+)([,]([A-Z]+\\d+))*[)]";

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
        String value = ((JTextField) textField).getText();
        Date date;
        if(value.matches(DATE_REGEX)){
            try{
                date=dateFormat.parse(value);
                return date;
            } catch (ParseException e){
                return "";
            }
        } else if(value.matches(FORMULA_CONST_REGEX_P)){
            String[] values = value.split("[=.+]");
            int days=Integer.parseInt(values[3])+Integer.parseInt(values[4]);
            String resultDateString=formDateString(values[1],values[2],Integer.toString(days));
            try{
                date=dateFormat.parse(resultDateString);
                return date;
            } catch (ParseException e){
                return "";
            }
        }
        else if(value.matches(FORMULA_CONST_REGEX_M)){
            String[] values = value.split("[=.-]");
            int days=Integer.parseInt(values[3])-Integer.parseInt(values[4]);
            String resultDateString;
            String firstDateString=formDateString(values[1],values[2],values[3]);
            String secondDateString=formDateString("0","0",values[4]);
            Date firstDate,secondDate;
            try{
                firstDate=dateFormat.parse(firstDateString);
                secondDate=dateFormat.parse(secondDateString);
                if(firstDate.compareTo(secondDate)<=0){
                    JOptionPane.showMessageDialog(null,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
                    return "";
                }
                resultDateString=formDateString(values[1],values[2],Integer.toString(days));
                long l=firstDate.getTime()-secondDate.getTime();
                return new Date(l);
            } catch (ParseException e){
                return "";
            }
        }
        return null;
    }

    public String formDateString(String year,String month, String day){
        StringBuffer buffer=new StringBuffer();
        buffer.append(year);
        buffer.append(".");
        buffer.append(month);
        buffer.append(".");
        buffer.append(day);
        return buffer.toString();
    }
}

//                JOptionPane.showMessageDialog(View.this,"Input error","Date error", JOptionPane.ERROR_MESSAGE);
