package br.ufla.gac106.s2022_2.critiquei;

public class ObraFactory {
    public static Obra criarObra(int id, String nome, String descricao, String urlPoster, String urlImgIlustrativa, String dataLancamento, String genero, String tipoDeObra, int quantidadeTemporadas, int quantidadeEpisodios, int duracaoEmMinutos, Boolean temContinuacao) {
        if(tipoDeObra.equals("S")) {
            return new Serie(id, nome, descricao, urlPoster, urlImgIlustrativa, dataLancamento, genero, tipoDeObra, quantidadeTemporadas, quantidadeEpisodios);
        } else if (tipoDeObra.equals("F")) {
            return new Filme(id, nome, descricao, urlPoster, urlImgIlustrativa, dataLancamento, genero, tipoDeObra, duracaoEmMinutos, temContinuacao);
        }
        return null;
    }
}
