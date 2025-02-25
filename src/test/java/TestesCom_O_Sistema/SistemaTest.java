package TestesCom_O_Sistema;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.Data;
import Programa.Sistema.GravadorDeDados;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Transacoes.TipoDeMovimentacao;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testeDeSistema() throws UsuarioJaCadastradoException, UsuarioNaoCadastradoException, IOException {
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        GravadorDeDados gravador = new GravadorDeDados();
        try{
            sistema.recuperarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }
        assertEquals("Emanuel",sistema.getNomeDoUsuario());
    }

}
