package exceptions;

import java.util.regex.Pattern;

public class InvalidPhoneNumberException extends InvalidInformationException{
    private static Pattern phoneNumberPattern = Pattern.compile("09\\d{9}");
    public InvalidPhoneNumberException(){
        super("InvalidPhoneNumberException");
    }
    public InvalidPhoneNumberException(String errorMassage){
        super(errorMassage);
    }

    public static boolean isPhoneValid(String phoneNumber){
        if(phoneNumberPattern.matcher(phoneNumber).find()){
            return true;
        }
        return false;
    }
}
