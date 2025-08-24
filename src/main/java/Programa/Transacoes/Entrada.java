package Programa.Transacoes;

import Programa.Sistema.Data;

public class Entrada extends MovimentoBase {

    private static final long serialVersionUID = 1L;
    private final Data data;

    public Entrada(int codigoDeMovimentacao, String descricao, double valor) {
        super(codigoDeMovimentacao, descricao, valor, TipoMovimento.ENTRADA);
        this.data = new Data(); // data do sistema
    }

    public Entrada() {
        super(0, "Sem descrição", 0, TipoMovimento.ENTRADA);
        this.data = new Data();
    }

    public Data getData() {
        return data;
    }

    @Override
    public String getMovimentoCompleto() {
        return super.getMovimentoCompleto() + " | Data e hora: " + data;
    }
}
