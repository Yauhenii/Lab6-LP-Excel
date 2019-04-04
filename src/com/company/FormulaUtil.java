package com.company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FormulaUtil {

    enum Letters{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z};
    private static char[] letters={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static final HashMap<Character,Integer> indexMap;
        static {
            {
                indexMap=new HashMap<Character, Integer>();
                for (int i = 0; i < letters.length; i++) {
                    indexMap.put(letters[i], i + 1);
                }
            }
        }

    //regex
    public static final String DEFAULT_DATE_FORMAT="yyyy.MM.dd";
    public static final String DATE="(\\d+[.]\\d+[.]\\d+)";
    public static final String DATE_PLUS_CONST="[=](\\d+[.]\\d+[.]\\d+)[+]\\d+";
    public static final String DATE_MIN_CONST="[=](\\d+[.]\\d+[.]\\d+)[-]\\d+";
    public static final String CELL_PLUS_CONST ="[=]([A-Z]\\d+)[+]\\d+";
    public static final String CELL_MIN_CONST ="[=]([A-Z]\\d+)[-]\\d+";
    public static final String MIN="[=][M][I][N][(](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))([,](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+)))*[)]";
    public static final String MAX="[=][M][A][X][(](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+))([,](([A-Z]+\\d+)|(\\d+[.]\\d+[.]\\d+)))*[)]";

    private static DateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    //validators
    public static boolean isDate(String string){
        return string.matches(DATE);
    }
    public static boolean isDateMinConst(String string){
        return string.matches(DATE_MIN_CONST);
    }
    public static boolean isDatePlusConst(String string){
        return string.matches(DATE_PLUS_CONST);
    }
    public static boolean isCellPlusConst(String string){
        return string.matches(CELL_PLUS_CONST);
    }
    public static boolean isCellMinConst(String string){
        return string.matches(CELL_MIN_CONST);
    }
    public static boolean isMin(String string){
        return string.matches(MIN);
    }
    public static boolean isMax(String string){
        return string.matches(MAX);
    }
    //parsers
    public static Date parse(String string) {
        Date date;
        try {
            date = dateFormat.parse(string);
            return date;
        } catch (
                ParseException e) {
            return null;
        }
    }
    public static int toIndex(char ch){
        return indexMap.get(ch);
    }
    //
    public static String format(Date date){
        return dateFormat.format(date);
    }
    //compilers
    public static Formula cmpAsDate(String value){
        Formula formula=new Formula();
        Date date=parse(value);
        formula.setFormulaString(value);
        formula.setShortFormulaString(format(date));
        formula.setFormulaType(Formula.Type.DATE);
        return formula;
    }
    public static Formula cmpAsDatePlusConst(String value){
        Formula formula=new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.DATE_P_C);
        return formula;
    }
    public static Formula cmpAsDateMinConst(String value){
        Formula formula=new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.DATE_M_C);
        return formula;
    }
    public static Formula cmpAsCellPlusConst(String value){
        Formula formula= new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.CELL_P_C);
        return formula;
    }
    public static Formula cmpAsCellMinConst(String value){
        Formula formula= new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.CELL_M_C);
        return formula;
    }
    public static Formula cmpAsMin(String value){
        Formula formula= new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.MIN);
        return formula;
    }
    public static Formula cmpAsMax(String value){
        Formula formula= new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setFormulaType(Formula.Type.MAX);
        return formula;
    }
}
