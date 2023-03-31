package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.Sistema;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

/**
 * Classe responsável por criar a janela principal da interface gráfica. Essa classe é responsável por
 * configurar o layout da janela, adicionar o título, botões de fechar e minimizar, além de definir as cores
 * padrões utilizadas na interface.
 */
public class Janela extends JFrame implements ActionListener {

    // Cores padrões utilizadas na interface
    private final static Color corFundoEscuro = Color.decode("#161A1F"); //*
    private final static Color corFundoClaro = Color.decode("#20272E"); //
    private final static Color corComponente = Color.decode("#414850"); //
    private final static Color corTextoMedio = Color.decode("#AEB8C2"); //
    private final static Color corTextoClaro = Color.decode("#FFFFFF"); //
    private final static Color corVerde = Color.decode("#11BC40");
    private final static Color corVermelho = Color.decode("#CF6800");
    private final static Color corAmarelo = Color.decode("#FFF4B9");
    private final static Color corAzul = Color.decode("#1F9ED8");
    // Usuario usuarioAtual;
    private JButton botaoFechar;
    private JButton botaoMinimizar;

    private static Sistema sistema;

    // cria janela com 1200x800

    public Janela(String titulo) {
        super.setTitle(titulo);
        super.setSize(1200, 700);
        super.setUndecorated(true);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        super.getContentPane().setBackground(getCorFundoEscuro());

        try {
            Image icon = ImageIO.read(new File("img/icons/logoJanela.png"));
            setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }


        super.setLocationRelativeTo(null);
        // bloquear redimensionamento
        super.setLayout(null);
        super.setResizable(false);


        if(sistema == null) { //caso nao tenha sido criado um sistema, cria-se
            sistema = new Sistema();
        }
        adicionaTopTab();

    }
    public static Color getCorFundoEscuro() {
        return corFundoEscuro;
    }

    public static Color getCorFundoClaro() {
        return corFundoClaro;
    }

    public static Color getCorComponente() {
        return corComponente;
    }

    public static Color getCorTextoMedio() {
        return corTextoMedio;
    }

    public static Color getCorTextoClaro() {
        return corTextoClaro;
    }

    public static Color getCorVerde() {
        return corVerde;
    }

    public static Color getCorVermelho() {
        return corVermelho;
    }

    public static Color getCorAmarelo() {
        return corAmarelo;
    }

    public static Color getCorAzul() {
        return corAzul;
    }

    /**
     * Cria um JoptionPane com a mensagem passada como parâmetro e o tipo de mensagem passado como parâmetro.
     * @param mensagem
     * @param tipoMensagem
     */
    public static void criaJOptionPane(String mensagem, int tipoMensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "",
                tipoMensagem);
    }

    /**
     * Método responsável por adicionar o botão de fechar e minimizar na janela.
     * @param urlPath - uma urlque contem uma image
     * @return - retorna uma imagem(Icon) a partir de uma url
     */
    public static ImageIcon imagemDeURL(String urlPath) {
        URL url = null;
        ImageIcon topBar;
        try {
            url = new URL(urlPath);
        } catch (MalformedURLException e) {
            topBar = null;
        }
        topBar = new ImageIcon(url);
        return topBar;
    }

    /**
     * SObrecarregado que troca o titulo da janela para um jeito personalizado no canto sup esq.
     */
    @Override
    public void setTitle(String title) {
        JLabel titulo = new JLabel(title);
        titulo.setFont(new Font("Inter", Font.BOLD, 18));
        titulo.setForeground(getCorTextoMedio());
        titulo.setBounds(11, 0, 90, 32);
        titulo.setHorizontalAlignment(SwingConstants.LEFT);
        add(titulo);
    }

    /**
     * Método responsável por adicionar o botão de fechar e minimizar na janela.
     */
    private void adicionaTopTab() {
        botaoFechar = new JButton(new ImageIcon("img/icons/iconeFechar.png"));
        botaoFechar.setBounds(1170, 8, 16, 16);
        botaoFechar.setBorderPainted(false);
        botaoFechar.setContentAreaFilled(false);
        botaoFechar.setFocusPainted(false);
        add(botaoFechar);

        botaoMinimizar = new JButton(new ImageIcon("img/icons/iconeMinimizar.png"));
        botaoMinimizar.setBounds(1145, 8, 16, 16);
        botaoMinimizar.setBorderPainted(false);
        botaoMinimizar.setContentAreaFilled(false);
        botaoMinimizar.setFocusPainted(false);
        add(botaoMinimizar);

        botaoFechar.addActionListener(this);
        botaoMinimizar.addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoFechar) {
            this.dispose();
        } else if (e.getSource() == botaoMinimizar) {
            setState(JFrame.ICONIFIED);
        }
    }

    public static Sistema getSistema() {
        return sistema;
    }
}
