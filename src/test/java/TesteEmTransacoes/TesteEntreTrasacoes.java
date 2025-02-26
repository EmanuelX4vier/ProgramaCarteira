package TesteEmTransacoes;

import Programa.Exceptions.TransacaoNaoExisteException;
import Programa.Sistema.Data;
import Programa.Sistema.Usuario;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;
import Programa.Transacoes.TipoDeMovimentacao;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TesteEntreTrasacoes {

    @Test
    public void testaTransacoes() throws TransacaoNaoExisteException {
        Random random = new Random();

        //Criação da lista de entradas.
        List<Entrada> listaDeEntradasParaUsuarioTeste = new ArrayList<>();
        //Ok

        //Criação da lista de saídas.
        List<Saida> listaDeSaidasParaUsuarioTeste = new ArrayList<>();
        //OK

        //Criação do usuário.
        int codigoDoUsuario = random.nextInt();
        Usuario usuarioTeste = new Usuario("Emanuel", 1000, codigoDoUsuario);
        //OK

        //O nome do usuário confere?
        assertEquals("Emanuel", usuarioTeste.getNome());
        //OK

        //O saldo corrente inicial registrado pelo usuário confere?
        assertEquals(1000, usuarioTeste.getSaldoCorrente());
        //OK

        //O codigo gerado para o usuario pelo random confere?
        assertEquals(codigoDoUsuario, usuarioTeste.getCodigo());
        //OK

        //O hasmap entrada está vazio ?
        assertTrue(usuarioTeste.getEntradas().size()==0);
        //OK

        //O hashmap saida está vazio ?
        assertTrue(usuarioTeste.getSaidas().size()==0);
        //OK

        //O teste de transação de entrada funciona?
        Entrada entrada1 = new Entrada(TipoDeMovimentacao.Recebimento, 200, "Agiotagem", new Data(), random.nextInt());
        listaDeEntradasParaUsuarioTeste.add(entrada1);
        usuarioTeste.setEntradas(listaDeEntradasParaUsuarioTeste);
        assertTrue(usuarioTeste.getEntradas().size()==1);
        //OK

        //O segundo teste de transação de entrada funciona?
        Entrada entrada2 = new Entrada(TipoDeMovimentacao.Indefinido, 100, "Achei na rua", new Data(), random.nextInt());
        listaDeEntradasParaUsuarioTeste.add(entrada2);
        List<Entrada> listaDeEntradas2 = new ArrayList<>(usuarioTeste.getEntradas());
        listaDeEntradas2.add(entrada2);
        assertTrue(listaDeEntradas2.size() == 2);
        usuarioTeste.setEntradas(listaDeEntradas2);
        //OK

        //A metodo saldo está correta(Teste com entradas)?
        double saldo = usuarioTeste.getSaldoCorrente()+usuarioTeste.getValorDeTodasAsEntradas()-usuarioTeste.getValorDeTodasAsSaidas();
        assertEquals(1300, saldo);
        //OK

        //O metodo de transação de saida funciona?
        Saida saida1 = new Saida(TipoDeMovimentacao.Compra, 300, "Compras", new Data(), random.nextInt());
        listaDeSaidasParaUsuarioTeste.add(saida1);
        usuarioTeste.setSaidas(listaDeSaidasParaUsuarioTeste);
        assertEquals(1, usuarioTeste.getSaidas().size());
        //OK

        //A operação saldo está correta(Teste com saídas)?
        double saldo2 = usuarioTeste.getSaldoCorrente()+usuarioTeste.getValorDeTodasAsEntradas()-usuarioTeste.getValorDeTodasAsSaidas();
        assertEquals(1000, saldo2);
        //OK



        //Testes extras.
        System.out.println(usuarioTeste.getCodigo());
        System.out.println(entrada1.getMovimentoCompleto());
        System.out.println(entrada2.getMovimentoCompleto());
        System.out.println(saida1.getMovimentoCompleto());
        System.out.println(usuarioTeste.getEntradas());
        System.out.println(usuarioTeste.getSaidas());
        System.out.println(usuarioTeste.getTransacaoPorCodigo(entrada1.getCodigoDeMovimentacao()));
        System.out.println(usuarioTeste.getSaldoCorrente());
        System.out.println(saldo);
        System.out.println(saldo2);
    }

}
