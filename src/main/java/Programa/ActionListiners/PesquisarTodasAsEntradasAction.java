package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisarTodasAsEntradasAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public PesquisarTodasAsEntradasAction (SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(sistema.getEntradasDoUsuario().isEmpty()){
            JOptionPane.showMessageDialog(janelaPrincipal,"Não existe entradas");
        }else{
            JOptionPane.showMessageDialog(janelaPrincipal,sistema.getEntradasDoUsuario());
        }
    }
}
