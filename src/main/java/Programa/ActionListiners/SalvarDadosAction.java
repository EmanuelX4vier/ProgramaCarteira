package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SalvarDadosAction implements ActionListener {
    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public SalvarDadosAction (SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sistema.salvarDados();
        } catch (IOException ex) {
            ex.getStackTrace();
        }
        JOptionPane.showMessageDialog(janelaPrincipal, "Dados salvos com sucesso, reinicie.");
    }
}
