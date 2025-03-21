package Programa.Transacoes;

import Programa.Sistema.Data;
import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Saida extends MovimentoBase implements Serializable {

    private Random random;

    private final int codigoDeMovimentacao;
    private String descricao;
    private double valor;
    private Data data;

    //Cria o movimento.
    public Saida(int codigoDeMovimentacao, String descricao, double valor) {
        super(descricao, valor);

        this.random = new Random();

        this.codigoDeMovimentacao = codigoDeMovimentacao;
        this.data = new Data();
    }

    public Saida() {
        super("Sem descrição", 0);
        this.codigoDeMovimentacao = random.nextInt();
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
        return codigoDeMovimentacao == saida.codigoDeMovimentacao && Objects.equals(descricao, saida.descricao) && Objects.equals(data, saida.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), codigoDeMovimentacao, descricao, data);
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
