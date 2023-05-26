package exceptions;

import java.util.regex.Pattern;

public class InvalidEmailException extends InvalidInformationException{
    private static Pattern emailPattern = Pattern.compile("\\w+@(gmail|yahoo)\\.com");

    public InvalidEmailException(){
        super("InvalidEmailException");
    }
    public InvalidEmailException(String errorMassage){
        super(errorMassage);
    }

    public static boolean isEmailValid(String email){
        if(emailPattern.matcher(email).find()){
            return true;
        }
        return false;
    }
}
