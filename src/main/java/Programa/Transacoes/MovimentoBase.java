package Programa.Transacoes;

import java.io.Serializable;
import java.util.Objects;

public abstract class MovimentoBase implements Serializable {

    private double valor;
    private String descricao;

    //Criação.
    public MovimentoBase(double valor, String descricao){
        this.valor = valor;
        this.descricao = descricao;
    }

    //get's e set's.
    public abstract String getMovimentoCompleto();

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //equals, hashcode e toString.


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoBase that = (MovimentoBase) o;
        return Double.compare(valor, that.valor) == 0 && Objects.equals(descricao, that.descricao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor, descricao);
    }

    @Override
    public String toString() {
        return "Movimento no valor de: "+getValor()+"; Descrita como: "+getDescricao();
    }
}
