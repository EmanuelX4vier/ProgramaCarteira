package Programa.Sistema;

import Programa.Exceptions.TransacaoNaoExisteException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.MovimentoBase;
import Programa.Transacoes.Saida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


import java.util.List;

public class Usuario implements Serializable {

    private String nome;
    private double saldoCorrente;
    private final int codigo;
    private HashMap<Integer, Entrada> entradas;
    private HashMap<Integer, Saida> saidas;

    //Criação o usuário.
    public Usuario(String nome, double saldoCorrente, int codigoDoUsuario){
        this.nome=nome;
        this.saldoCorrente=saldoCorrente;
        this.codigo = codigoDoUsuario;
        this.entradas=new HashMap<>();
        this.saidas=new HashMap<>();
    }

    public Usuario(){
        this.nome = "Sem nome";
        this.saldoCorrente = 0;
        this.codigo = 0;
        this.entradas=new HashMap<>();
        this.saidas=new HashMap<>();
    }

    //get's e set's.
        //Nome, saldo e codigo.
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldoCorrente() {
        return this.saldoCorrente;
    }

    public void setSaldoCorrente(double novoSaldoCorrente) {
        this.saldoCorrente = novoSaldoCorrente;
    }

    public int getCodigo() {
        return this.codigo;
    }

        //Entradas e saidas
    public List<Entrada> getEntradas() {
        List<Entrada> listaDeEntradas = new ArrayList<>(this.entradas.values());
        return listaDeEntradas;
    }

    public void setEntradas(List<Entrada> entradas) {
        List<Entrada> novasEntradas = new ArrayList<>(entradas);
        this.entradas = new HashMap<>();
        for(Entrada e: novasEntradas){
            Entrada novaEntrada = new Entrada(e.getTipo(), e.getValor(), e.getDescricao(), e.getData(), e.getCodigoDeMovimentacao());
            this.entradas.put(e.getCodigoDeMovimentacao(), novaEntrada);
        }
    }

    public List<Saida> getSaidas() {
        List<Saida> listaDeSaidas = new ArrayList<>(this.saidas.values());
        return listaDeSaidas;
    }

    public void setSaidas(List<Saida> saidas) {
        List<Saida> novasSaidas = new ArrayList<>(saidas);
        this.saidas = new HashMap<>();
        for(Saida e: novasSaidas){
            Saida novaSaida = new Saida(e.getTipo(), e.getValor(), e.getDescricao(), e.getData(), e.getCodigoDeMovimentacao());
            this.saidas.put(e.getCodigoDeMovimentacao(), novaSaida);
        }
    }

    public double getValorDeTodasAsEntradas(){
        double todasAsEntradas = 0;
        for(Entrada e: this.entradas.values()){
           todasAsEntradas += e.getValor();
        }
        return todasAsEntradas;
    }

    public double getValorDeTodasAsSaidas(){
        double todasAsSaidas = 0;
        for(Saida e: this.saidas.values()){
            todasAsSaidas += e.getValor();
        }
        return todasAsSaidas;
    }

        //PesquisaDeTransacao.
    public MovimentoBase getTransacaoPorCodigo(int codigoDaTransacao) throws TransacaoNaoExisteException {
        if(this.entradas.containsKey(codigoDaTransacao)){
            return this.entradas.get(codigoDaTransacao);
        }else{
            throw new TransacaoNaoExisteException("Esta transação não existe");
        }
    }

    //Equals, hashCode e toString.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Double.compare(saldoCorrente, usuario.saldoCorrente) == 0 && codigo == usuario.codigo && Objects.equals(nome, usuario.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, saldoCorrente, codigo);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", saldoCorrente=" + saldoCorrente +
                ", codigo=" + codigo +
                '}';
    }
}
