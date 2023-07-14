package exceptions;

public class InvalidDiscountCodeException extends Exception{
    public InvalidDiscountCodeException(){
        super("InvalidDiscountCodeException");
    }
    public InvalidDiscountCodeException(String errorMassage){
        super("InvalidDiscountCodeException --> " + errorMassage);
    }
}
