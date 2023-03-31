package br.ufla.gac106.s2022_2.critiquei;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa um usuário do sistema.
 */
public class Usuario {
    private final String nomeDeUsuario;
    private final String email;
    private final String senha;
    private final int quantAvaliacoes;
    private String generoFavorito;
    private final TipoUsuario tipoUsuario;

    private Map<Obra, Double> listaAvaliacoes;

    /**
     * Construtor da classe, responsável por inicializar os atributos do objeto.
     *
     * @param nomeDeUsuario o nome de usuário do usuário.
     * @param email         o email do usuário.
     * @param senha         a senha do usuário.
     * @param tipoUsuario   o tipo de usuário (moderador, comum ou administrador).
     */
    public Usuario(String nomeDeUsuario, String email, String senha, TipoUsuario tipoUsuario) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        quantAvaliacoes = 0;
        listaAvaliacoes = new HashMap<>();
    }

    public String getNomeDeUsuario() {
        return nomeDeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getQuantAvaliacoes() {
        return quantAvaliacoes;
    }

    public String getGeneroFavorito() {
        return generoFavorito;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public Map<Obra, Double> getListaAvaliacoes() {
        if(listaAvaliacoes.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableMap(listaAvaliacoes);
    }

    public void adicionaAvaliacao(Obra obra, double nota) {
        listaAvaliacoes.put(obra, nota);
    }
}
















