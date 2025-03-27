package Programa.ActionListiners;

import Programa.Exceptions.EntradaJaRegistradaException;
import Programa.Exceptions.SaidaJaRegistradaException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarSaidaAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public RegistrarSaidaAction(SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }
    public void actionPerformed(ActionEvent e) {
        String descricao = JOptionPane.showInputDialog(janelaPrincipal, "Digite a descrição:");
        double valor = Double.parseDouble(JOptionPane.showInputDialog(janelaPrincipal, "Digite o valor"));
        try {
            sistema.registrarSaida(descricao, valor);
            JOptionPane.showMessageDialog(janelaPrincipal, "Registro feito com sucesso.");
        } catch (SaidaJaRegistradaException ex) {
            ex.getMessage();
        }
    }
}
