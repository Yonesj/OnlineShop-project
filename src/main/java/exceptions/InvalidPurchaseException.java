package exceptions;

public class InvalidPurchaseException extends Exception{
    public InvalidPurchaseException(){
        super("InvalidPurchaseException");
    }
    public InvalidPurchaseException(String errorMassage){
        super("InvalidPurchaseException --> " + errorMassage);
    }

}
