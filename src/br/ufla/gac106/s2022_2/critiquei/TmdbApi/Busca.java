package br.ufla.gac106.s2022_2.critiquei.TmdbApi;

import java.util.Collections;
import java.util.List;

/*
 * Classe que consiste uma modelagem que equivale ao json retornado pelo
 * TMDB: o total de resultados, uma lista de resultados que dentro dela está
 * uma outra classe interna que representa parte dos dados da API que fazem
 * sentido para o nosso programa.
 */
public class Busca {
    private List<MoviesAndTV> results;
    //private int total_results;

    /**
     * Função que retorna uma lista com os resultados obtidos na busca.
     *
     * @return List<MoviesAndTV> - Lista com os resultados da busca.
     */
    public List<MoviesAndTV> getResults() {
        return Collections.unmodifiableList(results);
    }

}
