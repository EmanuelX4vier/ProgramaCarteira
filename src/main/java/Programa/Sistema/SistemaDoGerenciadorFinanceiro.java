package Programa.Sistema;

import Programa.Exceptions.EntradaJaRegistradaException;
import Programa.Exceptions.SaidaJaRegistradaException;
import Programa.Transacoes.Entrada;
import Programa.Transacoes.Saida;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class SistemaDoGerenciadorFinanceiro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Usuario usuarioPrincipal;
    private final GravadorDeDados gravador;
    private final Random random;

    public SistemaDoGerenciadorFinanceiro() {
        this.gravador = new GravadorDeDados();
        this.random = new Random();
        inicializarUsuarioSeNaoExistir();
    }

    /**
     * Inicializa o usuário caso ele ainda não exista.
     * Se existir, mantém o usuário atual.
     */
    public void inicializarUsuarioSeNaoExistir() {
        if (usuarioPrincipal != null) return; // já existe
        try {
            this.usuarioPrincipal = gravador.recuperaDados();
            JOptionPane.showMessageDialog(null, "Sistema recuperado com sucesso.");
        } catch (IOException e) {
            criarNovoUsuario(); // cria novo usuário
        }
    }

    /**
     * Cria um novo usuário pedindo dados via JOptionPane.
     */
    private void criarNovoUsuario() {
        JOptionPane.showMessageDialog(null, "Nenhum usuário encontrado. Vamos criar um novo usuário.");

        String nome = JOptionPane.showInputDialog(null, "Digite seu nome:");
        while (nome == null || nome.trim().isEmpty()) {
            nome = JOptionPane.showInputDialog(null, "Nome inválido. Digite seu nome:");
        }

        double saldo = 0;
        boolean saldoValido = false;
        while (!saldoValido) {
            try {
                String saldoStr = JOptionPane.showInputDialog(null, "Digite o saldo inicial da sua conta:");
                saldo = Double.parseDouble(saldoStr);
                saldoValido = true;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Valor inválido. Digite um número válido.");
            }
        }

        this.usuarioPrincipal = new Usuario(nome, saldo);

        try {
            gravador.gravaDados(usuarioPrincipal);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar usuário: " + ioException.getMessage());
        }
    }

    // Métodos de cadastro e substituição de usuário
    public void cadastraUsuario(String nome, double saldoCorrente) {
        this.usuarioPrincipal = new Usuario(nome, saldoCorrente);
    }

    public void substituiUsuario(Usuario usuario) {
        this.usuarioPrincipal = usuario;
    }

    // Registro de entradas e saídas
    public void registrarEntrada(String descricao, double valor) throws EntradaJaRegistradaException {
        int novoCodigo = random.nextInt();
        if (usuarioPrincipal.verificarTransacao(novoCodigo, descricao)) {
            throw new EntradaJaRegistradaException("Já existe uma entrada com este código e descrição.");
        }
        Entrada entrada = new Entrada(novoCodigo, descricao, valor);
        usuarioPrincipal.adicionarTransacao(entrada);
    }

    public void registrarSaida(String descricao, double valor) throws SaidaJaRegistradaException {
        int novoCodigo = random.nextInt();
        if (usuarioPrincipal.verificarTransacao(novoCodigo, descricao)) {
            throw new SaidaJaRegistradaException("Já existe uma saída com este código e descrição.");
        }
        Saida saida = new Saida(novoCodigo, descricao, valor);
        usuarioPrincipal.adicionarTransacao(saida);
    }

    // Getters e setters básicos
    public String getNomeDoUsuario() {
        return usuarioPrincipal.getNome();
    }

    public void setNomeDoUsuario(String novoNome) {
        usuarioPrincipal.setNome(novoNome);
    }

    public double getSaldoDoUsuario() {
        return usuarioPrincipal.getSaldoCorrente();
    }

    public void setSaldoDoUsuario(double novoSaldo) {
        usuarioPrincipal.setSaldoCorrente(novoSaldo);
    }

    // Entradas e saídas
    public List<Entrada> getEntradasDoUsuario() {
        return usuarioPrincipal.getEntradas();
    }

    public List<Saida> getSaidasDoUsuario() {
        return usuarioPrincipal.getSaidas();
    }

    public double getValorDeTodasAsEntradas() {
        return usuarioPrincipal.getValorDeTodasAsEntradas();
    }

    public double getValorDeTodasAsSaidas() {
        return usuarioPrincipal.getValorTotalDeTodasAsSaidas();
    }

    // Operações com o saldo
    public double calcularSaldo() {
        return getSaldoDoUsuario() + (getValorDeTodasAsEntradas() - getValorDeTodasAsSaidas());
    }

    // Gravação de dados
    public void salvarDados() throws IOException {
        gravador.gravaDados(usuarioPrincipal);
    }

    // Apaga todos os dados do usuário
    public void apagarTodosOsDados() {
        usuarioPrincipal.setSaldoCorrente(0);
        usuarioPrincipal.setTransacoes(new java.util.HashMap<>());
    }
}
