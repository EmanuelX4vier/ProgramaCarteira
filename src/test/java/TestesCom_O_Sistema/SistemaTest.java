package TestesCom_O_Sistema;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
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
        //sistema.cadastraUsuario("Emanuel", 1000);
        try{
            sistema.recuperarDados();
        } catch (IOException e) {
            e.getStackTrace();
        }
        assertEquals("Emanuel",sistema.getNomeDoUsuario());
        //sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Recebimento, 1000, "Agiotagem,");
        //sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Recebimento, 2000, "Roubei");
        //assertEquals(4, sistema.getEntradasDoUsuario().size());
        sistema.apagarTodosOsDados();
        try{
            sistema.salvarDados();
        }catch (IOException e){
            e.getStackTrace();
        }
    }

}
