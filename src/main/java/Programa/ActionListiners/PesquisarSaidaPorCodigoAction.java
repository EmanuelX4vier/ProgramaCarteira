package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisarSaidaPorCodigoAction implements ActionListener {
    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public PesquisarSaidaPorCodigoAction (SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) throws NumberFormatException {
        if(sistema.getSaidasDoUsuario().isEmpty()){
            JOptionPane.showMessageDialog(janelaPrincipal,"Não existe entradas");
        }else{
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(janelaPrincipal, "Digite o código da entrada:"));
            for(Saida saida: sistema.getSaidasDoUsuario()){
                if(saida.getCodigoDeMovimentacao() == codigo){
                    JOptionPane.showMessageDialog(janelaPrincipal, saida);
                }else {
                    JOptionPane.showMessageDialog(janelaPrincipal, "Não existe saida ccom este código.");
                }
            }
        }
    }
}

