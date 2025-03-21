package TestesCom_O_Sistema;

import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testeDeSistema() throws IOException {
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        try{
            sistema.recuperarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }
        assertEquals("Emanuel", sistema.getNomeDoUsuario());
    }

}
