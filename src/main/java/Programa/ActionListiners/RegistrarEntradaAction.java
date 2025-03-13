package Programa.ActionListiners;

import Programa.Sistema.SistemaDoGerenciadorFinanceiroParteDoUsuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrarEntradaAction implements ActionListener {

    private SistemaDoGerenciadorFinanceiroParteDoUsuario sistema = new SistemaDoGerenciadorFinanceiroParteDoUsuario();
    private JFrame janela = new JFrame("Registro de entrada.");

    public void actionPerformed(ActionEvent e) {

        /*sistema.registrarEntrada(sistema.getCodigoDoUsuario());*/
    }
}
