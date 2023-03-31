package br.ufla.gac106.s2022_2.critiquei.TmdbApi;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe MoviesAndTV é responsável por guardar as informações de uma obra retornada pela API do Tmdb.
 */
public class MoviesAndTV {
    private int id;
    private String title;
    private String name;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private String release_date;
    private String first_air_date;
    private String media_type;


    public int getId() {
        return id;
    }

    public String getTitulo() {
        if (title != null) {
            return title;
        } else {
            return name;
        }
    }

    public String getResumo() {
        return overview;
    }

    /**
     * Método que retorna a URL da imagem de poster da obra.
     *
     * @param tamanho tamanho da imagem desejado (W92, W154, etc)
     * @return URL da imagem de poster
     */
    public String getURLImagemPoster(TamanhoImagem tamanho) {
        return "https://image.tmdb.org/t/p/" + tamanho.name() + this.poster_path;
    }

    /**
     * Método que retorna a data de lançamento da obra, levando em conta que pode vir com nomes diferentes da API.
     *
     * @return data de lançamento da obra
     */
    public String getDataLancamento() {
        String dataAFormatar;
        if (release_date != null) {
            dataAFormatar = release_date;
        } else {
            dataAFormatar = first_air_date;
        }
        try {
            SimpleDateFormat formatoDaApi = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatoPadrao = new SimpleDateFormat("dd/MM/yyyy");
            Date data = formatoDaApi.parse(dataAFormatar);

            // Formata a data de acordo com o formato de saída desejado
            return formatoPadrao.format(data);

        } catch (Exception e) {
            System.out.println("sem data");
            return "-";
        }


    }

    /**
     * Método que retorna a URL da imagem ilustrativa da obra.
     *
     * @param tamanho tamanho da imagem desejado (W342, W780, etc)
     * @return URL da imagem ilustrativa
     */
    public String getURLImagemIlustrativa(TamanhoImagem tamanho) {
        return "https://image.tmdb.org/t/p/" + tamanho.name() + this.backdrop_path;
    }

    /*
     * Método que retorna o tipo da obra (filme ou serie), retorno já no padrão do sistema de S ou F.
     */
    public String getTipoObra() {
        if (media_type.equals("tv")) {
            return "S";
        } else if (media_type.equals("movie")) {
            return "F";
        }
        return null;
    }
}
