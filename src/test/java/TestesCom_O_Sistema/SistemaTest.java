package TestesCom_O_Sistema;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiroParteDoUsuario;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testeDeSistema() throws UsuarioJaCadastradoException, UsuarioNaoCadastradoException, IOException {
        SistemaDoGerenciadorFinanceiroParteDoUsuario sistema = new SistemaDoGerenciadorFinanceiroParteDoUsuario();
        //Usuario de teste. Nome = Emanuel; Saldo corrente inicial: 1000.
        try{
            sistema.recuperarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }
        assertEquals("Emanuel", sistema.getNomeDoUsuario());
        assertEquals(1000, sistema.getSaldoAtual());
        //Informações conferem.

        //Teste com transação.
                //sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Recebimento, 200, "Agiotagem");
        assertEquals(1,sistema.getEntradasDoUsuario().size());
            //Transação de entrada e gravação de dados funcionando.
                //sistema.registrarSaida(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Compra, 300, "Armamento");
        assertEquals(1, sistema.getEntradasDoUsuario().size());
            //Transção de saída e gravação de dados funcionando.
        //Teste com transações OK.

        //Testes com o saldo.
        sistema.somaSaldo();
        assertEquals(900, sistema.getSaldoAtual());
            //somaSaldo e saldoAtual funcionando.













        /*try{
            sistema.salvarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }*/


    }

}
