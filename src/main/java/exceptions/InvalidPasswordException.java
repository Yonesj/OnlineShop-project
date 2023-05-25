package exceptions;

public class InvalidPasswordException extends InvalidInformationException{
    public InvalidPasswordException(){
        super("InvalidPasswordException");
    }
    public InvalidPasswordException(String errorMassage){
        super(" InvalidPasswordException --> " + errorMassage);
    }
}
