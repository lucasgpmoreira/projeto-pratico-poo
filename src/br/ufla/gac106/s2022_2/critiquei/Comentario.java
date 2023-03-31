package br.ufla.gac106.s2022_2.critiquei;

public class Comentario {
    private String nomeUsuario;
    private int idObra;
    private String textoComentario;
    private String dataHora;

    public Comentario (String nomeUsuario, int idObra, String textoComentario, String dataHora) {
        this.nomeUsuario = nomeUsuario;
        this.idObra = idObra;
        this.textoComentario = textoComentario;
        this.dataHora = dataHora;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public int getIdObra() {
        return idObra;
    }

    public String getTextoComentario() {
        return textoComentario;
    }

    public String getDataHora() {
        return dataHora;
    }
}
