package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import br.ufla.gac106.s2022_2.critiquei.TipoUsuario;
import br.ufla.gac106.s2022_2.critiquei.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Classe de cadastro de um usuario.
 */
public class Cadastro extends Janela {
    private JButton botaoCadastrar;
    private JTextField inputUsername;
    private JTextField inputEmail;
    private JPasswordField inputSenha;

    /**
     * Construtor da classe, responsável por chamar os inicializadores, a img, o logo e os inputs.
     */
    public Cadastro() {
        super("Registro");
        adicionaImgIlustrat();
        adicionaLogo();
        adicionaInputsLogin();
    }

    /**
     * Método responsável por adicionar uma imagem ilustrativa na tela de cadastro.
     * Ele cria um JLabel com uma imagem que é setada como o recurso da aplicação
     * em "img/initPage/imgNotebook.png" e configura as suas dimensões e posição.
     * Por fim, ele adiciona o JLabel no painel da tela de cadastro.
     */
    private void adicionaImgIlustrat() {
        JLabel note = new JLabel(new ImageIcon("img/initPage/imgNotebook.png")); // imagem ilustrativa
        note.setBounds(19, 174, 660, 371);
        add(note);
    }

    /**
     * Essa função adiciona o logo e o texto "critiquei" na tela de cadastro.
     * O logo é adicionado como uma imagem e o texto é adicionado como um JLabel.
     * A posição e as dimensões dos elementos são definidas e configuradas.
     */
    private void adicionaLogo() {
        JLabel imgLogo = new JLabel(new ImageIcon("img/initPage/logoHeaderLogin.png")); // logo
                                                                                                                // principal
        imgLogo.setBounds(859, 116, 122, 116);
        add(imgLogo);

        JLabel text = new JLabel("critiquei");
        text.setFont(new Font("Inter", Font.BOLD, 49));
        text.setForeground(getCorAmarelo());
        text.setBounds(784, 223, 258, 96);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text);
    }

    /**
     Método que adiciona os inputs de cadastro (e-mail e senha) e seus titulos na tela de cadastro.
     Adiciona também os botões de entrar e de "não possuo cadastro", além do
     background da área de inputs, um retangulo de cantos arredondados.
     */
    private void adicionaInputsLogin() {
        // input de nome de usuario
        JLabel labelUsername = new JLabel("nome de usuário");
        labelUsername.setFont(new Font("Inter", Font.PLAIN, 14));
        labelUsername.setBounds(784, 334, 115, 24);
        add(labelUsername);
        inputUsername = new JTextField();
        inputUsername.setBounds(784, 358, 258, 35);
        add(inputUsername);
        // input de email
        JLabel labelEmail = new JLabel("e-mail");
        labelEmail.setFont(new Font("Inter", Font.PLAIN, 14));
        labelEmail.setBounds(784, 409, 75, 24);
        add(labelEmail);
        inputEmail = new JTextField();
        inputEmail.setBounds(783, 433, 258, 35);
        add(inputEmail);
        // input de senha
        JLabel labelSenha = new JLabel("senha");
        labelSenha.setFont(new Font("Inter", Font.PLAIN, 14));
        labelSenha.setBounds(784, 484, 75, 24);
        add(labelSenha);
        inputSenha = new JPasswordField();
        inputSenha.setBounds(783, 508, 258, 35);
        add(inputSenha);
        // botao entrar
        botaoCadastrar = new JButton("cadastrar",
                new ImageIcon("img/initPage/estrelaDoBotao.png"));
        botaoCadastrar.setBounds(850, 583, 125, 39);
        botaoCadastrar.setBackground(getCorVerde());
        botaoCadastrar.setForeground(getCorAmarelo());
        botaoCadastrar.setFont(new Font("Inter", Font.BOLD, 14));
        add(botaoCadastrar);

        // background da area de inputs
        JLabel bgcadastroJLabel = new JLabel(new ImageIcon("img/initPage/bgInput.png"));
        bgcadastroJLabel.setBounds(717, 79, 393, 579);
        add(bgcadastroJLabel);

        botaoCadastrar.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrar) {
            if (inputUsername.getText().equals("") || inputEmail.getText().equals("")
                    || inputSenha.getPassword().toString().equals("")) {
                JOptionPane.showMessageDialog(null, "Você precisa completar todos os campos", "Erro",
                        JOptionPane.WARNING_MESSAGE);
                return;
            } else if(super.getSistema().verificaSeUsuarioJaExiste(inputEmail.getText()) || super.getSistema().verificaSeUsuarioJaExiste(inputUsername.getText())) {
                JOptionPane.showMessageDialog(null, "Já existe um cadastro com esse email ou nome de usuário", "Erro",
                JOptionPane.WARNING_MESSAGE);
                return;
            } else {
                String senha = new String(inputSenha.getPassword());
                Boolean conseguiuCadastrar = super.getSistema().adicionaUsuario(new Usuario(
                        inputUsername.getText(),
                        inputEmail.getText(),
                        senha,
                        TipoUsuario.COMUM));
                if (conseguiuCadastrar) {
                    Login log = new Login();
                    log.setVisible(true);
                    this.dispose();
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso! Faça login", "OK",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "Não foi possível concluir o cadastro", "Erro",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

        }
        super.actionPerformed(e);
    }
}