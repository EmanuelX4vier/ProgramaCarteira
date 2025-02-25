package Programa.Exceptions;

public class EntradaNaoRegistradaException extends Exception {
    public EntradaNaoRegistradaException(String message) {
        super(message);
    }
    public EntradaNaoRegistradaException(){
        super();
    }
}
