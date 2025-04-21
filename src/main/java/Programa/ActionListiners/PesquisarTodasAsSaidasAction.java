package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisarTodasAsSaidasAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public PesquisarTodasAsSaidasAction(SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(sistema.getSaidasDoUsuario().isEmpty()){
            JOptionPane.showMessageDialog(janelaPrincipal,"Não existe saidas");
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal,sistema.getSaidasDoUsuario());
        }
    }
}
