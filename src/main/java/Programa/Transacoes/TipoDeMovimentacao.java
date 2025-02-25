package Programa.Transacoes;

public enum TipoDeMovimentacao {
    Fatura, Luz, Agua, Internet, Academia, Salario, RecebimentoQualquer, Compra, Venda, Passagem, Comida, PagamentoQualquer, Indefinido;


    public TipoDeMovimentacao tipoDoMovimento(){
        return Indefinido;
    }

}
