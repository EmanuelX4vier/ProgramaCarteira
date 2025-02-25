package Programa.Exceptions;

public class SaidaJaRegistradaException extends Exception {
    public SaidaJaRegistradaException(String message) {
        super(message);
    }
    public SaidaJaRegistradaException(){
        super();
    }
}
