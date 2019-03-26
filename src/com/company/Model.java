package com.company;

import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class Model extends DefaultTableModel {

    public static final Object[] tableHeader = new String[]{"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    public Model() {
        super(tableHeader, 0);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if(aValue instanceof Formula){
//            if(((Formula) aValue).getFormulaType()==Formula.Type.DATE){
//                for(int i=0;i<((Formula) aValue).childCells.size();i++){
//                    int r=((Formula) aValue).childCells.get(i).r;
//                    int c=((Formula) aValue).childCells.get(i).c;
//
//                }
//            }
//            else
                if(((Formula) aValue).getFormulaType()==Formula.Type.CELL_P_C) {
                String formulaString = ((Formula) aValue).getFormulaString();
                String[] operands=formulaString.split("[+]");
                String op1=operands[0];
                String op2=operands[1];

                int c=FormulaUtil.toIndex(op1.charAt(1));
                int r=Integer.parseInt(Character.toString(op1.charAt(2)))-1;
                if(getValueAt(r,c)!="") {
                    Formula formula = (Formula) getValueAt(r, c);

                    Date date = FormulaUtil.parse(formula.getShortFormulaString());
                    date = DateUtil.addDays(date, Integer.parseInt(op2));

                    ((Formula) aValue).setShortFormulaString(FormulaUtil.format(date));
                    ((Formula) aValue).setParentAdress(op1.substring(1));
                } else{
                    ((Formula) aValue).setShortFormulaString("#ERROR");
                }

            }
        }
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }
}
