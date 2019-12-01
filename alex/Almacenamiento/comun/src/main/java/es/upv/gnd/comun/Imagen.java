package es.upv.gnd.comun;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class Imagen {
    String titulo;
    String url;
    long tiempo;

    public Imagen() {
    }

    public Imagen(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
        this.tiempo = new Date().getTime();
    }

    public static void registrarImagen(String titulo, String url) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Imagen imagen = new Imagen(titulo, url);
        db.collection("imagenes").document().set(imagen);
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
