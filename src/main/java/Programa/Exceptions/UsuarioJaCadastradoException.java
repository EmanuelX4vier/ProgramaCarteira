package Programa.Exceptions;

public class UsuarioJaCadastradoException extends Exception {
    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
    public UsuarioJaCadastradoException(){
        super();
    }
}
