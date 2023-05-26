package exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InvalidPasswordException extends InvalidInformationException{
    private static Pattern passPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    private static Matcher matcher;

    public InvalidPasswordException(){
        super("InvalidPasswordException");
    }
    public InvalidPasswordException(String errorMassage){
        super(errorMassage);
    }

    public static boolean isPassValid(String password){
        if(passPattern.matcher(password).find()){
           return true;
        }
        return false;
    }
}
