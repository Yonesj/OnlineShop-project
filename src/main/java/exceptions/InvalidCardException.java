package exceptions;

import java.util.regex.Pattern;

public class InvalidCardException extends Exception{
    private static Pattern cardPattern = Pattern.compile("\\d{4}( |-)\\d{4}( |-)\\d{4}( |-)\\d{4}");

    public InvalidCardException(){
        super("InvalidCardException");
    }
    public InvalidCardException(String errorMassage){
        super(errorMassage);
    }

    public static boolean isCardValid(String card){
        if(cardPattern.matcher(card).find()){
            return true;
        }
        return false;
    }
}
