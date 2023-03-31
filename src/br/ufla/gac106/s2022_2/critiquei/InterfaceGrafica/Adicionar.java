package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.Filme;
import br.ufla.gac106.s2022_2.critiquei.Obra;
import br.ufla.gac106.s2022_2.critiquei.Serie;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Tela de adicionar uma obra ao sistema, onde o usuário pode escolher se a obra é um filme ou uma série.
 * e então autocomplete os campos com as informações da obra escolhida.
 */
public class Adicionar extends InterfaceComMenu {
    private JPanel painelFeed;
    private JLabel imgIlustrativa;

    private JRadioButton radioButtonSerie;
    private JRadioButton radioButtonFilme;
    private JTextField campoTitulo;
    private JTextField campoGenero;
    private JTextField campoData;
    private JTextField campoDuracao;
    private JTextField campoQuantidadeTemps;
    private JTextField campoQuantidadeEps;
    private JCheckBox checkBoxTemContinuacao;
    private JTextArea campoDescricao;
    private JButton botaoAutoComplete;
    private JButton botaoAdicionar;
    private JButton botaoOkPainelSelecao;
    private JListComImagens jListComImagens;


    Adicionar() {
        super("Adicionar");
        super.setBotaoEmDestaqueAdicionar();

        // configuraDadosListaObras();
        configuraPainelFeed();
        configuraImgIlustrativa();


        JScrollPane scrollAdd = new JScrollPane(painelFeed);
        super.add(scrollAdd);

        super.add(painelFeed);
        super.add(imgIlustrativa);
    }

    /**
     * Método responsavél por configurar a imagem ilustrativa da tela de adicionar.
     */
    private void configuraImgIlustrativa() {
        imgIlustrativa = new JLabel(new ImageIcon("img/imgsIlustrativas/TTNC.png"));
        imgIlustrativa.setBounds(648, 105, 522, 522);

    }

    /**
     * Método responsavél por configurar o painel de feed da tela de adicionar.
     * adicionando todos os componentes necessários para a tela de adicionar,
     * como os botões, campos de texto, etc.
     */
    private void configuraPainelFeed() {
        painelFeed = new JPanel();
        painelFeed.setBounds(265, 32, 500, 668);
        painelFeed.setBackground(getCorFundoClaro());

        JPanel painelAuxiliar = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        painelAuxiliar.setPreferredSize(new Dimension(500, 668));
        painelAuxiliar.setBackground(painelFeed.getBackground());
        // painelDoBotao.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // adiciona o label que pede qual tipo de conteudo o usuario quer
        JLabel labelTipoConteudo = new JLabel("Tipo de conteúdo");
        labelTipoConteudo.setSize(450, 20);
        labelTipoConteudo.setFont(new Font("Inter", Font.PLAIN, 15));
        labelTipoConteudo.setForeground(Janela.getCorTextoClaro());
        labelTipoConteudo.setHorizontalAlignment(SwingConstants.CENTER);
        painelAuxiliar.add(labelTipoConteudo);
        // ==================================================================

        // adiciona os radio buttons para escolher entre um e outro
        radioButtonSerie = new JRadioButton("Série");
        radioButtonFilme = new JRadioButton("Filme");
        ButtonGroup grupoTvSerie = new ButtonGroup();
        JPanel painelRadioButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
        painelRadioButtons.setSize(450, 20);
        painelRadioButtons.setFont(new Font("Inter", Font.PLAIN, 15));
        painelRadioButtons.setBackground(painelFeed.getBackground());
        painelRadioButtons.setForeground(Janela.getCorTextoClaro());

        grupoTvSerie.add(radioButtonFilme);
        grupoTvSerie.add(radioButtonSerie);

        painelRadioButtons.add(radioButtonFilme);
        painelRadioButtons.add(radioButtonSerie);
        painelAuxiliar.add(painelRadioButtons);
        // ==============================================================

        // adiciona input titulo;
        campoTitulo = new JTextField();
        configuraJTextComponent(campoTitulo, new Dimension(400, 45), "Título");
        painelAuxiliar.add(campoTitulo);
        // ==============================================================

        // adiciona botao auto completar
        botaoAutoComplete = new JButton("Autocompletar", new ImageIcon("img/icons/iconeAutoComp.png"));
        botaoAutoComplete.setBackground(getForeground());
        botaoAutoComplete.setPreferredSize(new Dimension(200, 35));
        botaoAutoComplete.setOpaque(true);
        botaoAutoComplete.setBackground(getCorComponente());
        botaoAutoComplete.setForeground(getCorTextoClaro());
        botaoAutoComplete.setFont(new Font("Inter", Font.BOLD, 14));
        botaoAutoComplete.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAutoComplete.setIconTextGap(6);
        botaoAutoComplete.setHorizontalTextPosition(SwingConstants.LEFT);
        painelAuxiliar.add(botaoAutoComplete);
        // ===============================================================

        // adiciona os campos do genero e da data um ao lado do outro
        JPanel painelGeneroEData = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        painelGeneroEData.setFont(new Font("Inter", Font.PLAIN, 15));
        painelGeneroEData.setBackground(painelFeed.getBackground());
        painelGeneroEData.setForeground(Janela.getCorTextoClaro());
        painelGeneroEData.setSize(400, 50);

        campoGenero = new JTextField();
        configuraJTextComponent(campoGenero, new Dimension(100, 45), "Gênero");
        painelGeneroEData.add(campoGenero);

        campoData = new JTextField();
        configuraJTextComponent(campoData, new Dimension(130, 45), "Data Lançamento");
        painelGeneroEData.add(campoData);

        checkBoxTemContinuacao = new JCheckBox("Tem continuação");
        painelGeneroEData.add(checkBoxTemContinuacao);


        painelAuxiliar.add(painelGeneroEData);
        // ================================================================================

        // adiciona os campos da duracao e da quant temps um ao lado do outro
        JPanel painelDuracaoTemps = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
        painelDuracaoTemps.setFont(new Font("Inter", Font.PLAIN, 15));
        painelDuracaoTemps.setBackground(painelFeed.getBackground());
        painelDuracaoTemps.setForeground(Janela.getCorTextoClaro());
        painelDuracaoTemps.setSize(400, 50);

        campoDuracao = new JTextField();
        configuraJTextComponent(campoDuracao, new Dimension(183, 45), "Duração do filme");
        painelDuracaoTemps.add(campoDuracao);

        campoQuantidadeTemps = new JTextField();
        configuraJTextComponent(campoQuantidadeTemps, new Dimension(105, 45), "Quant. temps");
        painelDuracaoTemps.add(campoQuantidadeTemps);

        campoQuantidadeEps = new JTextField();
        configuraJTextComponent(campoQuantidadeEps, new Dimension(90, 45), "Quant. eps");
        painelDuracaoTemps.add(campoQuantidadeEps);

        painelAuxiliar.add(painelDuracaoTemps);

        // =======================================================================================

        // adiciona painel descricao
        campoDescricao = new JTextArea();
        configuraJTextComponent(campoDescricao, new Dimension(400, 280), "Descrição da obra");
        campoDescricao.setWrapStyleWord(true);
        campoDescricao.setLineWrap(true);

        painelAuxiliar.add(campoDescricao);

        // adiciona botao de add
        botaoAdicionar = new JButton("Adicionar", new ImageIcon("img/icons/iconCheck.png"));
        botaoAdicionar.setBackground(getForeground());
        botaoAdicionar.setPreferredSize(new Dimension(200, 35));
        botaoAdicionar.setOpaque(true);
        botaoAdicionar.setBackground(getCorComponente());
        botaoAdicionar.setForeground(getCorTextoClaro());
        botaoAdicionar.setFont(new Font("Inter", Font.BOLD, 14));
        botaoAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoAdicionar.setIconTextGap(6);
        botaoAdicionar.setHorizontalTextPosition(SwingConstants.LEFT);
        painelAuxiliar.add(botaoAdicionar);
        // =================================================

        painelFeed.add(painelAuxiliar);

        radioButtonFilme.addActionListener(this);
        radioButtonSerie.addActionListener(this);
        campoTitulo.addActionListener(this);
        campoGenero.addActionListener(this);
        campoData.addActionListener(this);
        campoDuracao.addActionListener(this);
        campoQuantidadeTemps.addActionListener(this);
        botaoAutoComplete.addActionListener(this);
        botaoAdicionar.addActionListener(this);
    }

    /**
     * Configura o JTextComponent para o padrão do programa.
     *
     * @param textField O JTextComponent a ser configurado, podendo ser qualquer um dos que herdam de JTextComponent.
     * @param d
     * @param titulo
     */
    private void configuraJTextComponent(JTextComponent textField, Dimension d, String titulo) {
        textField.setPreferredSize(d);
        textField.setBackground(painelFeed.getBackground());
        textField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(getCorTextoMedio(), 1), titulo, TitledBorder.LEFT, TitledBorder.TOP, new Font("Inter", Font.PLAIN, 13), getCorTextoClaro()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (radioButtonFilme.isSelected()) {
            campoQuantidadeTemps.setEnabled(false);
            campoQuantidadeTemps.setText("");
            campoQuantidadeEps.setEnabled(false);
            campoQuantidadeEps.setText("");
            campoDuracao.setEnabled(true);
        } else if (radioButtonSerie.isSelected()) {
            campoQuantidadeEps.setEnabled(true);
            campoQuantidadeTemps.setEnabled(true);
            campoDuracao.setEnabled(false);
            campoDuracao.setText("");
            checkBoxTemContinuacao.setEnabled(false);
        }

        if (e.getSource() == botaoAutoComplete) {
            ArrayList<Obra> obras = super.getSistema().buscaObra(campoTitulo.getText());
            jListComImagens = configuraPainelSeleção(obras);

        }

        if (e.getSource() == botaoOkPainelSelecao) {
            try {
                campoTitulo.setText(jListComImagens.getObraSelecionada().getNome());
                campoDescricao.setText(jListComImagens.getObraSelecionada().getDescricao());
                campoData.setText(jListComImagens.getObraSelecionada().getDataLancamento());

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = formatter.format(jListComImagens.getObraSelecionada().getDataLancamento());
                campoData.setText(formattedDate);

                System.out.println("+" + campoGenero.getText() + "+");
            } catch (IndexOutOfBoundsException excep) {
                Janela.criaJOptionPane("Não foram encontradas respostas para esse título", JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException excep) {
                campoData.setText("");
            }
        }

        if (e.getSource() == botaoAdicionar) {
            Obra obra = null;
            // se os campos estao sem texto
            if ((campoTitulo.getText().equals("") || campoGenero.getText().equals("") || campoData.getText().equals("") || campoDescricao.getText().equals("")) && (campoDuracao.getText().equals("") && campoQuantidadeTemps.getText().equals(""))) {
                Janela.criaJOptionPane("Você precisa preencher todos os campos", JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                try {
                    if (radioButtonFilme.isSelected()) {
                        obra = new Filme(0, campoTitulo.getText(), campoDescricao.getText(), jListComImagens.getObraSelecionada().getPathPoster(), // pega a imagem selecionada para
                                // ser
                                // adicionada na descricao
                                jListComImagens.getObraSelecionada().getUrlImgIlustrativa(), // pega a imagem
                                // selecionada
                                // para ser adicionada na
                                // descricao
                                campoData.getText(), campoGenero.getText(), "F", Integer.parseInt(campoDuracao.getText()), checkBoxTemContinuacao.isSelected());
                    } else if (radioButtonSerie.isSelected()) {
                        obra = new Serie(0, campoTitulo.getText(), campoDescricao.getText(), jListComImagens.getObraSelecionada().getPathPoster(), // pega a imagem selecionada para
                                // ser
                                // adicionada na descricao
                                jListComImagens.getObraSelecionada().getUrlImgIlustrativa(), // pega a imagem
                                // selecionada
                                // para ser adicionada na
                                // descricao
                                campoData.getText(), campoGenero.getText(), "S", Integer.parseInt(campoQuantidadeTemps.getText()), Integer.parseInt(campoQuantidadeEps.getText()));
                    } else {
                        Janela.criaJOptionPane("Você precisa escolher o tipo de conteúdo, série ou filme", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    //se der null pointer exception, é pq o usuario nao selecionou nenhuma obra na lista
                } catch (NullPointerException exp) {
                    Janela.criaJOptionPane("Você precisa utilizar o sistema de autocompletar", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

            // finalmente, adiciona a obra
            if (!super.getSistema().adicionaObra(obra)) {
                Janela.criaJOptionPane("Houve algum erro ao adicionar essa série", JOptionPane.ERROR_MESSAGE);
            } else {
                Janela.criaJOptionPane("O conteúdo foi adicionado!", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        super.actionPerformed(e);
    }

    private JListComImagens configuraPainelSeleção(ArrayList<Obra> obras) {
        JFrame painelSelecao = new JFrame();
        painelSelecao.setLayout(new BorderLayout());
        painelSelecao.setSize(400, 600);
        painelSelecao.setLocationRelativeTo(null);

        JTextArea labelInfo = new JTextArea("Para validar é preciso selecionar um item e depois clicar em SELECIONAR e depois em OK");
        labelInfo.setWrapStyleWord(true);
        labelInfo.setLineWrap(true);
        labelInfo.setEditable(false);
        labelInfo.setFont(new Font("Inter", Font.BOLD, 14));
        painelSelecao.add(labelInfo, BorderLayout.NORTH);

        jListComImagens = new JListComImagens(obras, painelSelecao.getSize(), "Selecionar", false);
        painelSelecao.add(jListComImagens.getPainelPrincipal(), BorderLayout.CENTER);

        botaoOkPainelSelecao = new JButton("OK");
        botaoOkPainelSelecao.addActionListener(this);
        painelSelecao.add(botaoOkPainelSelecao, BorderLayout.SOUTH);
        painelSelecao.setVisible(true);
        return jListComImagens;

    }
}
