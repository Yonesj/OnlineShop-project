package exceptions;

public class InvalidInformationException extends Exception{
    public InvalidInformationException(){
        super("InvalidInformationException");
    }
    public InvalidInformationException(String errorMassage){
        super("InvalidInformationException --> " + errorMassage);
    }
}
