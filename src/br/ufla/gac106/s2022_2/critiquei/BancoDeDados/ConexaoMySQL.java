package br.ufla.gac106.s2022_2.critiquei.BancoDeDados;

import br.ufla.gac106.s2022_2.critiquei.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Classe que faz a conexão com o banco de dados.
 */
public class ConexaoMySQL {
    // TODO trocar esses dados para o do bfreesqldatabase

    //dados de conexão com o banco de dados
    private final static String HOSTNAME_E_PORTA = "-";
    private final static String NOME_DO_BANCO = "-";
    private final static String USUARIO = "-";
    private final static String SENHA = "-";
    
    /**
     * Função que faz a conexão com o banco de dados.
     * @return Connection - Objeto da classe Connection, que representa a conexão com o banco de dados.
     */
    private Connection criaConexaoMySQL() {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://" + HOSTNAME_E_PORTA + "/" + NOME_DO_BANCO + "",
                    USUARIO, SENHA);
            System.out.println("sucesso");
        } catch (SQLException ex) {
            System.out.println("sem conexão");
        } catch (ClassNotFoundException ex) {
            System.out.println("faltam dependencias");
        }

        return conexao;
    }

    /**
     * Fecha a conexão com o banco de dados.
     * @param conexao
     */
    private void fechaConexaoMySQL(Connection conexao) {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("Desconectou do banco de dados.");
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu desconectar do BD.");
        }
    }

    public ArrayList<Obra> obrasCadastradas() {
        Connection conn = criaConexaoMySQL();
        ArrayList<Obra> obras = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM Obra;";

            Statement codigo = conn.createStatement();
            ResultSet resultado = codigo.executeQuery(consulta);

            while (resultado.next()) {
                    obras.add(ObraFactory.criarObra(
                            resultado.getInt("idTable"),
                            resultado.getString("titulo"),
                            resultado.getString("descricao"),
                            resultado.getString("pathPoster"),
                            resultado.getString("pathImgIlustrativa"),
                            resultado.getString("dataLancamento"),
                            resultado.getString("genero"),
                            resultado.getString("tipoObra"),
                            resultado.getInt("quantTemps"),
                            resultado.getInt("quantEps"),
                            resultado.getInt("duracaoEmMinutos"),
                            resultado.getBoolean("temContinuacao")));
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu consultar os dados da obra.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
        return obras;
    }

    public void adicionaObra(Obra obra) {
        Connection conn = criaConexaoMySQL();
        try {
            String codigo = "";
            if (obra.getTipoDeObra().equals("S")) {
                codigo = "INSERT INTO Obra (`tipoObra`, `quantTemps`, `genero`, `dataLancamento`, `pathPoster`, `pathImgIlustrativa`, `titulo`, `descricao`, `quantEps`, `temContinuacao`) VALUES (";
                codigo = codigo + "'" + obra.getTipoDeObra() + "',"
                        + " '" + obra.getQuantidadeTemporadas() + "',"
                        + " '" + obra.getGenero() + "',"
                        + " '" + obra.getDataLancamento() + "',"
                        + " '" + obra.getPathPoster() + "',"
                        + " '" + obra.getUrlImgIlustrativa() + "',"
                        + " '" + obra.getNome() + "',"
                        + " '" + obra.getDescricao() + "',"
                        + " '" + obra.getQuantidadeEpisodios() + "',"
                        + "NULL);";
            } else {
                codigo = "INSERT INTO Obra (`tipoObra`, `duracaoEmMinutos`, `genero`, `dataLancamento`, `pathPoster`, `pathImgIlustrativa`, `titulo`, `descricao`, `quantEps`, `temContinuacao`) VALUES (";
                codigo = codigo + "'" + obra.getTipoDeObra() + "',"
                        + " '" + obra.getDuracaoEmMinutos() + "',"
                        + " '" + obra.getGenero() + "',"
                        + " '" + obra.getDataLancamento() + "',"
                        + " '" + obra.getPathPoster() + "',"
                        + " '" + obra.getUrlImgIlustrativa() + "',"
                        + " '" + obra.getNome() + "',"
                        + " '" + obra.getDescricao() + "',"
                        + " NULL,";
                        if(obra.getTemContinuacao()) {
                            codigo += "'1');";
                        } else {
                            codigo += "'0');";
                        }
            }



            Statement statement = conn.createStatement();
            statement.execute(codigo);
        } catch (SQLException ex) {
            System.out.println("não conseguiu add.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        Connection conn = criaConexaoMySQL();
        try {
            String codigo = "INSERT INTO Usuario (`email`, `nomeUsuario`, `senha`, `tipoUsuario`) VALUES (";

            codigo = codigo + "'" + usuario.getEmail() + "',"
                    + " '" + usuario.getNomeDeUsuario() + "',"
                    + " '" + usuario.getSenha() + "',";
            if(usuario.getTipoUsuario().name().equals("COMUM")) {
                codigo += " 'C');";
            } else if(usuario.getTipoUsuario().name().equals("MODERADOR")) {
                codigo += " 'M');";
            } else {
                codigo += " 'A');";
            }


            Statement statement = conn.createStatement();
            statement.execute(codigo);
        } catch (SQLException ex) {
            System.out.println("não conseguiu add.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
    }

    public ArrayList<Usuario> usuariosCadastrados() {
        Connection conn = criaConexaoMySQL();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM Usuario;";

            Statement codigo = conn.createStatement();
            ResultSet resultado = codigo.executeQuery(consulta);

            while (resultado.next()) {
                String tipoUsuarioChar = resultado.getString("tipoUsuario");
                TipoUsuario tipoUsuario = null;
                if (tipoUsuarioChar.equals("C")) {
                    tipoUsuario = TipoUsuario.COMUM;
                } else if (tipoUsuarioChar.equals("M")) {
                    tipoUsuario = TipoUsuario.MODERADOR;
                } else if (tipoUsuarioChar.equals("A")) {
                    tipoUsuario = TipoUsuario.ADMINISTRADOR;
                }
                usuarios.add(new Usuario(
                        resultado.getString("nomeUsuario"),
                        resultado.getString("email"),
                        resultado.getString("senha"),
                        tipoUsuario));

            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu consultar os dados da obra.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
        return usuarios;
    }


    public void excluiObra(int id) {
        Connection conn = criaConexaoMySQL();
        String codigo = "DELETE FROM `Obra` WHERE (`idTable` = '" + id + "');";
        try {
            Statement statement = conn.createStatement();
            statement.execute(codigo);
        } catch (SQLException ex) {
            System.out.println("não conseguiu add.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
    }

    public void adicionaComentario(Comentario comentario) {
        Connection conn = criaConexaoMySQL();
        try {
            String codigo = "INSERT INTO comentario (`textoComentario`, `horaComentario`, `idObra`, `nomeUsuario`) VALUES (";

            codigo = codigo + "'" + comentario.getTextoComentario() + "',"
                    + " '" + comentario.getDataHora() + "',"
                    + " '" + comentario.getIdObra() + "',"
                    + " '" + comentario.getNomeUsuario() + "');";

            Statement statement = conn.createStatement();
            statement.execute(codigo);
        } catch (SQLException ex) {
            System.out.println("não conseguiu add comentario.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
    }

    public ArrayList<Comentario> todosComentarios() {
        Connection conn = criaConexaoMySQL();
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            String consulta = "SELECT * FROM comentario;";

            Statement codigo = conn.createStatement();
            ResultSet resultado = codigo.executeQuery(consulta);

            while (resultado.next()) {
                comentarios.add(new Comentario(
                        resultado.getString("nomeUsuario"),
                        resultado.getInt("idObra"),
                        resultado.getString("textoComentario"),
                        resultado.getString("horaComentario")
                        ));
            }
        } catch (SQLException ex) {
            System.out.println("Não conseguiu consultar os dados da obra.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }
        return comentarios;
    }

    public void adicionaAvaliacao(int idObra, String nomeUsuario, double nota) {
        Connection conn = criaConexaoMySQL();
        try {
            String codigo = "";
            codigo = "INSERT INTO Avaliacao (`idObra`, `nomeUsuario`, `nota`) VALUES (";
            codigo = codigo + "'" + idObra + "',"
                    + " '" + nomeUsuario + "',"
                    + " '" + nota + "');";

            Statement statement = conn.createStatement();
            statement.execute(codigo);
        }

        catch (SQLException ex) {
            System.out.println("não conseguiu add.");
        } finally {
            fechaConexaoMySQL(conn); // sempre deve fechar a conexão
        }

    }
}
