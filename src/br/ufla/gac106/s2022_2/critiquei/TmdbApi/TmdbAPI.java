package br.ufla.gac106.s2022_2.critiquei.TmdbApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Classe que representa a API do TMDb, com ela é possível buscar por filmes, séries e pessoas.
 */
public class TmdbAPI {
    private final String chaveAPI;
    private String URLParaBusca;

    /**
     * Construtor da classe TmdbAPI, ele recebe a chave de autenticação da API.
     *
     * @param chaveAPI chave de autenticação da API.
     */
    public TmdbAPI(String chaveAPI) {
        this.chaveAPI = chaveAPI;
        URLParaBusca = "https://api.themoviedb.org/3/search/multi?api_key=" + this.chaveAPI + "&language=pt-BR&query="; //somar aqui o termo de busca
    }


    /**
     * Método que busca por um termo específico na API.
     *
     * @param termoBusca termo que será buscado na API.
     * @return retorna uma lista de objetos MoviesAndTV, que contém as informações dos filmes, séries e pessoas encontrados.
     */
    public List<MoviesAndTV> buscaPorTermo(String termoBusca) {
        String urlParaChamada = URLParaBusca + termoBusca.replace(" ", "%20");
        Busca busca = null;
        try {
            URL url = new URL(urlParaChamada);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            if (conexao.getResponseCode() != 200)
                throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
            BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
            String jsonEmString = converteJsonEmString(resposta);


            Gson gson = new Gson();
            busca = gson.fromJson(jsonEmString, Busca.class);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        return busca.getResults();
    }

    /**
     * Método auxiliar para converter a resposta da API (em json) para uma string.
     *
     * @param buffereReader objeto que armazena a resposta da API.
     * @return retorna a resposta da API em formato de string.
     */
    private String converteJsonEmString(BufferedReader buffereReader) throws IOException {
        String resposta, jsonEmString = "";
        while ((resposta = buffereReader.readLine()) != null) {
            jsonEmString += resposta;
        }
        return jsonEmString;
    }
}




