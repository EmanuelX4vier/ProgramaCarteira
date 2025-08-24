package Interface_Grafica;

import Programa.ActionListiners.*;
import Programa.Sistema.SistemaDoGerenciadorFinanceiro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Tela extends JFrame {

    private final SistemaDoGerenciadorFinanceiro sistema;

    private JLabel backgroundLabel;
    private JLabel nomeDoUsuario;
    private JLabel saldoDoUsuario;
    private JLabel valorDeEntradas;
    private JLabel quantidadeDeEntradas;
    private JLabel valorDeSaidas;
    private JLabel quantidadeDeSaidas;

    private JMenuBar barraDeMenu;
    private JMenu menuRegistrar, menuPesquisar, menuOutro;
    private JMenuItem registrarEntrada, registrarSaida;
    private JMenuItem pesquisarTodasAsEntradas, pesquisarTodasAsSaidas;
    private JMenuItem pesquisarEntradaPorCodigo, pesquisarSaidaPorCodigo;
    private JMenuItem apagarDados, salvarDados;

    private ImageIcon backgroundImage;
    private JPanel painelBase;

    public Tela(SistemaDoGerenciadorFinanceiro sistema) {
        this.sistema = sistema;
        initComponents();
        inicializarListeners();
        atualizarInformacoes();
        iniciarAtualizacaoAutomatica();
    }

    private void initComponents() {
        setTitle("Gerenciador Financeiro");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        // Painel base com cor de fundo
        painelBase = new JPanel(null);
        painelBase.setBackground(new Color(0xFFFFFFFF, true)); // Fundo cinza claro
        painelBase.setBounds(0, 0, 800, 600);
        add(painelBase);

        // Imagem centralizada
        backgroundImage = new ImageIcon("./img/carteira.png");
        backgroundLabel = new JLabel(backgroundImage);
        painelBase.add(backgroundLabel);

        // Nome do usuário
        nomeDoUsuario = new JLabel("", SwingConstants.CENTER);
        nomeDoUsuario.setFont(new Font("Segoe UI", Font.BOLD, 24));
        nomeDoUsuario.setForeground(Color.BLACK); // Preto
        painelBase.add(nomeDoUsuario);

        // Saldo
        saldoDoUsuario = new JLabel("", SwingConstants.CENTER);
        saldoDoUsuario.setFont(new Font("Segoe UI", Font.BOLD, 20));
        saldoDoUsuario.setForeground(new Color(0x1565C0)); // Azul harmônico
        painelBase.add(saldoDoUsuario);

        // Entradas
        valorDeEntradas = new JLabel("", SwingConstants.LEFT);
        valorDeEntradas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valorDeEntradas.setForeground(new Color(0x2E7D32)); // Verde harmônico
        quantidadeDeEntradas = new JLabel("", SwingConstants.LEFT);
        quantidadeDeEntradas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        quantidadeDeEntradas.setForeground(new Color(0x388E3C));
        painelBase.add(valorDeEntradas);
        painelBase.add(quantidadeDeEntradas);

        // Saídas
        valorDeSaidas = new JLabel("", SwingConstants.RIGHT);
        valorDeSaidas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        valorDeSaidas.setForeground(new Color(0xC62828)); // Vermelho harmônico
        quantidadeDeSaidas = new JLabel("", SwingConstants.RIGHT);
        quantidadeDeSaidas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        quantidadeDeSaidas.setForeground(new Color(0xD32F2F));
        painelBase.add(valorDeSaidas);
        painelBase.add(quantidadeDeSaidas);

        // Barra de menu
        barraDeMenu = new JMenuBar();

        menuRegistrar = new JMenu("Registrar");
        registrarEntrada = new JMenuItem("Registrar Entrada");
        registrarSaida = new JMenuItem("Registrar Saída");
        menuRegistrar.add(registrarEntrada);
        menuRegistrar.add(registrarSaida);

        menuPesquisar = new JMenu("Pesquisa");
        pesquisarTodasAsEntradas = new JMenuItem("Pesquisar Entradas");
        pesquisarTodasAsSaidas = new JMenuItem("Pesquisar Saídas");
        pesquisarEntradaPorCodigo = new JMenuItem("Pesquisar Entradas Por Código");
        pesquisarSaidaPorCodigo = new JMenuItem("Pesquisar Saídas Por Código");
        menuPesquisar.add(pesquisarTodasAsEntradas);
        menuPesquisar.add(pesquisarTodasAsSaidas);
        menuPesquisar.add(pesquisarEntradaPorCodigo);
        menuPesquisar.add(pesquisarSaidaPorCodigo);

        menuOutro = new JMenu("Outros");
        apagarDados = new JMenuItem("Apagar Dados");
        salvarDados = new JMenuItem("Salvar Dados");
        menuOutro.add(apagarDados);
        menuOutro.add(salvarDados);

        barraDeMenu.add(menuRegistrar);
        barraDeMenu.add(menuPesquisar);
        barraDeMenu.add(menuOutro);

        setJMenuBar(barraDeMenu);

        // Tamanho inicial
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Ajusta tudo ao redimensionar
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                redimensionarComponentes();
            }
        });
    }

    private void inicializarListeners() {
        registrarEntrada.addActionListener(new RegistrarEntradaAction(sistema, this));
        registrarSaida.addActionListener(new RegistrarSaidaAction(sistema, this));
        pesquisarTodasAsEntradas.addActionListener(new PesquisarTodasAsEntradasAction(sistema, this));
        pesquisarTodasAsSaidas.addActionListener(new PesquisarTodasAsSaidasAction(sistema, this));
        pesquisarEntradaPorCodigo.addActionListener(new PesquisarEntradaPorCodigoAction(sistema, this));
        pesquisarSaidaPorCodigo.addActionListener(new PesquisarSaidaPorCodigoAction(sistema, this));
        apagarDados.addActionListener(new ApagarDadosAction(sistema, this));
        salvarDados.addActionListener(new SalvarDadosAction(sistema, this));
    }

    public void atualizarInformacoes() {
        nomeDoUsuario.setText(sistema.getNomeDoUsuario());
        saldoDoUsuario.setText(String.format("Saldo: %.2f R$", sistema.calcularSaldo()));

        valorDeEntradas.setText(String.format("Entradas: %.2f R$", sistema.getValorDeTodasAsEntradas()));
        quantidadeDeEntradas.setText("Qtd: " + sistema.getEntradasDoUsuario().size());

        valorDeSaidas.setText(String.format("Saídas: %.2f R$", sistema.getValorDeTodasAsSaidas()));
        quantidadeDeSaidas.setText("Qtd: " + sistema.getSaidasDoUsuario().size());
    }

    private void redimensionarComponentes() {
        int w = getWidth();
        int h = getHeight();

        painelBase.setBounds(0, 0, w, h);

        // Imagem centralizada
        int imgW = backgroundImage.getIconWidth();
        int imgH = backgroundImage.getIconHeight();
        backgroundLabel.setBounds((w - imgW) / 2, (h - imgH) / 3, imgW, imgH);

        // Nome centralizado
        nomeDoUsuario.setBounds(0, h / 12, w, 40);

        // Saldo logo abaixo do nome
        saldoDoUsuario.setBounds(0, h / 12 + 50, w, 30);

        // Entradas lado esquerdo
        valorDeEntradas.setBounds(w / 10, h / 2, 300, 30);
        quantidadeDeEntradas.setBounds(w / 10, h / 2 + 30, 300, 30);

        // Saídas lado direito
        valorDeSaidas.setBounds(w - w / 10 - 300, h / 2, 300, 30);
        quantidadeDeSaidas.setBounds(w - w / 10 - 300, h / 2 + 30, 300, 30);
    }

    private void iniciarAtualizacaoAutomatica() {
        Timer timer = new Timer(500, e -> atualizarInformacoes());
        timer.start();
    }

    public static void main(String[] args) {
        SistemaDoGerenciadorFinanceiro sistema = new SistemaDoGerenciadorFinanceiro();
        SwingUtilities.invokeLater(() -> {
            Tela tela = new Tela(sistema);
            tela.setVisible(true);
        });
    }
}
