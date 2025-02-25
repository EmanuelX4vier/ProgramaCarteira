package Programa.Exceptions;

public class EntradaJaRegistradaException extends Exception{
    public EntradaJaRegistradaException(String msg){
        super(msg);
    }
    public EntradaJaRegistradaException(){
        super();
    }
}
