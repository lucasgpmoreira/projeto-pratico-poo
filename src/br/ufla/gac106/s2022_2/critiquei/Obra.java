package br.ufla.gac106.s2022_2.critiquei;

import br.ufla.gac106.s2022_2.base.Avaliacao;

/**
 * Classe Obra que representa uma obra (filme ou seriado) que pode ser cadastrada no sistema.
 * Possui informações como nome, id, descrição, url do poster, url da imagem ilustrativa,
 * data de lançamento, gênero e tipo de obra (filme ou seriado).
 */
public abstract class Obra implements Avaliacao {
    private final String nome;
    private final int id;
    private final String descricao;
    private final String urlPoster;
    private final String urlImgIlustrativa;
    private final String dataLancamento;
    private final String genero;
    private double somaAvaliacoes;
    private double quantidadeAvaliacoes;
    private final String tipoDeObra;

    public enum tipoDeObra {SERIE, FILME}

    /**
     * Construtor da classe Obra, que inicializa as informações da obra.
     *
     * @param id                id da obra
     * @param nome              nome da obra
     * @param descricao         descrição da obra
     * @param urlPoster         url do poster da obra
     * @param urlImgIlustrativa url da imagem ilustrativa da obra
     * @param dataLancamento    data de lançamento da obra
     * @param genero            gênero da obra
     * @param tipoDeObra        tipo de obra (F ou S)
     */
    public Obra(int id, String nome, String descricao, String urlPoster, String urlImgIlustrativa, String dataLancamento, String genero, String tipoDeObra) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.urlPoster = urlPoster;
        this.urlImgIlustrativa = urlImgIlustrativa;
        this.dataLancamento = dataLancamento;
        this.genero = genero;
        this.tipoDeObra = tipoDeObra;
        somaAvaliacoes = 0;
        quantidadeAvaliacoes = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPathPoster() {
        return urlPoster;
    }

    public String getDataLancamento() {
        return dataLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public String getUrlImgIlustrativa() {
        return urlImgIlustrativa;
    }

    public String getTipoDeObra() {
        return tipoDeObra;
    }

    public int getQuantidadeTemporadas() {
        return -1;
    }

    public int getDuracaoEmMinutos() {
        return -1;
    }

    public Boolean getTemContinuacao() {
        return null;
    }

    public int getQuantidadeEpisodios() {
        return -1;
    }

    @Override
    public String nomeItemAvaliado() {
        return nome;
    }

    @Override
    public double classificacaoMedia() {
        if(quantidadeAvaliacoes == 0) {
            return 0;
        } else {
            return somaAvaliacoes / quantidadeAvaliacoes;
        }
    }

    public void avaliar(double nota) {
        somaAvaliacoes += nota;
        quantidadeAvaliacoes += 1;
    }




}