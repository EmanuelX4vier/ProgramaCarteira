package Programa.Sistema;

import Programa.Exceptions.TransacaoNaoExisteException;
import Programa.Transacoes.MovimentoBase;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;
import Programa.Transacoes.TipoMovimento;

import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private double saldoCorrente;
    private HashMap<Integer, MovimentoBase> transacoes; // Mapa único para entradas e saídas

    // Construtores
    public Usuario(String nome, double saldoCorrente){
        this.nome = nome;
        this.saldoCorrente = saldoCorrente;
        this.transacoes = new HashMap<>();
    }

    public Usuario() {
        this("Sem nome", 0);
    }

    // Adiciona qualquer tipo de transação
    public void adicionarTransacao(MovimentoBase movimento){
        transacoes.put(movimento.getCodigoDeMovimentacao(), movimento);
    }

    // Recupera uma transação pelo código
    public MovimentoBase getTransacao(int codigo) throws TransacaoNaoExisteException {
        if(transacoes.containsKey(codigo)) return transacoes.get(codigo);
        throw new TransacaoNaoExisteException("Não existe transação com este código: " + codigo);
    }

    // Lista apenas entradas
    public List<Entrada> getEntradas() {
        List<Entrada> lista = new ArrayList<>();
        for(MovimentoBase m : transacoes.values()){
            if(m.getTipo() == TipoMovimento.ENTRADA) lista.add((Entrada) m);
        }
        return lista;
    }

    // Lista apenas saídas
    public List<Saida> getSaidas() {
        List<Saida> lista = new ArrayList<>();
        for(MovimentoBase m : transacoes.values()){
            if(m.getTipo() == TipoMovimento.SAIDA) lista.add((Saida) m);
        }
        return lista;
    }

    // Calcula o saldo atual
    public double calcularSaldo() {
        double totalEntradas = getEntradas().stream().mapToDouble(Entrada::getValor).sum();
        double totalSaidas = getSaidas().stream().mapToDouble(Saida::getValor).sum();
        saldoCorrente = saldoCorrente + totalEntradas - totalSaidas;
        return saldoCorrente;
    }

    // Verifica se uma transação específica existe
    public boolean verificarTransacao(int codigo, String descricao){
        MovimentoBase m = transacoes.get(codigo);
        return m != null && m.getDescricao().equalsIgnoreCase(descricao);
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getSaldoCorrente() { return saldoCorrente; }
    public void setSaldoCorrente(double saldoCorrente) { this.saldoCorrente = saldoCorrente; }

    public HashMap<Integer, MovimentoBase> getTransacoes() { return transacoes; }
    public void setTransacoes(HashMap<Integer, MovimentoBase> transacoes) { this.transacoes = transacoes; }

    // Total de entradas e saídas
    public double getValorDeTodasAsEntradas() {
        return getEntradas().stream().mapToDouble(Entrada::getValor).sum();
    }

    public double getValorTotalDeTodasAsSaidas() {
        return getSaidas().stream().mapToDouble(Saida::getValor).sum();
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", saldoCorrente=" + saldoCorrente +
                ", totalTransacoes=" + transacoes.size() +
                '}';
    }
}
