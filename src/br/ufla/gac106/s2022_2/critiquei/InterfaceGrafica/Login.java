package br.ufla.gac106.s2022_2.critiquei.InterfaceGrafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Nessa classe foi escolhido posicionar cada elemento pixel a pixel.
 * Essa classe é responsável por criar a janela de login da aplicação. Ela herda da classe Janela e implementa
 * os métodos para adicionar os componentes de interface gráfica, como os inputs de email e senha, o botão de entrar
 * e o botão para os usuários que não possuem cadastro. Ela também implementa ações para os botões, como verificar
 * se o email e senha estão corretos e redirecionar o usuário para a tela de cadastro caso ele aperte o botão "não
 * possuo cadastro".
 */
public class Login extends Janela {
    //private Sistema sistema = new Sistema();
    private JButton botaoEntrar;
    private JButton botaoNaoPossuoCadastro;
    private JTextField inputEmail;
    private JPasswordField inputSenha;

    /**
     * O construtor da classe chama os métodos adicionaImgIlustrat, adicionaLogo e
     * adicionaInputsLogin, que são responsáveis por adicionar elementos gráficos
     * como imagens, logo, entre outros.
     */
    public Login() {
        super("Login");
        adicionaImgIlustrat();
        adicionaLogo();
        adicionaInputsLogin();
    }

    /**
     * Método responsável por adicionar uma imagem ilustrativa na tela de login.
     * Ele cria um JLabel com uma imagem que é setada como o recurso da aplicação
     * em "img/initPage/imgNotebook.png" e configura as suas dimensões e posição.
     * Por fim, ele adiciona o JLabel no painel da tela de login.
     */
    private void adicionaImgIlustrat() {
        JLabel note = new JLabel(new ImageIcon("img/initPage/imgNotebook.png")); // imagem
        note.setBounds(19, 174, 660, 371);
        add(note);
    }

    /**
     * Essa função adiciona o logo e o texto "critiquei" na tela de login.
     * O logo é adicionado como uma imagem e o texto é adicionado como um JLabel.
     * A posição e as dimensões dos elementos são definidas e configuradas.
     */
    private void adicionaLogo() {
        JLabel imgLogo = new JLabel(new ImageIcon("img/initPage/logoHeaderLogin.png")); // logo
        // principal
        imgLogo.setBounds(859, 127, 122, 116);
        add(imgLogo);

        JLabel text = new JLabel("critiquei");
        text.setFont(new Font("Inter", Font.BOLD, 49));
        text.setForeground(getCorAmarelo());
        text.setBounds(784, 233, 258, 96);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text);
    }


    /**
     Método que adiciona os inputs de login (e-mail e senha) e seus titulos na tela de login.
     Adiciona também os botões de entrar e de "não possuo cadastro", além do
     background da área de inputs, um retangulo de cantos arredondados.
     */
    private void adicionaInputsLogin() {

        // input de email
        JLabel labelEmail = new JLabel("e-mail");
        labelEmail.setFont(new Font("Inter", Font.PLAIN, 14));
        labelEmail.setBounds(784, 330, 75, 24);
        add(labelEmail);
        inputEmail = new JTextField();
        inputEmail.setBounds(784, 354, 258, 35);
        add(inputEmail);

        // input de senha
        JLabel labelSenha = new JLabel("senha");
        labelSenha.setFont(new Font("Inter", Font.PLAIN, 14));
        labelSenha.setBounds(784, 414, 75, 24);
        add(labelSenha);
        inputSenha = new JPasswordField();
        inputSenha.setBounds(784, 439, 258, 35);
        add(inputSenha);


        botaoEntrar = new JButton("entrar", new ImageIcon("img/initPage/estrelaDoBotao.png"));
        botaoEntrar.setBounds(857, 506, 111, 39);
        botaoEntrar.setBackground(getCorVerde());
        botaoEntrar.setForeground(getCorAmarelo());
        botaoEntrar.setFont(new Font("Inter", Font.BOLD, 15));
        add(botaoEntrar);

        botaoNaoPossuoCadastro = new JButton("não possuo cadastro");
        botaoNaoPossuoCadastro.setBounds(830, 573, 167, 20);
        botaoNaoPossuoCadastro.setFont(new Font("Inter", Font.PLAIN, 13));
        botaoNaoPossuoCadastro.setForeground(getCorTextoMedio());
        botaoNaoPossuoCadastro.setBackground(getCorFundoClaro());
        add(botaoNaoPossuoCadastro);
        // background da area de inputs
        JLabel bgLoginJLabel = new JLabel(new ImageIcon("img/initPage/bgInput.png"));
        bgLoginJLabel.setBounds(717, 70, 393, 579);
        add(bgLoginJLabel);

        botaoEntrar.addActionListener(this);
        botaoNaoPossuoCadastro.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String senha = new String(inputSenha.getPassword());
        if (e.getSource() == botaoEntrar) {
            if (super.getSistema().autenticaUsuarioExistente(inputEmail.getText(), senha)) {
                Colecoes col = new Colecoes();
                col.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos", "Erro", JOptionPane.ERROR_MESSAGE);
                inputEmail.setText("");
                inputSenha.setText("");
            }

        } else if (e.getSource() == botaoNaoPossuoCadastro) {
            Cadastro cad = new Cadastro();
            cad.setVisible(true);
            this.dispose();
        }

        super.actionPerformed(e);
    }

}
