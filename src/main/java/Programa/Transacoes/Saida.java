package Programa.Transacoes;

import Programa.Sistema.Data;

public class Saida extends MovimentoBase {

    private static final long serialVersionUID = 1L;
    private final Data data;

    public Saida(int codigoDeMovimentacao, String descricao, double valor) {
        super(codigoDeMovimentacao, descricao, valor, TipoMovimento.SAIDA);
        this.data = new Data(); // data do sistema
    }

    public Saida() {
        super(0, "Sem descrição", 0, TipoMovimento.SAIDA);
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
