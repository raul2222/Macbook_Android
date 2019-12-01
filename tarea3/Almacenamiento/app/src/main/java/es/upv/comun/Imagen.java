package es.upv.comun;

import java.util.Date;

public class Imagen {
    public String titulo;
    public String url;
    public long tiempo;
    public Imagen() {}
    public Imagen(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
        this.tiempo = new Date().getTime();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTiempo() {
        return tiempo;
    }

    public void setTiempo(long tiempo) {
        this.tiempo = tiempo;
    }
}