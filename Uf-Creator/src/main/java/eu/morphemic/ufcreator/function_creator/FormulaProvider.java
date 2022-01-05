package eu.morphemic.ufcreator.function_creator;

import java.util.Collection;

public class FormulaProvider {
    static String getLocalityUtility(String c1,String c2,String c3,String c4){
        return "1/(1+acos(sin("+c1+"*pi/180)*sin("+c3+"*pi/180)+cos("+c1+"*pi/180)*cos("+c2+"*pi/180)+cos("+c4+"*pi/180−"+c2+"*pi/180))*6371000)";
    }
    static String multiply(String expr, String expr2) {
        return "(" + expr2 + "*" +expr +")";
    }
    static String add(String expr1, String expr2) {
        return "(" + expr1 + "+" + expr2 + ")";
    }
    static String minus(String expr1, String expr2) {
        return "(" + expr1 + "-" + expr2 + ")";
    }
    static String getSum(Collection<String> expressions) {
        return "(" + String.join("+", expressions) + ")";
    }

}
