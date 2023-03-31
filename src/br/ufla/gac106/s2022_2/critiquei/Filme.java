package br.ufla.gac106.s2022_2.critiquei;

public class Filme extends Obra {
    private final int duracaoEmMinutos;
    private final Boolean temContinuacao;

    /**
     * Construtor da classe Filme que herda as informações da classe Obra e adiciona
     * a duração em minutos do filme.
     *
     * @param id               id do filme
     * @param nome             nome do filme
     * @param descricao        descrição do filme
     * @param pathPoster       caminho da imagem do poster do filme
     * @param backdropPath     caminho da imagem ilustrativa do filme
     * @param dataLancamento   data de lançamento do filme
     * @param genero           gênero do filme
     * @param duracaoEmMinutos duração do filme em minutos
     * @param tipoObra         tipo da obra
     */
    public Filme(int id, String nome, String descricao, String urlPoster, String urlImgIlustrativa, String dataLancamento, String genero, String tipoDeObra, int duracaoEmMinutos, Boolean temContinuacao) {
        super(id, nome, descricao, urlPoster, urlImgIlustrativa, dataLancamento, genero, tipoDeObra);
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.temContinuacao = temContinuacao;
    }

    /**
     * Método sobrescrito que retorna a duração do filme em minutos.
     *
     * @return int duração em minutos
     */
    @Override
    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    @Override
    public Boolean getTemContinuacao() {
        return temContinuacao;
    }
}
