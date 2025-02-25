package Programa;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.Data;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Sistema.Usuario;
import Programa.Transacoes.TipoDeMovimentacao;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class ProgramaCarteira {
    public static void main (String [] args) throws UsuarioJaCadastradoException, IOException, UsuarioNaoCadastradoException {
        //Inicialização e recuperação do usuario padrão.
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        Random random = new Random();
        try {
            sistema.recuperarDados();
            JOptionPane.showMessageDialog(null, "Sistema recuperado com sucesso!");
        } catch (IOException e) {
            e.getMessage();
            e.getStackTrace();
        }
        //Verifica se já existe alguém cadastrado.
        if (sistema.getNomeDoUsuario().equalsIgnoreCase("") || sistema.getNomeDoUsuario().equalsIgnoreCase("Sem nome")) {
            JOptionPane.showMessageDialog(null, "Olá usuário! Vamos nos cadastrar agora.");
            try {
                String nome = JOptionPane.showInputDialog(null, "Me diga seu nome:");
                double saldoCorrenteInicial = Double.parseDouble(JOptionPane.showInputDialog(null, "Agora me diga o seu saldo corrente principal:"));
                Usuario usuarioPrincipal = new Usuario(nome, saldoCorrenteInicial, random.nextInt());
                sistema.substitui_O_Usuario(usuarioPrincipal);
                JOptionPane.showMessageDialog(null, "Você foi cadastrado com sucesso! Aqui está seu código: " + sistema.getCodigoDoUsuario());
                sistema.salvarDados();
            } catch (UsuarioNaoCadastradoException | NumberFormatException e) {
                e.getMessage();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seja bem vindo de volta " + sistema.getNomeDoUsuario() + "! Segue suas atuais informações: \n" + sistema.getInformativo() + "\nVamos começar!");
        }
        //Inicia menu:
        boolean menu = true;
        while (menu){
            int opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "O que deseja fazer?\n" +
                    "1. Registrar nova entrada.\n" +
                    "2. Registrar nova saida\n" +
                    "3.Ver saldo\n" +
                    "4. Ver suas transações\n" +
                    "5. Sair"));
            switch (opcao){
                case 1:
                    int tipoCase1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ok, vamos registrar uma nova entrada em sua conta. Primeiro me diga qual o tipo da transação que será feita:\n" +
                            "1. Recebimento\n" +
                            "2. Deposito.\n" +
                            "3. Venda.\n" +
                            "4. Sair."));
                    int valorCase1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Agora o valor:"));
                    String descricaoCase1 = JOptionPane.showInputDialog(null, "E por fim a descrição:");
                    switch (tipoCase1){
                        case 1:
                            try{
                                sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Recebimento, valorCase1, descricaoCase1);
                                JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                                break;
                            } catch (Exception e) {
                                e.getStackTrace();
                                break;
                            }
                        case 2:
                            try{
                                sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Deposito, valorCase1, descricaoCase1);
                                JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                                break;
                            } catch (Exception e) {
                                e.getStackTrace();
                                break;
                            }
                        case 3:
                            try{
                                sistema.registrarEntrada(sistema.getCodigoDoUsuario(), TipoDeMovimentacao.Venda, valorCase1, descricaoCase1);
                                JOptionPane.showMessageDialog(null, "Registrado com sucesso!");
                                break;
                            } catch (Exception e) {
                                e.getStackTrace();
                                break;
                            }
                        case 4:
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Erro");
                            break;
                    }
                break;
                //Case 2
                default:
                    JOptionPane.showMessageDialog(null, "Erro");
                    break;
            }
            menu = false;
        }
        JOptionPane.showMessageDialog(null, sistema.getEntradasDoUsuario());
        JOptionPane.showMessageDialog(null, sistema.saldo());
        try{
            sistema.salvarDados();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erro");
            e.getStackTrace();

        }
    }

}
