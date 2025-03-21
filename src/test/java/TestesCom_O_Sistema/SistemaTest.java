package TestesCom_O_Sistema;

import Programa.Exceptions.EntradaJaRegistradaException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testeDeSistema() throws IOException, EntradaJaRegistradaException {
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        try{
            sistema.recuperarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }
        assertEquals("Emanuel", sistema.getNomeDoUsuario());
        sistema.registrarEntrada("Salário", 1450);
        System.out.println(sistema.getEntradasDoUsuario());
    }

}
