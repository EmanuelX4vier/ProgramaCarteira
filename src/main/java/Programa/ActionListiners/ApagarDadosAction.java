package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApagarDadosAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public ApagarDadosAction (SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        sistema.apagarTodosOsDados();
        JOptionPane.showMessageDialog(janelaPrincipal, "Dados apagados com sucesso, salve e reinicie.");
    }
}