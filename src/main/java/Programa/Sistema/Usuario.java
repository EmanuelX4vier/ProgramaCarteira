package Programa.Sistema;

import Programa.Exceptions.TransacaoNaoExisteException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.MovimentoBase;
import Programa.Transacoes.Saida;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Usuario implements Serializable {

    private String nome;
    private double saldoCorrente;
    private HashMap<Integer, Entrada> entradas;
    private HashMap<Integer, Saida> saidas;

    //Criação o usuário.
    public Usuario(String nome, double saldoCorrente){
        this.nome=nome;
        this.saldoCorrente=saldoCorrente;
        this.entradas=new HashMap<>();
        this.saidas=new HashMap<>();
    }

    public Usuario(){
        this.nome = "Sem nome";
        this.saldoCorrente = 0;
        this.entradas=new HashMap<>();
        this.saidas=new HashMap<>();
    }

    //Demais métodos.
    public boolean verificadorDeMovimentacao(int codigoDeMovimentacao, String descicao){
        for(Entrada a: this.entradas.values()){
            if(a.getCodigoDeMovimentacao() == codigoDeMovimentacao && a.getDescricao().equalsIgnoreCase(descicao)){
                return true;
            }
        }
        for(Saida s: this.saidas.values()){
            if(s.getCodigoDeMovimentacao() == codigoDeMovimentacao && s.getDescricao().equalsIgnoreCase(descicao)){
                return true;
            }
        }
        return false;
    }

    public void adicionarEntrada(Entrada entrada){
        this.entradas.put(entrada.getCodigoDeMovimentacao(), entrada);
    }

    public void adicionarSaida(Saida saida){
        this.saidas.put(saida.getCodigoDeMovimentacao(), saida);
    }

    //Get's.
        //Nome e saldo.
    public String getNome() {
        return nome;
    }

    public double getSaldoCorrente() {
        return saldoCorrente;
    }

        //Entradas e Saídas.
    /*public MovimentoBase getTransacao (int codigoDeMovimentacao) throws TransacaoNaoExisteException {
        for(Entrada e: this.entradas.values()){
            if(e.getCodigoDeMovimentacao() ==  codigoDeMovimentacao){
                return e;
            }
        }

        for(Saida s: this.saidas.values()){
            if(s.getCodigoDeMovimentacao() == codigoDeMovimentacao){
                return s;
            }
        }
        throw new TransacaoNaoExisteException("Não existe transação com este código.");
    }*/

    public List<Entrada> getEntradas() {
        List<Entrada> listaDeEntradas = new ArrayList<>(this.entradas.values());
        return listaDeEntradas;
    }

    public double getValorDeTodasAsEntradas(){
        double valorTotal = 0;
        for(Entrada e: this.entradas.values()){
            valorTotal += e.getValor();
        }
        return valorTotal;
    }

    public List<Saida> getSaidas() {
        List<Saida> listaDeSaidas = new ArrayList<>(this.saidas.values());
        return listaDeSaidas;
    }

    public double getValorTotalDeTodasAsSaidas(){
        double valorTotal = 0;
        for(Saida s: this.saidas.values()){
            valorTotal += s.getValor();
        }
        return valorTotal;
    }

    //Set's.
        //Nome e saldo.
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSaldoCorrente(double saldoCorrente) {
        this.saldoCorrente = saldoCorrente;
    }

        //Entradas e saidas
    public void setEntradas(List<Entrada> entradas) {
        List<Entrada> novasEntradas = new ArrayList<>(entradas);
        this.entradas = new HashMap<>();
        for(Entrada e: novasEntradas){
            Entrada novaEntrada = new Entrada(e.getCodigoDeMovimentacao(), e.getDescricao(), e.getValor());
            this.entradas.put(e.getCodigoDeMovimentacao(), novaEntrada);
        }
    }

    public void setSaidas(List<Saida> saidas) {
        List<Saida> novasSaidas = new ArrayList<>(saidas);
        this.saidas = new HashMap<>();
        for(Saida e: novasSaidas){
            Saida novaSaida = new Saida(e.getCodigoDeMovimentacao(), e.getDescricao(), e.getValor());
            this.saidas.put(e.getCodigoDeMovimentacao(), novaSaida);
        }
    }
}
