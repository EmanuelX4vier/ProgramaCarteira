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
        this.gravador = new GravadorDeDados();
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
    public void registrarEntrada (int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao, Data data) throws UsuarioNaoCadastradoException {
        if(usuarioPrincipal.getCodigo() == codigoDoUsuario){
            Entrada novaEntrada = new Entrada(tipo, valor, descricao, data, random.nextInt());
            List<Entrada> novaLista = new ArrayList<>(usuarioPrincipal.getEntradas());
            novaLista.add(novaEntrada);
            usuarioPrincipal.setEntradas(novaLista);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
    }

    public void registrarSaida(int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao, Data data) throws UsuarioNaoCadastradoException{
        if(usuarioPrincipal.getCodigo() == codigoDoUsuario){
            Saida novaSaida = new Saida(tipo, valor, descricao, data, random.nextInt());
            List<Saida> novaLista = new ArrayList<>(usuarioPrincipal.getSaidas());
            novaLista.add(novaSaida);
            usuarioPrincipal.setSaidas(novaLista);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
    }

    //Operações com o saldo.
    public double saldo (){
        double saldo = usuarioPrincipal.getSaldoCorrente()+usuarioPrincipal.getValorDeTodasAsEntradas()-usuarioPrincipal.getValorDeTodasAsSaidas();
        usuarioPrincipal.setSaldoCorrente(saldo);
        return saldo;
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
        return "Saldo atual:\n"+saldo()+"\nTransações de entrada:\n"+getEntradasDoUsuario()+"\nTransações de saída:\n"+getSaidasDoUsuario();
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
        return usuarioPrincipal.getValorDeTodasAsSaidas();
    }

    public MovimentoBase getTransacaoPorCodigo(int codigo) throws TransacaoNaoExisteException {
        return usuarioPrincipal.getTransacaoPorCodigo(codigo);
    }

    //Gravação, recuperação e exlcusão de dados;
    public void salvarDados() throws IOException {
        gravador.gravaDados(this.usuarioPrincipal);
    }

    public void recuperarDados() throws IOException {
        List<Usuario> usuariosRecuperados = new ArrayList<>(gravador.recuperaDados());
        for(Usuario u: usuariosRecuperados){
            Usuario usuario = new Usuario(u.getNome(), u.getSaldoCorrente(), u.getCodigo());
            this.usuarioPrincipal = usuario;
            this.usuarioPrincipal.setEntradas(u.getEntradas());
            this.usuarioPrincipal.setSaidas(u.getSaidas());
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
