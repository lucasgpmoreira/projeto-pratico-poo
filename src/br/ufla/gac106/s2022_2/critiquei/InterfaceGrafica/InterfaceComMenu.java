package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.TipoUsuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe InterfaceComMenu herda de Janela e é responsável por criar uma interface gráfica com menu lateral.
 * Possui botões para acessar a coleção, adicionar obras, remover obras e sair, além de um label com imagem de perfil e username do usuário atual.
 * A classe também verifica se o usuário atual é um moderador ou administrador e habilita o botão de adição e remoção de obras, respectivamente.
 */
public class InterfaceComMenu extends Janela {
    private final JPanel painelMenu;
    private final JButton botaoColecao;
    private final JButton botaoAdicionar;
    private final JButton botaoRemover;
    private final JButton botaoSair;
    private JLabel labelUsuarioAtual;


    /**
     * Construtor da classe InterfaceComMenu, configura os botões da barra lateral
     * e os adiciona ao painel lateral, que por sua vez é adicionado à janela, tendo
     * configurações de posição e tamanho.
     * @param titulo
     */
    InterfaceComMenu(String titulo) {
        super(titulo);
        painelMenu = new JPanel();
        botaoColecao = new JButton();
        botaoAdicionar = new JButton();
        botaoRemover = new JButton();
        botaoSair = new JButton();

        //configura os botoes da barra lateral e o label com imagem de perfil e username
        configuraBotoes(botaoColecao, "Coleção", "button-collection-icon");
        configuraBotoes(botaoAdicionar, "Adicionar", "button-add-icon");
        configuraBotoes(botaoRemover, "Remover", "button-remove-icon");
        configuraBotoes(botaoSair, "Sair", "button-logout-icon");
        configuraLabelUsuarioAtual();

        //faz as configurações do painel lateral
        painelMenu.setBounds(0, 32, 250, 668);
        painelMenu.setOpaque(true);
        painelMenu.setBackground(getCorFundoClaro());
        painelMenu.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        //adiciona os componentes ao menu lateral
        painelMenu.add(labelUsuarioAtual);
        painelMenu.add(Box.createVerticalStrut(60));
        painelMenu.add(botaoColecao);
        if (super.getSistema().getUsuarioAtual().getTipoUsuario() == TipoUsuario.MODERADOR || super.getSistema().getUsuarioAtual().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
            painelMenu.add(botaoAdicionar);
        }
        if (super.getSistema().getUsuarioAtual().getTipoUsuario() == TipoUsuario.ADMINISTRADOR) {
            painelMenu.add(botaoRemover);
        }


        botaoSair.setBackground(Color.decode("#471B1B"));
        painelMenu.add(botaoSair);

        super.add(painelMenu);

        botaoColecao.addActionListener(this);
        botaoAdicionar.addActionListener(this);
        botaoRemover.addActionListener(this);
        botaoSair.addActionListener(this);
    }

    /**
     * Método que configura os botões do menu lateral de acordo com o ícone e o texto que serão exibidos.
     * @param botao
     * @param text
     * @param fileIcon
     */
    private void configuraBotoes(JButton botao, String text, String fileIcon) {
        botao.setText(text);
        botao.setPreferredSize(new Dimension(175, 35));
        botao.setOpaque(true);
        botao.setBackground(getCorComponente());
        botao.setForeground(getCorTextoClaro());
        botao.setFont(new Font("Inter", Font.BOLD, 18));

        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setIconTextGap(6);

        Icon icon = new ImageIcon("img/icons/" + fileIcon + ".png");
        botao.setIcon(icon);
        botao.setHorizontalTextPosition(SwingConstants.LEADING);

    }

    /**
     * Método que configura o label com imagem de perfil e username do usuário atual.
     * O icone vem da api do dicebear, que gera um avatar a partir de um hash do username.
     */
    private void configuraLabelUsuarioAtual() {
        String nomeUsuarioAtual = super.getSistema().getUsuarioAtual().getNomeDeUsuario(); // super.getUsernameUsuarioAtual();
        labelUsuarioAtual = new JLabel(nomeUsuarioAtual, imagemDeURL(
                "https://avatars.dicebear.com/api/initials/" + nomeUsuarioAtual
                        + ".png?r=50&backgroundColors[]=lightBlue&backgroundColors[]=lightGreen&backgroundColors[]=orange&backgroundColors[]=yellow&backgroundColors[]=lime&backgroundColorLevel=400&bold=true&size=35"),
                SwingConstants.LEFT);
        labelUsuarioAtual.setFont(new Font("Inter", Font.BOLD, 16));
        labelUsuarioAtual.setForeground(getCorAmarelo());
        labelUsuarioAtual.setIconTextGap(10);

    }

    //esses métodos são para adicionar algum botao em destaque de acordo com suas cores
    protected void setBotaoEmDestaqueColecao() {
        botaoColecao.setBackground(getCorAzul());
        botaoAdicionar.setBackground(getCorComponente());
        botaoRemover.setBackground(getCorComponente());
    }

    protected void setBotaoEmDestaqueAdicionar() {
        botaoColecao.setBackground(getCorComponente());
        botaoAdicionar.setBackground(getCorVerde());
        botaoRemover.setBackground(getCorComponente());
    }

    protected void setBotaoEmDestaqueRemover() {
        botaoColecao.setBackground(getCorComponente());
        botaoAdicionar.setBackground(getCorComponente());
        botaoRemover.setBackground(getCorVermelho());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoColecao) {
            Colecoes col = new Colecoes();
            col.setVisible(true);
            this.dispose();
        } else if (e.getSource() == botaoAdicionar) {
            Adicionar add = new Adicionar();
            add.setVisible(true);
            this.dispose();
        } else if (e.getSource() == botaoRemover) {
            Remover rem = new Remover();
            rem.setVisible(true);
            this.dispose();
        } else if (e.getSource() == botaoSair) {
            Login log = new Login();
            log.setVisible(true);
            this.dispose();
        }
        super.actionPerformed(e);
    }

}
