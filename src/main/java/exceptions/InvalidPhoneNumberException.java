package exceptions;

public class InvalidPhoneNumberException extends InvalidInformationException{
    public InvalidPhoneNumberException(){
        super("InvalidPhoneNumberException");
    }
    public InvalidPhoneNumberException(String errorMassage){
        super(" InvalidPhoneNumberException--> " + errorMassage);
    }
}
