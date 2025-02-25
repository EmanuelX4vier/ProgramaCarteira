package Programa.Sistema;
import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;
import Programa.Transacoes.TipoDeMovimentacao;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;


public class SistemaDoGerenciadorFinanceiro implements Serializable {

    private Usuario usuarios;
    private GravadorDeDados gravador;

    //inicia o sistema
    public SistemaDoGerenciadorFinanceiro(){
        this.usuarios = new Usuario();
        this.gravador = new GravadorDeDados();
    }

    //Cria codigo aleatório para o usuário.
    Random random = new Random();

    //Cria usuário.
    public void cadastraUsuario (String nome, double saldoCorrente) throws UsuarioJaCadastradoException {
        int codigoDoUsuario = random.nextInt();
        Usuario usuario = new Usuario(nome, saldoCorrente, codigoDoUsuario);
        this.usuarios = usuario;
    }

    //Registra saidas e entradas de valores.
    public void registrarEntrada (int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao, Data data) throws UsuarioNaoCadastradoException {
        if(usuarios.getCodigo() == codigoDoUsuario){
            Entrada novaEntrada = new Entrada(tipo, valor, descricao, data, random.nextInt());
            usuarios.getEntradas().add(novaEntrada);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
    }

     public void registrarSaida(int codigoDoUsuario, TipoDeMovimentacao tipo, double valor, String descricao, Data data) throws UsuarioNaoCadastradoException{
        if(usuarios.getCodigo() == codigoDoUsuario){
            Saida novaSaida = new Saida(tipo, valor, descricao, data, random.nextInt());
            usuarios.getSaidas().add(novaSaida);
        }else{
            throw new UsuarioNaoCadastradoException("Codigo errado ou usuário não cadastrado");
        }
     }

     //Operações com o saldo.
     public double saldo (){
        double saldo = usuarios.getSaldoCorrente()+usuarios.getValorDeTodasAsEntradas()-usuarios.getValorDeTodasAsSaidas();
        usuarios.setSaldoCorrente(saldo);
        return saldo;
    }


    //get's e set's.
    public String getNomeDoUsuario(){
        String nome = usuarios.getNome();
        return nome;
    }

    public int getCodigoDoUsuario() throws UsuarioNaoCadastradoException {
        int codigoEncontrado = usuarios.getCodigo();
        return codigoEncontrado;
    }

    public List<Entrada> getEntradasDoUsuario(){
        List<Entrada>listaDeEntradas = usuarios.getEntradas();
        return listaDeEntradas;
    }

    public List<Saida> getSaidasDoUsuario(){
        List<Saida>listaDeSaidas = usuarios.getSaidas();
        return listaDeSaidas;
    }


    //Gravação e recuperação
    public void salvarDados() throws IOException {
        gravador.gravaDados(this.usuarios);
    }

    public void recuperarDados() throws IOException {
        List<Usuario> usuariosRecuperados = new ArrayList<>(gravador.recuperaDados());
        for(Usuario u: usuariosRecuperados){
            Usuario usuario = new Usuario(u.getNome(), u.getSaldoCorrente(), u.getCodigo());
            this.usuarios = usuario;
            this.usuarios.setEntradas(u.getEntradas());
            this.usuarios.setSaidas(u.getSaidas());
        }
    }
}
