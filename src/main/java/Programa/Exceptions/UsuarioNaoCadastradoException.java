package Programa.Exceptions;

public class UsuarioNaoCadastradoException extends Exception{
    public UsuarioNaoCadastradoException (String msg){
        super(msg);
    }
    public UsuarioNaoCadastradoException (){
        super();
    }
}
