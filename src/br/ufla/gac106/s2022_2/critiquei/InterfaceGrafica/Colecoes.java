package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.Obra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

public class Colecoes extends InterfaceComMenu {
    //private final Sistema sistema = new Sistema();
    private JPanel painelFeed;
    private JLabel imgIlustrativa;

    /**
     * O construtor das Coleções tem como tarefas principais:
     * - Iniciar o painel do feed, criar um painel para adicionar um botao e um
     * scrool e adicioná-los ao painel do feed;
     * - É preciso iniciar também o painel de detalhes, o painel lateral que
     * mostrará os detalhes dos
     * filmes;
     */
    Colecoes() {
        super("Coleções");
        super.setBotaoEmDestaqueColecao();
        configuraPainelFeed();
        configuraImgIlustrativa();

        super.add(painelFeed);
        super.add(imgIlustrativa);
    }

    /**
     * Método responsável por adicionar uma imagem ilustrativa na tela de login.
     * Ele cria um JLabel com uma imagem que é setada como o recurso da aplicação
     * em "img/initPage/imgNotebook.png" e configura as suas dimensões e posição.
     * Por fim, ele adiciona o JLabel no painel da tela de login.
     */
    private void configuraImgIlustrativa() {
        imgIlustrativa = new JLabel(new ImageIcon("img/imgsIlustrativas/HP.png"));
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

        JListComImagens jlist = new JListComImagens(obrasCadastradas, new Dimension(painelFeed.getWidth(), 630), "Ver Detalhes ", true);
        JTextField buscar = new JTextField("Buscar");
        buscar.setForeground(getCorTextoMedio());
        buscar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (buscar.getText().equals("Buscar")) {
                    buscar.setText("");
                    buscar.setForeground(getCorTextoMedio());
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if (buscar.getText().isEmpty()) {
                    buscar.setForeground(getCorTextoMedio());
                    buscar.setText("Buscar");
                }
            }
            });
        buscar.setPreferredSize(new Dimension(300, 30));
        
        JButton filtrar = new JButton("F");
        filtrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlist.atualizaListaDeObras(Janela.getSistema().filtrarObras(buscar.getText()));
            }
        });
        painelFeed.add(buscar);
        painelFeed.add(filtrar);
        painelFeed.add(jlist.getPainelPrincipal());
    }
}
