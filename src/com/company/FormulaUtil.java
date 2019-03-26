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
    public static final String DATE_REGEX="(\\d+[.]\\d+[.]\\d+)";


    public static final String OP_PLUS_CONST="[=]([A-Z]\\d+)[+]\\d+";

    private static DateFormat dateFormat=new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    //validators
    public static boolean isDate(String string){
        return string.matches(DATE_REGEX);
    }
    public static boolean isOpPlusConst(String string){
        return string.matches(OP_PLUS_CONST);
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
        formula.setParentAdress(null);
        formula.setFormulaType(Formula.Type.DATE);
        return formula;
    }
    public static Formula cmpAsCellPlusConst(String value){
        Formula formula= new Formula();
        formula.setFormulaString(value);
        formula.setShortFormulaString(null);
        formula.setParentAdress(null);
        formula.setFormulaType(Formula.Type.CELL_P_C);
        return formula;
    }

}
