package exceptions;

public class InsufficientStockException extends InvalidPurchaseException{
    public InsufficientStockException(){
        super("InsufficientStockException");
    }
    public InsufficientStockException(String errorMassage){
        super(" InsufficientStockException --> " + errorMassage);
    }
}
