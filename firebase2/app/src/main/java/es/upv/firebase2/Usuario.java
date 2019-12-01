package es.upv.firebase2;

public class Usuario {
    public String nombre;
    public String correo;
    public long inicioSesion;
    public Usuario () {}
    public Usuario (String nombre, String correo, long inicioSesion) {
        this.nombre = nombre;
        this.correo = correo;
        this.inicioSesion = inicioSesion;
    }
    public Usuario (String nombre, String correo) {
        this(nombre, correo, System.currentTimeMillis());
    }



}