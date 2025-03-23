package Programa.Sistema;

import Programa.Exceptions.EntradaJaRegistradaException;
import Programa.Exceptions.SaidaJaRegistradaException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class SistemaDoGerenciadorFinanceiro implements Serializable {

    private Usuario usuarioPrincipal;
    private GravadorDeDados gravador;
    private Random random;

    //inicia o sistema
    public SistemaDoGerenciadorFinanceiro(){
        this.usuarioPrincipal = new Usuario();
        this.gravador = new GravadorDeDados();
        this.random = new Random();
    }

    //Gerencia o Usuário.
    public void cadastraUsuario (String nome, double saldoCorrente){
        this.usuarioPrincipal = new Usuario(nome, saldoCorrente);
    }

    public void substitui_O_Usuario(Usuario usuario){
        this.usuarioPrincipal = usuario;
    }

    //Registra saidas e entradas de valores.
    public void registrarEntrada (String descricao, double valor) throws EntradaJaRegistradaException {
        int novoCodigo = random.nextInt();
        if(usuarioPrincipal.verificadorDeMovimentacao(novoCodigo, descricao)){
            throw new EntradaJaRegistradaException("Já existe uma entrada com este código e descrição.");
        }else{
            Entrada entrada = new Entrada(novoCodigo, descricao, valor);
            usuarioPrincipal.adicionarEntrada(entrada);
        }
    }

    public void registrarSaida (String descricao, double valor) throws SaidaJaRegistradaException {
        int novoCodigo = random.nextInt();
        if(usuarioPrincipal.verificadorDeMovimentacao(novoCodigo, descricao)){
            throw new SaidaJaRegistradaException("Já existe uma saida com este código e descrição.");
        }else{
            Saida saida = new Saida(novoCodigo, descricao, valor);
            usuarioPrincipal.adicionarSaida(saida);
        }
    }

    //get's e set's.
        //Nome e saldo.
    public String getNomeDoUsuario(){
        return usuarioPrincipal.getNome();
    }
    public void setNomeDoUsuario(String novoNome){
        this.usuarioPrincipal.setNome(novoNome);
    }
    public double getSaldoDoUsuario(){
        return this.usuarioPrincipal.getSaldoCorrente();
    }
    public void setSaldoDoUsuario(double novoSaldo){
        this.usuarioPrincipal.setSaldoCorrente(novoSaldo);
    }

        //Informativo base do usuario.
    public String getInformativo(){
        return  "Saldo atual:\n"+getSaldoDoUsuario()+"\n" +
                "Quantidade de movimentações de entrada:\n"+getEntradasDoUsuario().size()+"\n" +
                "Quantidade de movimentações de saída:\n"+getSaidasDoUsuario().size();
    }

        //Entradas e saídas.
    public List<Entrada> getEntradasDoUsuario(){
        return usuarioPrincipal.getEntradas();
    }

    public List<Saida> getSaidasDoUsuario(){
        return usuarioPrincipal.getSaidas();
    }

    public double getValorDeTodasAsEntradas(){
        return usuarioPrincipal.getValorDeTodasAsEntradas();
    }

    public double getValorDeTodasAsSaidas(){
        return usuarioPrincipal.getValorTotalDeTodasAsSaidas();
    }

    //Operações com o saldo.
    public void calcularSaldo (){
        double saldo = (getValorDeTodasAsEntradas()-getValorDeTodasAsSaidas())+getSaldoDoUsuario();
        this.usuarioPrincipal.setSaldoCorrente(saldo);
    }

    //Gravação, recuperação e exlcusão de dados;
    public void salvarDados() throws IOException {
        gravador.gravaDados(this.usuarioPrincipal);
    }

    public void recuperarDados() throws IOException {
        this.usuarioPrincipal = gravador.recuperaDados();
    }

    public void apagarTodosOsDados(){
        usuarioPrincipal.setSaldoCorrente(0);
        usuarioPrincipal.setEntradas(new ArrayList<>());
        usuarioPrincipal.setSaidas(new ArrayList<>());
    }
}
