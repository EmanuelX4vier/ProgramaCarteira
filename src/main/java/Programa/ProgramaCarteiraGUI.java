package Programa;

import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProgramaCarteiraGUI extends JFrame {

    JLabel linha1, linha2, linha3;
    JPanel painelInformativo, painelDeMenu;
    ImageIcon carteiraImg = new ImageIcon("./img/carteira.png");
    JMenuBar barraDeMenu = new JMenuBar();
    SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();

    public ProgramaCarteiraGUI(){

        try {
            sistema.recuperarDados();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setLayout(new BorderLayout());

        setTitle("Sua carteira virtual");
        setSize(800, 600);
        setLocation(250,50);
        setResizable(true);
        getContentPane().setBackground(Color.GRAY);
        linha1 = new JLabel(sistema.getNomeDoUsuario(), JLabel.CENTER);
        linha1.setFont(new Font("Times New Roman", Font.BOLD, 24));
        linha1.setForeground(Color.LIGHT_GRAY);

        linha2 = new JLabel(String.valueOf("Saldo atual: "+sistema.getSaldoDoUsuario()), JLabel.CENTER);
        linha2.setFont(new Font("Times New Roman", Font.BOLD, 14));
        linha2.setForeground(Color.LIGHT_GRAY);
        linha3 = new JLabel(carteiraImg, JLabel.CENTER);

        painelInformativo = new JPanel();
        painelInformativo.setLayout(new GridLayout(2,1));
        painelInformativo.setBackground(Color.DARK_GRAY);
        painelInformativo.add(linha1);
        painelInformativo.add(linha2);
        add(linha3, BorderLayout.CENTER);
        add(painelInformativo, BorderLayout.SOUTH);


        JMenu menuRegistrar = new JMenu("Cadastrar");
        menuRegistrar.setFont(new Font("Times New Roman", Font.BOLD, 14));
        menuRegistrar.setForeground(Color.LIGHT_GRAY);

        JMenuItem menuRegistrarEntrada = new JMenuItem("Cadastrar entrada de dinheiro");
        menuRegistrarEntrada.setBackground(Color.DARK_GRAY);
        menuRegistrarEntrada.setFont(new Font("Times New Roman", Font.BOLD, 14));
        menuRegistrarEntrada.setForeground(Color.LIGHT_GRAY);
        menuRegistrar.add(menuRegistrarEntrada);


        JMenuItem menuRegistrarSaida = new JMenuItem("Cadastrar saída de dinheiro");
        menuRegistrarSaida.setBackground(Color.DARK_GRAY);
        menuRegistrarSaida.setFont(new Font("Times New Roman", Font.BOLD, 14));
        menuRegistrarSaida.setForeground(Color.LIGHT_GRAY);
        menuRegistrar.add(menuRegistrarSaida);

        barraDeMenu.add(menuRegistrar);
        barraDeMenu.setBackground(Color.DARK_GRAY);
        barraDeMenu.setSize(14,14);
        setJMenuBar(barraDeMenu);

    }

    public static void main (String [] args){
        JFrame janela = new ProgramaCarteiraGUI();
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
