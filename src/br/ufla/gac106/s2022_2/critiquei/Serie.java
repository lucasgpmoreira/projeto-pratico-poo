package br.ufla.gac106.s2022_2.critiquei;

public class Serie extends Obra {

    private final int quantidadeTemporadas;
    private final int quantidadeEpisodios;

    /**
     * Construtor da classe que recebe os atributos de uma série e chama o construtor da classe mãe Obra
     *
     * @param id                   id da série
     * @param nome                 nome da série
     * @param descricao            descrição da série
     * @param pathPoster           url da imagem de poster da série
     * @param backDropPath         url da imagem ilustrativa da série
     * @param dataLancamento       data de lançamento da série
     * @param genero               gênero da série
     * @param quantidadeTemporadas quantidade de temporadas da série
     * @param tipoObra             tipo de obra (série ou filme)
     */
    public Serie(int id, String nome, String descricao, String urlPoster, String urlImgIlustrativa, String dataLancamento, String genero, String tipoDeObra, int quantidadeTemporadas, int quantidadeEpisodios) {
        super(id, nome, descricao, urlPoster, urlImgIlustrativa, dataLancamento, genero, tipoDeObra);
        this.quantidadeTemporadas = quantidadeTemporadas;
        this.quantidadeEpisodios = quantidadeEpisodios;
    }


    @Override
    public int getQuantidadeTemporadas() {
        return quantidadeTemporadas;
    }

    @Override
    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

}
