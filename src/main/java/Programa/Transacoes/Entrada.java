package Programa.Transacoes;

import Programa.Sistema.Data;

import java.io.Serializable;
import java.util.Objects;

public class Entrada extends MovimentoBase implements Serializable {

    private TipoDeMovimentacao tipo;
    private double valor;
    private String descricao;
    private Data data;
    private final int codigoDeMovimentacao;

    //Cria o movimento.
    public Entrada(TipoDeMovimentacao tipo, double valor, String descricao, Data data, int codigoDeMovimentacao) {
        super(valor, descricao);
        this.tipo = tipo;
        this.data = data;
        this.codigoDeMovimentacao = codigoDeMovimentacao;
    }
    public Entrada(){
        super(0, "Sem descrição");
        this.tipo = TipoDeMovimentacao.Indefinido;
        this.data = new Data();
        this.codigoDeMovimentacao = 0;
    }

    //get's e set's.
    public TipoDeMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeMovimentacao tipo) {
        this.tipo = tipo;
    }

    public int getCodigoDeMovimentacao() {
        return codigoDeMovimentacao;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public String getMovimentoCompleto() {
        return "Entrada de: "+getTipo()+"; No valor de: "+getValor()+"; Descrita como: "+getDescricao()+"; Na data: "+getData()+"; De código: "+getCodigoDeMovimentacao()+" .";
    }

    //equals e hashcode com codigo e tipo.
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Entrada entrada = (Entrada) o;
        return codigoDeMovimentacao == entrada.codigoDeMovimentacao && tipo == entrada.tipo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tipo, codigoDeMovimentacao);
    }


}
