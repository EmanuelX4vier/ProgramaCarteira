package Programa.Transacoes;

import Programa.Sistema.Data;
import java.io.Serializable;
import java.util.Objects;

public class Saida extends MovimentoBase implements Serializable {

    private final int codigoDeMovimentacao;
    private String descricao;
    private Data data;
    private double valor;

    //Cria o movimento.
    public Saida(int codigoDeMovimentacao, String descricao, Data data, double valor) {
        super(descricao, valor);
        this.codigoDeMovimentacao = codigoDeMovimentacao;
        this.data = data;
    }

    public Saida() {
        super("Sem descrição", 0);
        this.codigoDeMovimentacao = 0;
        this.data = new Data();
    }

    //Get's.
    public int getCodigoDeMovimentacao() {
        return codigoDeMovimentacao;
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    public Data getData() {
        return data;
    }

    @Override
    public double getValor() {
        return valor;
    }

    //Set's.
    @Override
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
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
        if (!super.equals(o)) return false;
        Saida saida = (Saida) o;
        return codigoDeMovimentacao == saida.codigoDeMovimentacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigoDeMovimentacao);
    }

    //toString.
    @Override
    public String toString() {
        return "Saida{" +
                "codigoDeMovimentacao=" + codigoDeMovimentacao +
                ", descricao='" + descricao + '\'' +
                ", data=" + data +
                ", valor=" + valor +
                '}';
    }
}
