package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.Obra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Classe que cria uma lista de obras com imagens, essa classe cria um painel principal para posteriormente
 * retorná-lo para a classe que a chamou.
 */
public class JListComImagens implements ActionListener {
    private final JPanel painelPrincipal;
    private final JButton botaoAdd;
    private JPanel painelDoBotao;
    private final List<Obra> obrasCadastradas;
    private List<Obra> obrasFiltradas;
    private final Boolean ehNecessarioDetalhes;
    private JList<Obra> jlistObras;
    private JScrollPane scrollFeed;
    private Obra obraSelecionada;

    /**
     * Método construtor da classe JListComImagens. Ele recebe como parâmetros uma lista de obras, uma dimensão para o painel principal,
     * um texto para o botão "add", e um booleano para determinar se é necessário exibir detalhes da obra selecionada.
     * Esse construtor cria um painel principal, uma JList, um JScrollPane, um botão "add" e seta as configurações para esses elementos.
     */
    public JListComImagens(List<Obra> listaDeObras, Dimension tamPainel, String textoBotao, Boolean necessarioDetalhes) {
        List<Obra> obrasCadastradasAux;
        painelPrincipal = new JPanel();
        obrasCadastradasAux = listaDeObras;
        obrasCadastradas = obrasCadastradasAux;
        this.ehNecessarioDetalhes = necessarioDetalhes;

        configuraJlist(obrasCadastradas);
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setPreferredSize(tamPainel);
        painelPrincipal.setBackground(Janela.getCorFundoClaro());
        painelDoBotao = new JPanel();
        painelDoBotao.setBackground(Janela.getCorFundoClaro());
        painelDoBotao.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        botaoAdd = new JButton(textoBotao);
        botaoAdd.addActionListener(this);
        botaoAdd.setSize(40, 20);
        botaoAdd.setPreferredSize(new Dimension(180, 30));

        painelDoBotao.add(botaoAdd);
        painelPrincipal.add(painelDoBotao, BorderLayout.SOUTH);
        painelPrincipal.add(scrollFeed, BorderLayout.CENTER);
    }


    /**
     * Método que configura a JList de obras a serem exibidas na tela.
     * Ele cria uma nova JList com a lista de obras passadas como parâmetro, seta o renderer da JList com o renderer
     * personalizado "MovieListRenderer", seta a cor de seleção de fundo e de fonte da JList e cria um scrollPane
     * para a JList.
     */
    private void configuraJlist(List<Obra> listaDeObras) {
        jlistObras = new JList<>(listaDeObras.toArray(new Obra[0]));
        jlistObras.setCellRenderer(new MovieListRenderer());
        jlistObras.setSelectionBackground(Janela.getCorFundoClaro());
        jlistObras.setSelectionForeground(Janela.getCorTextoClaro());

        scrollFeed = new JScrollPane(jlistObras);
        scrollFeed.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollFeed.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        jlistObras.getSelectedValuesList();
    }

    public JPanel getPainelPrincipal() {
        return painelPrincipal;
    }

    public Obra getObraSelecionada() {
        return obraSelecionada;
    }

    /**
     * Classe que cria uma lista de obras com imagens, essa classe cria um painel principal para posteriormente
     * retorná-lo para a classe que a chamou. Também é possível adicionar uma ação ao clicar no botão, como
     * mostrar detalhes da obra selecionada.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoAdd) {
            // message dialog
            Obra obraDaLista = (Obra) jlistObras.getSelectedValue();
            if (obraDaLista != null) {
                obraSelecionada = obraDaLista;
                if (ehNecessarioDetalhes) {
                    new PainelDetalhes(obraDaLista);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Você não selecionou nenhum série ou filme do feed", "Erro",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * estende a classe DefaultListCellRenderer e é responsável por renderizar os elementos da lista,
     * ou seja, é responsável por definir como serão exibidos os elementos da JList. Nela é definido
     * o texto a ser exibido, a imagem, a fonte, a posição do texto e o espaço entre texto e imagem.
     */
    private class MovieListRenderer extends DefaultListCellRenderer {
        Font font = new Font("inter", Font.BOLD, 18);

        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
                                                      boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            Obra obras = (Obra) value;
            label.setText(obras.getNome());
            label.setIcon(Janela.imagemDeURL(obras.getPathPoster()));
            label.setHorizontalTextPosition(JLabel.RIGHT);
            label.setFont(font);
            label.setIconTextGap(15);
            label.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            return label;
        }
    }

    public void atualizaListaDeObras(List<Obra> listaDeObras) {
        this.obrasFiltradas = listaDeObras;
        painelPrincipal.removeAll();
        painelDoBotao.add(botaoAdd);
        configuraJlist(obrasFiltradas);
        painelPrincipal.add(painelDoBotao, BorderLayout.SOUTH);
        painelPrincipal.add(scrollFeed, BorderLayout.CENTER);
        painelPrincipal.revalidate();
        painelPrincipal.repaint();
    }

}
