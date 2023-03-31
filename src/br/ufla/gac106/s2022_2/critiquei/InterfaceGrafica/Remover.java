package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.Obra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
/**
 * Classe que inicializa os objetos da UI da parte de remover obras do sistema
 */
public class Remover extends InterfaceComMenu {
    private JPanel painelFeed;
    private JLabel imgIlustrativa;
    private JListComImagens jListComImagens;
    private JButton botaoOkPainelSelecao;


    /**
     * O construtor da classe Remover tem como tarefas principais:
     * - Iniciar o painel do feed, criar um painel para adicionar um botao e um
     * scrool e adicioná-los ao painel do feed
     * - Configura o botão de remover como o botão em destaque na classe interfaceComMenu
     * - adiciona a imagem ilustrativa da tela de remover
     */
    public Remover() {
        super("Remover");
        super.setBotaoEmDestaqueRemover();

        configuraPainelFeed();
        configuraImgIlustrativa();

        super.add(painelFeed);
        super.add(imgIlustrativa);
    }

    /**
     * Método responsável por configurar o painel de imagem ilustrativa.
     * Ele cria uma label nova com uma imagem pré-definida e seta suas coordenadas na tela.
     */
    private void configuraImgIlustrativa() {
        imgIlustrativa = new JLabel(new ImageIcon("img/imgsIlustrativas/PULP.png"));
        imgIlustrativa.setBounds(648, 105, 522, 522);

    }


    /**
     * Método responsável por configurar o painel do feed, onde serão exibidas as obras cadastradas no banco de dados.
     * Ele cria um novo JPanel, define as dimensões e cor de fundo, adiciona um layout de border e uma label com informações.
     * Ele cria também uma JListComImagens, que é uma classe que herda de JList e tem métodos para adicionar imagens a cada item.
     * Adiciona essa JList ao painel e adiciona um botão "REMOVER" para remover a obra selecionada.
     */
    private void configuraPainelFeed() {
        List<Obra> obrasCadastradas;
        obrasCadastradas = super.getSistema().ObrasCadastradas();
        painelFeed = new JPanel();
        painelFeed.setBounds(265, 32, 490, 668);
        painelFeed.setBackground(getCorFundoClaro());
        painelFeed.setLayout(new BorderLayout());

        JTextArea labelInfo = new JTextArea(
                "Para validar é preciso selecionar um item e depois clicar em SELECIONAR e então, em REMOVER");
        labelInfo.setWrapStyleWord(true);
        labelInfo.setLineWrap(true);
        labelInfo.setEditable(false);
        labelInfo.setFont(new Font("Inter", Font.BOLD, 14));
        painelFeed.add(labelInfo, BorderLayout.NORTH);

        jListComImagens = new JListComImagens(obrasCadastradas, painelFeed.getSize(), "Selecionar", false);
        painelFeed.add(jListComImagens.getPainelPrincipal(), BorderLayout.CENTER);

        botaoOkPainelSelecao = new JButton("REMOVER");
        botaoOkPainelSelecao.addActionListener(this);
        painelFeed.add(botaoOkPainelSelecao, BorderLayout.SOUTH);

    }

    /**
     * Esse método sobrescreve o método actionPerformed da classe InterfaceComMenu e é responsável por
     * tratar os eventos de clique do botão "REMOVER" dentro do painel de remoção de obras. Ele verifica
     * se uma obra foi selecionada na lista de obras antes de tentar removê-la e, caso tenha sido,
     * chama o método removeObra do sistema, passando o id da obra selecionada. Em caso de sucesso ou
     * falha, uma mensagem de feedback é exibida para o usuário.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoOkPainelSelecao) {
            if (jListComImagens.getObraSelecionada() == null) {
                Janela.criaJOptionPane("Selecione uma obra para remover", JOptionPane.WARNING_MESSAGE);
            } else if (super.getSistema().removeObra(jListComImagens.getObraSelecionada().getId())) {
                Janela.criaJOptionPane("Obra excluída.", JOptionPane.INFORMATION_MESSAGE);
            } else {
                Janela.criaJOptionPane("Não foi possível excluir", JOptionPane.ERROR_MESSAGE);
            }
        }
        super.actionPerformed(e);
    }
}
