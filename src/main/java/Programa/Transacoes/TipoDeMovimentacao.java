package Programa.Transacoes;

import java.io.Serializable;

public enum TipoDeMovimentacao implements Serializable {
    Indefinido, Cartao, Boleto, Compra, Recebimento, Deposito, Venda ;


    public TipoDeMovimentacao tipoDoMovimento(){
        return Indefinido;
    }

}
