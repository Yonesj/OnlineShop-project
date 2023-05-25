package exceptions;

public class InsufficientBalanceException extends InvalidPurchaseException{
    public InsufficientBalanceException(){
        super("InsufficientBalanceException");
    }
    public InsufficientBalanceException(String errorMassage){
        super(" InsufficientBalanceException --> " + errorMassage);
    }
}
