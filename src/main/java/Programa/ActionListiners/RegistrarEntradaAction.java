package Programa.ActionListiners;

import Programa.Exceptions.EntradaJaRegistradaException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarEntradaAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiro sistema;
    private JFrame janelaPrincipal;

    public RegistrarEntradaAction(SistemaDoGerenciadorFinanceiro sistema, JFrame janela){
        this.sistema = sistema;
        this.janelaPrincipal = janela;
    }

    public void actionPerformed(ActionEvent e) {
        String descricao = JOptionPane.showInputDialog(janelaPrincipal, "Digite a descrição:");
        double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor"));

        try {
            sistema.registrarEntrada(descricao, valor);
            JOptionPane.showMessageDialog(janelaPrincipal, "Registro feito com sucesso.");
        } catch (EntradaJaRegistradaException ex) {
            ex.getMessage();
        }
    }
}
