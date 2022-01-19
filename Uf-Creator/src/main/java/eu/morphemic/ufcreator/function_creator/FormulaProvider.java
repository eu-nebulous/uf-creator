package eu.morphemic.ufcreator.function_creator;

import java.util.Collection;

public class FormulaProvider {
    static String getLocalityUtility(String c1,String c2,String c3,String c4){
        return "1/(1+acos(sin("+c1+"*pi/180)*sin("+c3+"*pi/180)+cos("+c1+"*pi/180)*cos("+c2+"*pi/180)+cos("+c4+"*pi/180−"+c2+"*pi/180))*6371000)";
    }
    static String getFinishOnTime(String c1,String c2,String metric1,String metric2,String metric3,String variable1,String variable2){
        return "1/(1+exp(-"+c2+" *("+c1+" -(("+metric1+" * "+metric2+")/("+variable1+" * "+variable2+"))+ "+metric3+")))";
    }
    static String getCoresCostUtility(){
        return "exp()";
    }
    static String getSShaped(String metric,String a,String b){
        return "1-(1+e^(("+metric+" -"+b+")*6.90)/("+a+"-"+b+"))^-1";
    }
    static String getReversedSShaped(String metric,String a,String b){
        return "(1+e^(("+metric+" -"+b+")*6.90)/("+a+"-"+b+"))^-1";
    }
    static String getUShaped(String metric,String a,String b){
        return "1-e^(-6.90*("+metric+" -"+a+")^2/("+b+"-"+a+")^2)";
    }
    static String getReversedUShaped(String metric,String a,String b){
        return "e^(-6.90*("+metric+" -"+a+")^2/("+b+"-"+a+")^2)";
    }
    static String getConstantShaped(String metric,String a,String b){
        return "a";
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
