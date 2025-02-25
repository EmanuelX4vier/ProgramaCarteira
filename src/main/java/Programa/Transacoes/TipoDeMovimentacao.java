package Programa.Transacoes;

public enum TipoDeMovimentacao {
    Indefinido, Cartao, Boleto, Compra, Recebimento, Deposito, Venda ;


    public TipoDeMovimentacao tipoDoMovimento(){
        return Indefinido;
    }

}
