package Programa.Transacoes;

import java.io.Serializable;
import java.util.Objects;

public abstract class MovimentoBase implements Serializable {

    private static final long serialVersionUID = 1L;
    private String descricao;
    private double valor;
    private final int codigoDeMovimentacao; // identificador único
    private final TipoMovimento tipo;       // ENTRADA ou SAIDA

    // Criação do movimento
    public MovimentoBase(int codigoDeMovimentacao, String descricao, double valor, TipoMovimento tipo) {
        this.codigoDeMovimentacao = codigoDeMovimentacao;
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
    }

    // Getters
    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public int getCodigoDeMovimentacao() {
        return codigoDeMovimentacao;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    // Setters
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    // Representação textual
    public String getMovimentoCompleto() {
        return tipo + " | Código: " + codigoDeMovimentacao + " | Valor: " + valor + " | Descrição: " + descricao;
    }

    // Equals e HashCode (usando código único)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoBase that = (MovimentoBase) o;
        return codigoDeMovimentacao == that.codigoDeMovimentacao;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigoDeMovimentacao);
    }

    @Override
    public String toString() {
        return getMovimentoCompleto();
    }
}
