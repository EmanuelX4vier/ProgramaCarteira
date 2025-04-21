package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Transacoes.Entrada;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisarEntradaPorCodigoAction implements ActionListener {
    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public PesquisarEntradaPorCodigoAction(SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        if(sistema.getEntradasDoUsuario().isEmpty()){
            JOptionPane.showMessageDialog(janelaPrincipal,"Não existe entradas");
        }else{
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, "Digite o código da entrada:"));
            for(Entrada entrada: sistema.getEntradasDoUsuario()){
                if(entrada.getCodigoDeMovimentacao() == codigo){
                    JOptionPane.showMessageDialog(janelaPrincipal, entrada);
                }else {
                    JOptionPane.showMessageDialog(janelaPrincipal, "Não existe entrada ccom este código.");
                }
            }
    }
}
}
