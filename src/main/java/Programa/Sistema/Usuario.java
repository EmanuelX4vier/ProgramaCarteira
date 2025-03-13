package Programa.Sistema;

import Programa.Transacoes.Entrada;
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

    //Get's.
        //Nome e saldo.
    public String getNome() {
        return nome;
    }

    public double getSaldoCorrente() {
        return saldoCorrente;
    }

        //Entradas e Saídas.
    public List<Entrada> getEntradas() {
        List<Entrada> listaDeEntradas = new ArrayList<>(this.entradas.values());
        return listaDeEntradas;
    }

    public List<Saida> getSaidas() {
        List<Saida> listaDeSaidas = new ArrayList<>(this.saidas.values());
        return listaDeSaidas;
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
            Entrada novaEntrada = new Entrada(e.getCodigoDeMovimentacao(), e.getDescricao(), e.getData(), e.getValor());
            this.entradas.put(e.getCodigoDeMovimentacao(), novaEntrada);
        }
    }

    public void setSaidas(List<Saida> saidas) {
        List<Saida> novasSaidas = new ArrayList<>(saidas);
        this.saidas = new HashMap<>();
        for(Saida e: novasSaidas){
            Saida novaSaida = new Saida(e.getCodigoDeMovimentacao(), e.getDescricao(), e.getData(), e.getValor());
            this.saidas.put(e.getCodigoDeMovimentacao(), novaSaida);
        }
    }
}
