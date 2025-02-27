package Programa.Sistema;

import Programa.Exceptions.TransacaoNaoExisteException;
import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.MovimentoBase;
import Programa.Transacoes.Saida;
import Programa.Transacoes.TipoDeMovimentacao;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class SistemaDoGerenciadorFinanceiro implements Serializable {

    private Usuario usuarioPrincipal;
    private GravadorDeDados gravador;

    //inicia o sistema
    public SistemaDoGerenciadorFinanceiro(){
        this.usuarioPrincipal = new Usuario();
        this.gravador = new GravadorDeDados();;
    }

    //Cria codigo aleatório para o usuário.
    Random random = new Random();

    //Cria usuário.
    public void cadastraUsuario (String nome, double saldoCorrente) throws UsuarioJaCadastradoException {
        Usuario usuario = new Usuario(nome, saldoCorrente, random.nextInt());
        this.usuarioPrincipal = usuario;
    }

    public void substitui_O_Usuario(Usuario usuario){
        this.usuarioPrincipal = usuario;
    }

    //Registra saidas e entradas de valores.
    public void registrarEntrada (int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao) throws UsuarioNaoCadastradoException {
        if(usuarioPrincipal.getCodigo() == codigoDoUsuario){
            Entrada novaEntrada = new Entrada(tipo, valor, descricao, new Data(), random.nextInt());
            List<Entrada> novaLista = new ArrayList<>(usuarioPrincipal.getEntradas());
            novaLista.add(novaEntrada);
            usuarioPrincipal.setEntradas(novaLista);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
    }

    public void registrarSaida(int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao) throws UsuarioNaoCadastradoException{
        if(usuarioPrincipal.getCodigo() == codigoDoUsuario){
            Saida novaSaida = new Saida(tipo, valor, descricao, new Data(), random.nextInt());
            List<Saida> novaLista = new ArrayList<>(usuarioPrincipal.getSaidas());
            novaLista.add(novaSaida);
            usuarioPrincipal.setSaidas(novaLista);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
    }



    //get's e set's.
        //Nome e codigo.
    public String getNomeDoUsuario(){
        return usuarioPrincipal.getNome();
    }

    public int getCodigoDoUsuario() throws UsuarioNaoCadastradoException {;
        return usuarioPrincipal.getCodigo();
    }

    //Informativo base do usuario.
    public String getInformativo(){
        return "Saldo atual:\n"+getSaldoAtual()+"\nTransações de entrada:\n"+getEntradasDoUsuario()+"\nTransações de saída:\n"+getSaidasDoUsuario();
    }

        //Entradas e saídas.
    public List<Entrada> getEntradasDoUsuario(){
        return usuarioPrincipal.getEntradas();
    }

    public List<Saida> getSaidasDoUsuario(){
        return usuarioPrincipal.getSaidas();
    }

    public double getValorDeTodasAsEntradas(){
        double valorDetodasAsEntradas = 0;
        for(Entrada e :this.usuarioPrincipal.getEntradas()){
            valorDetodasAsEntradas+=e.getValor();
        }
        return  valorDetodasAsEntradas;
    }

    public double getValorDeTodasAsSaidas(){
        double valordeTodasAsSaidas = 0;
        for(Saida s: this.usuarioPrincipal.getSaidas()){
            valordeTodasAsSaidas+=s.getValor();
        }
        return valordeTodasAsSaidas;
    }

    //Operações com o saldo.
    public double getSaldoAtual(){
        return this.usuarioPrincipal.getSaldoCorrente();
    }
    public double somaSaldo (){
        double saldo = usuarioPrincipal.getSaldoCorrente()+getValorDeTodasAsEntradas()-getValorDeTodasAsSaidas();
        usuarioPrincipal.setSaldoCorrente(saldo);
        return saldo;
    }

    //Pesquisa de transações.
    public MovimentoBase getTransacao (int codigo) throws TransacaoNaoExisteException{
        MovimentoBase transacaoEncontrada= null;
        for(Entrada e: this.usuarioPrincipal.getEntradas()){
            if(e.getCodigoDeMovimentacao() ==  codigo){
                transacaoEncontrada=e;
            }
        }

        for(Saida s: this.usuarioPrincipal.getSaidas()){
            if(s.getCodigoDeMovimentacao() == codigo){
                transacaoEncontrada=s;
            }
        }
        return transacaoEncontrada;
    }

    //Gravação, recuperação e exlcusão de dados;
    public void salvarDados() throws IOException {
        gravador.gravaDados(this.usuarioPrincipal);
    }

    public void recuperarDados() throws IOException {
        List<Usuario> usuariosRecuperados = new ArrayList<>(gravador.recuperaDados());
        for(Usuario u: usuariosRecuperados){
            Usuario usuario = new Usuario(u.getNome(), u.getSaldoCorrente(), u.getCodigo());
            List<Entrada> entradasRecuperadas = new ArrayList<>(u.getEntradas());
            List<Saida> saidasRecuperadas = new ArrayList<>(u.getSaidas());
            usuario.setEntradas(entradasRecuperadas);
            usuario.setSaidas(saidasRecuperadas);
            this.usuarioPrincipal = usuario;
        }
    }

    public void apagarTodosOsDados(){
        double saldoZero = 0;
        usuarioPrincipal.setSaldoCorrente(saldoZero);
        List<Entrada> entradaVazia = new ArrayList<>();
        usuarioPrincipal.setEntradas(entradaVazia);
        List<Saida> saidaVazia = new ArrayList<>();
        usuarioPrincipal.setSaidas(saidaVazia);
    }
}
