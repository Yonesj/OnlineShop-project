package exceptions;

public class InvalidEmailException extends InvalidInformationException{
    public InvalidEmailException(){
        super("InvalidEmailException");
    }
    public InvalidEmailException(String errorMassage){
        super(" InvalidEmailException --> " + errorMassage);
    }
}
