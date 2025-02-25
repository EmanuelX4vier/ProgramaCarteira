package TestesCom_O_Sistema;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.Data;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Transacoes.TipoDeMovimentacao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaTest {

    @Test
    public void testeDeSistema() throws UsuarioJaCadastradoException, UsuarioNaoCadastradoException {
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        sistema.cadastraUsuario("Emanuel", 300);
        sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.RecebimentoQualquer, 300, "400", new Data());

        System.out.println(sistema.getEntradasDoUsuario());
    }

}
