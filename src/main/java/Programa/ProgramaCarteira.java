package Programa;

import Programa.Exceptions.UsuarioJaCadastradoException;
import Programa.Exceptions.UsuarioNaoCadastradoException;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;
import Programa.Sistema.Usuario;

import javax.swing.*;
import java.io.IOException;
import java.util.Random;

public class ProgramaCarteira {
    public static void main (String [] args) throws UsuarioJaCadastradoException, IOException, UsuarioNaoCadastradoException {
        //Inicialização e recuperação do usuario padrão.
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        Random random = new Random();
        try{
            sistema.recuperarDados();
            JOptionPane.showMessageDialog(null, "Sistema recuperado com sucesso!");
        } catch (IOException e) {
            e.getMessage();
            e.getStackTrace();
        }
        //Verifica se já existe alguém cadastrado.
        if(sistema.getNomeDoUsuario().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "Olá usuário! Vamos nos cadastrar agora.");
            try{
                String nome = JOptionPane.showInputDialog(null, "Me diga seu nome:");
                double saldoCorrenteInicial = Double.parseDouble(JOptionPane.showInputDialog(null, "Agora me diga o seu saldo corrente principal:"));
                Usuario usuarioPrincipal = new Usuario(nome, saldoCorrenteInicial, random.nextInt());
                sistema.substitui_O_Usuario(usuarioPrincipal);
                JOptionPane.showMessageDialog(null, "Você foi cadastrado com sucesso! Aqui está seu código: "+sistema.getCodigoDoUsuario());
                sistema.salvarDados();
            } catch (UsuarioNaoCadastradoException | NumberFormatException e) {
                e.getMessage();
            }
        }else{
            JOptionPane.showMessageDialog(null, "Seja bem vindo de volta "+sistema.getNomeDoUsuario()+" !");
        }

    }
}
