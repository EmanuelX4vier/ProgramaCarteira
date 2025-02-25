package Programa.Exceptions;

public class SaidaNaoRegistradaException extends Exception {
    public SaidaNaoRegistradaException(String message) {
        super(message);
    }
    public SaidaNaoRegistradaException(){
        super();
    }
}
