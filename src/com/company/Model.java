package com.company;

import org.jgrapht.alg.cycle.CycleDetector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Model extends DefaultTableModel {

    public static final Object[] tableHeader = new String[]{"","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

    SimpleDirectedGraph<Cell, DefaultEdge> cellGraph;

    boolean trigger=false;

    public Model() {
        super(tableHeader, 0);
        cellGraph=new SimpleDirectedGraph<>(DefaultEdge.class);
        addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
               if(!trigger){
                   BreadthFirstIterator<Cell, DefaultEdge> iterator = new BreadthFirstIterator<>(cellGraph);
                   while (iterator.hasNext()) {
                       Cell c = iterator.next();
                       Object o = getValueAt(c.getR(), c.getC());
                       if (o instanceof Formula) {
                           refresh((Formula) o, c.getR(), c.getC());
                       }
                   }
               }
//                TopologicalOrderIterator<Cell, DefaultEdge> iterator=new TopologicalOrderIterator<>(cellGraph);
            }
        });
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        if(aValue instanceof Formula){
            Formula formula=(Formula) aValue;
            refresh(formula,row,column);
        }
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return super.getColumnClass(columnIndex);
    }

    void refresh(Formula formula,int row,int column){
        Cell c1 = new Cell(row, column);
        if(formula.getFormulaType()==Formula.Type.DATE){
            //nothing to do
        }
        else if(formula.getFormulaType()==Formula.Type.DATE_P_C){
            String string=formula.getFormulaString();
            String[] ops=string.split("[=+]");
            Date date=FormulaUtil.parse(ops[1]);
            date=DateUtil.addDays(date,Integer.parseInt(ops[2]));
            formula.setShortFormulaString(FormulaUtil.format(date));
        }
        else if(formula.getFormulaType()==Formula.Type.DATE_M_C){
            String string=formula.getFormulaString();
            String[] ops=string.split("[=-]");
            Date date=FormulaUtil.parse(ops[1]);
            try {
                date = DateUtil.subDays(date, Integer.parseInt(ops[2]));
                formula.setShortFormulaString(FormulaUtil.format(date));
            } catch (IllegalArgumentException e){
                formula.setShortFormulaString("#ERROR");
            }
        }
        else if(formula.getFormulaType()==Formula.Type.CELL_P_C) {
            String string = formula.getFormulaString();
            String[] ops = string.split("[=+]");
            int c = FormulaUtil.toIndex(ops[1].charAt(0));
            int r = Integer.parseInt(Character.toString(ops[1].charAt(1))) - 1;
            Object value = getValueAt(r, c);
            Cell c2;
            c2 = new Cell(r, c);
            if(c2.equals(c1)){
                formula.setShortFormulaString("#ERROR");
                return;
            }
            cellGraph.addVertex(c1);
            cellGraph.addVertex(c2);

            cellGraph.addEdge(c1, c2);

            CycleDetector<Cell, DefaultEdge> detector = new CycleDetector<>(cellGraph);
            if(detector.detectCycles()){
                formula.setShortFormulaString("#ERROR");
                cellGraph.removeEdge(c1, c2);
                trigger=true;
                return;
            }
            trigger=false;

            if (value instanceof Formula) {
                if (c1.equals(c2)) {
                    formula.setShortFormulaString("#ERROR");
                } else {
                    Formula f = (Formula) value;
                    if(((Formula) value).getShortFormulaString().equals("#ERROR")){
                        formula.setShortFormulaString("#ERROR");
                        return;
                    }
                    Date date = FormulaUtil.parse(f.getShortFormulaString());
                    date = DateUtil.addDays(date, Integer.parseInt(ops[2]));
                    formula.setShortFormulaString(FormulaUtil.format(date));
                }
            } else {
                formula.setShortFormulaString("#ERROR");
            }
        }
        else if(formula.getFormulaType()==Formula.Type.CELL_M_C) {
            String string = formula.getFormulaString();
            String[] ops = string.split("[=-]");
            int c = FormulaUtil.toIndex(ops[1].charAt(0));
            int r = Integer.parseInt(Character.toString(ops[1].charAt(1))) - 1;
            Object value = getValueAt(r, c);

            Cell c2;
            c2 = new Cell(r, c);
            if(c2.equals(c1)){
                formula.setShortFormulaString("#ERROR");
                return;
            }
            cellGraph.addVertex(c1);
            cellGraph.addVertex(c2);

            cellGraph.addEdge(c1, c2);

            CycleDetector<Cell, DefaultEdge> detector = new CycleDetector<>(cellGraph);
            if(detector.detectCycles()){
                formula.setShortFormulaString("#ERROR");
                cellGraph.removeEdge(c1, c2);
                trigger=true;
                return;
            }
            trigger=false;

            if (value instanceof Formula) {
                if (c1.equals(c2)) {
                    formula.setShortFormulaString("#ERROR");
                } else {
                    Formula f = (Formula) value;
                    if(((Formula) value).getShortFormulaString().equals("#ERROR")){
                        formula.setShortFormulaString("#ERROR");
                        return;
                    }
                    Date date = FormulaUtil.parse(f.getShortFormulaString());
                    try {
                        date = DateUtil.subDays(date, Integer.parseInt(ops[2]));
                        formula.setShortFormulaString(FormulaUtil.format(date));
                    } catch (IllegalArgumentException e){
                        formula.setShortFormulaString("#ERROR");
                    }
                }
            } else {
                formula.setShortFormulaString("#ERROR");
            }
        }
        else if(formula.getFormulaType()==Formula.Type.MIN || formula.getFormulaType()==Formula.Type.MAX) {
            String string = formula.getFormulaString();
            String[] ops = string.split("[=(,)]");
            ArrayList<Date> dates=new ArrayList<>();
            for(int i=2;i<ops.length;i++){
                Date date=FormulaUtil.parse(ops[i]);
                if(date!=null){
                    dates.add(date);
                } else{
                    int c = FormulaUtil.toIndex(ops[i].charAt(0));
                    int r = Integer.parseInt(Character.toString(ops[i].charAt(1))) - 1;
                    Object value = getValueAt(r, c);

                    Cell c2;
                    c2 = new Cell(r, c);
                    if(c2.equals(c1)){
                        formula.setShortFormulaString("#ERROR");
                        return;
                    }
                    cellGraph.addVertex(c1);
                    cellGraph.addVertex(c2);

                    cellGraph.addEdge(c1, c2);

                    CycleDetector<Cell, DefaultEdge> detector = new CycleDetector<>(cellGraph);
                    if(detector.detectCycles()){
                        formula.setShortFormulaString("#ERROR");
                        cellGraph.removeEdge(c1, c2);
                        trigger=true;
                        return;
                    }
                    trigger=false;

                    if (value instanceof Formula) {
                        Formula f = (Formula) value;
                        if(((Formula) value).getShortFormulaString().equals("#ERROR")){
                            formula.setShortFormulaString("#ERROR");
                            return;
                        }
                        Date d = FormulaUtil.parse(f.getShortFormulaString());
                        dates.add(d);
                    }
                    else{
                        formula.setShortFormulaString("#ERROR");
                        return;
                    }
                }
            }
            if(formula.getFormulaType()==Formula.Type.MIN){
                formula.setShortFormulaString(FormulaUtil.format(Collections.min(dates)));
            } else{
                formula.setShortFormulaString(FormulaUtil.format(Collections.max(dates)));
            }
        }
    }
}
