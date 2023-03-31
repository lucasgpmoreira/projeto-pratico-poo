package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import br.ufla.gac106.s2022_2.critiquei.Comentario;
import br.ufla.gac106.s2022_2.critiquei.Obra;

/**
 * Essa classe é responsável por criar uma janela com os detalhes de uma obra. Ela contém um ScrollPane onde é
 * adicionado um JPanel com os detalhes da obra, como imagem ilustrativa, título, gênero, data de lançamento
 * e descrição. O construtor da classe recebe como parâmetro uma obra, que é utilizada para preencher os detalhes
 * na tela.
 */
public class PainelDetalhes extends JFrame {
    private JScrollPane scrollPane;
    private JTextField campoComentario;
    private JPanel painelDoScroll;
    private Obra obra;
    private JPanel painelListaComentarios;
    private JButton botaoAvaliar;
    JComboBox<Integer> comboBoxAvaliacao;


    /**
     * Construtor da classe PainelDetalhes, que é responsável por criar uma janela onde serão exibidos os detalhes de uma obra.
     *
     * @param obra é a obra que será exibida no painel
     */
    public PainelDetalhes(Obra obra) {
        // configura a janela
        super.setSize(580, 600);
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        super.setLocationRelativeTo(null);
        // bloquear redimensionamento
        super.setLayout(new BorderLayout());
        super.setResizable(false);

        this.obra = obra;

        botaoAvaliar = new JButton("Avaliar");
        comboBoxAvaliacao = new JComboBox<>();
        if(Janela.getSistema().usuarioAtualJaAvaliou(obra)) {
            botaoAvaliar.setEnabled(false);
            comboBoxAvaliacao.setEnabled(false);
        }

        configuraDetails();

        super.add(scrollPane, BorderLayout.CENTER);
        super.setVisible(true);



        painelListaComentarios = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        configuraListaComentarios();
    }

    /**
     * Método responsável por configurar os detalhes da Obra.
     * Ele cria e configura os componentes necessários para exibir as informações da Obra,
     * como título, gênero, data de lançamento, duração e descrição.
     * Ele também configura um painel de rolagem para que todas as informações possam ser visualizadas.
     */
    private void configuraDetails() {
        painelDoScroll = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        painelDoScroll.setPreferredSize(new Dimension(400, 3000));
        painelDoScroll.setBackground(Janela.getCorFundoEscuro());

        JLabel imgIlustrativa = new JLabel(Janela.imagemDeURL(obra.getUrlImgIlustrativa()));
        imgIlustrativa.setSize(342, 192);
        imgIlustrativa.setBorder(BorderFactory.createLineBorder(Janela.getCorTextoMedio(), 2, true));
        painelDoScroll.add(imgIlustrativa);

        JLabel tituloObra = new JLabel(obra.getNome());
        tituloObra.setPreferredSize(new Dimension(420, 25));
        tituloObra.setFont(new Font("Inter", Font.BOLD, 20));
        tituloObra.setForeground(Janela.getCorTextoClaro());
        tituloObra.setHorizontalAlignment(SwingConstants.CENTER);
        painelDoScroll.add(tituloObra);

        JPanel dadosObra = new JPanel();
        dadosObra.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
        dadosObra.setBackground(painelDoScroll.getBackground());

        JButton genero = criaLabelComDados(obra.getGenero(), "Gênero");
        JButton estreia = criaLabelComDados(obra.getDataLancamento(), "Lançamento");
        JButton duracao = criaLabelComDados(obra.getDuracaoEmMinutos() + " mins.", "Duração");
        JButton temContinuacao = null;
        if(obra.getTemContinuacao() != null) {
            if (obra.getTemContinuacao()) {
                temContinuacao = criaLabelComDados("Sim", "Tem continuação?");
            } else {
                temContinuacao = criaLabelComDados("Não", "Tem continuação?");
            }
        }
        JButton quantTemps = criaLabelComDados(obra.getQuantidadeTemporadas() + " temps.", "Temporadas");
        JButton quantEps = criaLabelComDados(obra.getQuantidadeEpisodios() + " eps.", "Episódios");

        dadosObra.add(genero);
        dadosObra.add(estreia);

        if (obra.getTipoDeObra().equals("F")) {
            dadosObra.add(duracao);
            dadosObra.add(temContinuacao);
        } else if (obra.getTipoDeObra().equals("S")) {
            dadosObra.add(quantTemps);
            dadosObra.add(quantEps);
        }

        dadosObra.setSize(405, 40);
        painelDoScroll.add(dadosObra);

        //+========================================================================================================

        JTextArea textArea = new JTextArea(obra.getDescricao());
        textArea.setBackground(painelDoScroll.getBackground());
        textArea.setForeground(Janela.getCorTextoClaro());
        textArea.setEditable(false);
        textArea.setSize(new Dimension(365, 350));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        painelDoScroll.add(textArea);

        //================================================================================

        JPanel painelAvaliacao = new JPanel();

        painelAvaliacao.setBackground(Janela.getCorFundoEscuro());
        painelAvaliacao.setPreferredSize(new Dimension(450, 50));
        painelAvaliacao.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelAvaliacao.setAlignmentY(Component.CENTER_ALIGNMENT);

        for(int i = 1; i <= 5; i++) {
            comboBoxAvaliacao.addItem(i);
        }
        comboBoxAvaliacao.setPreferredSize(new Dimension(50, 30));

        JLabel labelAvaliacao = new JLabel("Avaliar obra, Nota: ");
        botaoAvaliar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janela.getSistema().avaliarObra(obra, comboBoxAvaliacao.getItemAt(comboBoxAvaliacao.getSelectedIndex()));
            }
        });

        painelAvaliacao.add(labelAvaliacao);
        painelAvaliacao.add(comboBoxAvaliacao);
        painelAvaliacao.add(botaoAvaliar);
        String dadoComponente = obra.classificacaoMedia() > 0 ? new DecimalFormat("#.00").format(obra.classificacaoMedia()): "0";
        painelAvaliacao.add(criaLabelComDados( dadoComponente, "Nota média"));
        painelDoScroll.add(painelAvaliacao);

        //================================================================================
        scrollPane = new JScrollPane(painelDoScroll);
        scrollPane.setBackground(Janela.getCorFundoClaro());
        scrollPane.setPreferredSize(super.getSize());


        campoComentario= new JTextField();
        configuraJTextComponent(campoComentario, new Dimension(400, 45), "Adicionar comentário");
        painelDoScroll.add(campoComentario);

        JButton botaoEnviarComentario = new JButton();
        botaoEnviarComentario.setIcon(new ImageIcon("img/icons/icon-enviar.png"));
        botaoEnviarComentario.setBackground(Janela.getCorFundoClaro());
        painelDoScroll.add(botaoEnviarComentario);
        botaoEnviarComentario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Janela.getSistema().comentarObra(obra, campoComentario.getText());
                painelListaComentarios.removeAll();
                configuraListaComentarios();
                painelListaComentarios.revalidate();
                painelListaComentarios.repaint();
            }
        });



    }

    private void configuraListaComentarios() {
        painelListaComentarios.setPreferredSize(painelDoScroll.getSize());
        painelListaComentarios.setBackground(painelDoScroll.getBackground());

        for(Comentario comentario : Janela.getSistema().comentariosObra(obra)) {
            painelListaComentarios.add(criaPainelDeComentario(comentario.getTextoComentario(), comentario.getNomeUsuario(), comentario.getDataHora()));
        }

        painelDoScroll.add(painelListaComentarios);
    }

    private void configuraJTextComponent(JTextComponent textField, Dimension d, String titulo) {
        textField.setPreferredSize(d);
        textField.setBackground(painelDoScroll.getBackground());
        textField.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(null, 1), titulo, TitledBorder.LEFT, TitledBorder.TOP, new Font("Inter", Font.PLAIN, 13)));
    }

    /**
     * Método responsável por criar um botão que sera na verdade um label com uma informação passada como parâmetro e um título.
     *
     * @param dadoComponente   informação a ser exibida no botão
     * @param tituloComponente título do botão
     * @return retorna o botão criado
     */
    private JButton criaLabelComDados(String dadoComponente, String tituloComponente) {
        JButton componente = new JButton(dadoComponente);
        componente.setPreferredSize(new Dimension(125, 35));
        componente.setOpaque(true);
        componente.setBackground(Janela.getCorComponente());
        componente.setForeground(Janela.getCorTextoClaro());
        componente.setFont(new Font("Inter", Font.BOLD, 15));
        componente.setAlignmentX(Component.CENTER_ALIGNMENT);
        componente.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEmptyBorder(),
                tituloComponente,
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Inter", Font.PLAIN, 11),
                Janela.getCorTextoMedio()));
        return componente;
    }

    private JPanel criaPainelDeComentario(String textoComentario, String usuarioComentario, String hora) {
        JPanel painelComentario = new JPanel(new BorderLayout());
        painelComentario.setPreferredSize(new Dimension(500, 60));
        JLabel labelUserComentario = new JLabel(usuarioComentario, Janela.imagemDeURL(
                "https://avatars.dicebear.com/api/initials/" + usuarioComentario
                        + ".png?r=50&backgroundColors[]=lightBlue&backgroundColors[]=lightGreen&backgroundColors[]=orange&backgroundColors[]=yellow&backgroundColors[]=lime&backgroundColorLevel=400&bold=true&size=25"),
                SwingConstants.CENTER);
        labelUserComentario.setPreferredSize(new Dimension(50,35));
        labelUserComentario.setFont(new Font("Inter", Font.PLAIN, 10));
        labelUserComentario.setHorizontalTextPosition(JLabel.CENTER);
        labelUserComentario.setVerticalTextPosition(JLabel.BOTTOM);
        painelComentario.add(labelUserComentario, BorderLayout.WEST);

        JTextArea comentario = new JTextArea(obra.getDescricao());
        comentario.setText(textoComentario);
        comentario.setBackground(painelDoScroll.getBackground());
        comentario.setForeground(Janela.getCorTextoClaro());
        comentario.setEditable(false);
        comentario.setSize(new Dimension(400, 50));
        comentario.setLineWrap(true);
        comentario.setWrapStyleWord(true);

        JScrollPane scrollDoComentario = new JScrollPane(comentario);
        scrollDoComentario.setPreferredSize(new Dimension(400, 50));
        scrollDoComentario.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        painelComentario.add(scrollDoComentario, BorderLayout.CENTER);

        JLabel labelHoraComentario = new JLabel(hora, SwingConstants.CENTER);
        labelHoraComentario.setPreferredSize(new Dimension(50,35));
        labelHoraComentario.setFont(new Font("Inter", Font.PLAIN, 10));
        labelHoraComentario.setHorizontalTextPosition(JLabel.CENTER);
        labelHoraComentario.setVerticalTextPosition(JLabel.CENTER);
        painelComentario.add(labelHoraComentario, BorderLayout.EAST);
        return painelComentario;
    }
}
