package Programa.Transacoes;

import Programa.Sistema.Data;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Saida implements Serializable {

    private Random random;

    private final int codigoDeMovimentacao;
    private String descricao;
    private double valor;
    private Data data;

    //Cria o movimento.
    public Saida(int codigoDeMovimentacao, String descricao, double valor) {
        this.random = new Random();
        this.codigoDeMovimentacao = codigoDeMovimentacao;
        this.descricao = descricao;
        this.valor = valor;
        this.data = new Data();
    }

    public Saida() {
        this.codigoDeMovimentacao = random.nextInt();
        this.descricao = "Sem descrição";
        this.valor = 0;
        this.data = new Data();
    }

    //Get's.
    public int getCodigoDeMovimentacao() {
        return codigoDeMovimentacao;
    }


    public String getDescricao() {
        return descricao;
    }

    public Data getData() {
        return data;
    }


    public double getValor() {
        return valor;
    }

    //Set's.

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setData(Data data) {
        this.data = data;
    }

    //Equals e HashCode.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Saida saida = (Saida) o;
        return codigoDeMovimentacao == saida.codigoDeMovimentacao && Objects.equals(descricao, saida.descricao) && Objects.equals(data, saida.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoDeMovimentacao, descricao, data);
    }

    //toString.
    @Override
    public String toString() {
        return "Informações de saida:\n" +
                "  Codigo: "+getCodigoDeMovimentacao()+";\n" +
                "  Descrição: "+getDescricao()+";\n" +
                "  Data e hora: "+getData()+";\n" +
                "  Valor: "+getValor()+".\n" +
                "\n";
    }
}
