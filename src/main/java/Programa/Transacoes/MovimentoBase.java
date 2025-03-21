package Programa.Transacoes;

import java.io.Serializable;
import java.util.Objects;

public abstract class MovimentoBase implements Serializable {

    private String descricao;
    private double valor;


    //Criação.
    public MovimentoBase(String descricao, double valor){
        this.descricao = descricao;
        this.valor = valor;
    }

    //Get's.
    public String getDescricao() {
        return descricao;
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

    //Equals e HashCode.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoBase that = (MovimentoBase) o;
        return Double.compare(valor, that.valor) == 0 && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descricao, valor);
    }

    //toString.
    @Override
    public String toString() {
        return "MovimentoBase{" +
                "descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
