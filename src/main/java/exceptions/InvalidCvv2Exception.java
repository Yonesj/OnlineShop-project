package exceptions;

import java.util.regex.Pattern;

public class InvalidCvv2Exception extends Exception{
    private static Pattern cvv2Pattern = Pattern.compile("\\d{3,4}");

    public InvalidCvv2Exception(){
        super("InvalidCvv2Exception");
    }
    public InvalidCvv2Exception(String errorMassage){
        super(errorMassage);
    }

    public static boolean isCvv2Valid(String cvv2){
        if(cvv2Pattern.matcher(cvv2).find()){
            return true;
        }
        return false;
    }
}
