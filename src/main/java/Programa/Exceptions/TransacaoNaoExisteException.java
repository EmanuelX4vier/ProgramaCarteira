package Programa.Exceptions;

public class TransacaoNaoExisteException extends Exception{
    public TransacaoNaoExisteException(String msg){
        super(msg);
    }
    public TransacaoNaoExisteException(){
        super();
    }
}
