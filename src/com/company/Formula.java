package com.company;

import java.util.ArrayList;

public class Formula {

    enum Type{DATE,DATE_P_C,DATE_M_C,CELL_P_C,CELL_M_C,MIN,MAX};


    private String shortFormulaString;
    private String formulaString;
    private Type formulaType;

    public ArrayList<Cell> childCells;

    public Formula() {
        childCells= new ArrayList<>();
    }

    public String getShortFormulaString() {
        return shortFormulaString;
    }

    public void setShortFormulaString(String shortFormulaString) {
        this.shortFormulaString = shortFormulaString;
    }

    public String getFormulaString() {
        return formulaString;
    }

    public void setFormulaString(String formulaString) {
        this.formulaString = formulaString;
    }

    public Type getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(Type formulaType) {
        this.formulaType = formulaType;
    }

    @Override
    public String toString() {
        return shortFormulaString;
    }
}
