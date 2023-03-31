package br.ufla.gac106.s2022_2.critiquei;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import br.ufla.gac106.s2022_2.critiquei.BancoDeDados.ConexaoMySQL;
import br.ufla.gac106.s2022_2.critiquei.TmdbApi.MoviesAndTV;
import br.ufla.gac106.s2022_2.critiquei.TmdbApi.TamanhoImagem;
import br.ufla.gac106.s2022_2.critiquei.TmdbApi.TmdbAPI;

/**
 * Essa classe representa o sistema principal do projeto, com as funções de adicionar usuários,
 * autenticar usuários existentes, buscar obras no banco de dados e na API,
 * adicionar obras ao banco de dados, e avaliar obras.
 */
public class Sistema {
    private Usuario usuarioAtual;
    private TmdbAPI api = new TmdbAPI("-");
    private ConexaoMySQL conexaoMySQL;
    private List<Usuario> usuariosCadastrados;
    private List<Obra> obrasCadastradas;
    private List<Comentario> comentarios;

    public Sistema() {
        usuariosCadastrados = new ArrayList<>();
        obrasCadastradas = new ArrayList<>();
        conexaoMySQL = new ConexaoMySQL();
        comentarios = new ArrayList<>();
        for (Usuario usuario : conexaoMySQL.usuariosCadastrados()) {
            usuariosCadastrados.add(usuario);
        }
        for(Obra obra : conexaoMySQL.obrasCadastradas()) {
            obrasCadastradas.add(obra);
        }
        for(Comentario comentario : conexaoMySQL.todosComentarios()) {
            comentarios.add(comentario);
        }
    }

    /**
     * Essa função recebe um objeto da classe usuario e adiciona a lista o proprio
     * usuario e o hash da senha, para não colocar no banco a senha propriamente. A função
     * vem da biblioteca JBCrypt.
     */
    public boolean adicionaUsuario(Usuario usuario) {
        if (usuario != null) {
            String hashDaSenha = BCrypt.hashpw(usuario.getSenha(), BCrypt.gensalt());
            Usuario usuarioNovo = new Usuario(usuario.getNomeDeUsuario(), usuario.getEmail(), hashDaSenha, usuario.getTipoUsuario());
            usuariosCadastrados.add(usuarioNovo);
            conexaoMySQL.adicionarUsuario(usuarioNovo);
            return true;
        }
        return false;
    }

    /**
     * Essa função verifica se já existe um usuário cadastrado com o email recebido.
     *
     * @param verificador parametro a ser verificado
     * @returns boolean true se já existe um usuário com esse email, false caso contrário
     */
    public boolean verificaSeUsuarioJaExiste(String verificador) {
        for (Usuario userAux : usuariosCadastrados) {
            if (userAux.getEmail().equals(verificador) || userAux.getNomeDeUsuario().equals(verificador)) {
                return true;
            }
        }
        return false;
    }

    private Usuario buscaUsuarioPorEmail(String email) {
        for(Usuario usuario : usuariosCadastrados) {
            if(usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Essa função verifica se existe um usuário cadastrado com o email e senha recebidos e, caso exista, define esse usuário como usuário atual.
     *
     * @param email email a ser verificado
     * @param senha senha a ser verificada
     * @returns boolean true se autenticação bem sucedida, false caso contrário
     */
    public boolean autenticaUsuarioExistente(String email, String senha) {
        if (verificaSeUsuarioJaExiste(email)) {
            Usuario usuarioBuscado = buscaUsuarioPorEmail(email);
            if(usuarioBuscado != null) {
                String senhaEncriptada = usuarioBuscado.getSenha();
                if (BCrypt.checkpw(senha, senhaEncriptada)) {
                    usuarioAtual = usuarioBuscado;
                    return true;
                }
            }
        }
        return false;
    }

    // ===== QUALQUER USUARIO

    /**
     * Esta função retorna uma ArrayList de obras que estão cadastradas no banco de
     * dados.
     *
     * @returns Arraylist<Obras> todas obras
     */
        public List<Obra> ObrasCadastradas() {
            return Collections.unmodifiableList(obrasCadastradas);
        }

    // ===USUARIO MODERADOR

    /**
     * Essa função busca obras com o nome recebido na API e as retorna em formato de ArrayList de Obra.
     *
     * @param nomeDaObra nome da obra a ser buscada
     * @returns ArrayList<Obra> obras encontradas
     */
    public ArrayList<Obra> buscaObra(String nomeDaObra) {
        ArrayList<Obra> obrasRetorno = new ArrayList<>();
        List<MoviesAndTV> listaObrasPesquisadas = api.buscaPorTermo(nomeDaObra);
        for (MoviesAndTV obraDaApi : listaObrasPesquisadas) {
            if(obraDaApi.getTipoObra() != null) {
                obrasRetorno.add(ObraFactory.criarObra(
                    obraDaApi.getId(),
                    obraDaApi.getTitulo(),
                    obraDaApi.getResumo(),
                    obraDaApi.getURLImagemPoster(TamanhoImagem.w92),
                    obraDaApi.getURLImagemIlustrativa(TamanhoImagem.w342),
                    obraDaApi.getDataLancamento(),
                    null,
                    obraDaApi.getTipoObra(),
                    0,
                    0,
                    0,
                    null
                ));
            }
        }
        return obrasRetorno;
    }

    /**
     * Esta função recebe um objeto da classe Obra e adiciona ao banco de dados.
     *
     * @param obra - Obra a ser adicionada
     * @returns boolean - true se a obra foi adicionada com sucesso, false caso contrário
     */
    public boolean adicionaObra(Obra obra) {
        if (obra != null) {
            obrasCadastradas.add(obra);
            conexaoMySQL.adicionaObra(obra);
            return true;
        }
        return false;
    }

    // USUARIO ADMIN

    /**
     * Esta função remove uma obra do banco de dados através do seu id. Somente usuários administradores podem realizar essa ação.
     *
     * @param id - id da obra a ser removida
     * @returns boolean - true se a obra foi removida com sucesso, false caso contrário
     */
    public boolean removeObra(int id) {
        for (int i = 0; i < obrasCadastradas.size(); i++) {
            if (obrasCadastradas.get(i).getId() == id) {
                conexaoMySQL.excluiObra(id); //precisa remover do banco logo  que o usuario remove
                obrasCadastradas.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o usuário atualmente logado no sistema
     *
     * @returns Usuario - objeto Usuario do usuário logado
     */
    public Usuario getUsuarioAtual() {
        return usuarioAtual;
    }

    public void comentarObra(Obra obra, String comentario) {
        if (obra != null && comentario != null) {
            Comentario novoComentario = new Comentario(usuarioAtual.getNomeDeUsuario(), obra.getId(), comentario, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            comentarios.add(novoComentario);
            conexaoMySQL.adicionaComentario(novoComentario);
        }
    }

    public List<Comentario> comentariosObra(Obra obra) {
        List<Comentario> comentariosObra = new ArrayList<>();
        for (Comentario comentario : comentarios) {
            if (comentario.getIdObra() == obra.getId()) {
                comentariosObra.add(comentario);
            }
        }
        return comentariosObra;
    }

    public boolean usuarioAtualJaAvaliou(Obra obra) {
        Map<Obra, Double> avaliacoesUsuario = usuarioAtual.getListaAvaliacoes();
        if(avaliacoesUsuario == null) {
            return false;
        }

        for (Obra obraAvaliada : avaliacoesUsuario.keySet()) {
            if (obraAvaliada.getId() == obra.getId()) {
                return true;
            }
        }
        return false;
    }

    public void avaliarObra(Obra obra, double nota) {
        if (obra != null) {
            obra.avaliar(nota);
            usuarioAtual.adicionaAvaliacao(obra, nota);
            conexaoMySQL.adicionaAvaliacao(obra.getId(), usuarioAtual.getNomeDeUsuario(), nota);
        }
    }

    public List<Obra> filtrarObras(String nome) {
        List<Obra> obrasFiltradas = new ArrayList<>();
        for (Obra obraFiltrada : obrasCadastradas) {
            if (obraFiltrada.getNome().equals(nome)) {
                obrasFiltradas.add(obraFiltrada);
            }
        }

        return Collections.unmodifiableList(obrasFiltradas);
    }

}
