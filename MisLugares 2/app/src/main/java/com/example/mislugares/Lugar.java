package com.example.mislugares;

public class Lugar {

    private String nombre;
    private String direccion;
    private GeoPunto posicion;
    private String foto;
    private int telefono;
    private String url;
    private String comentario;
    private long fecha;
    private float valoracion;
    private TipoLugar tipo;

    public Lugar(String nombre, String direccion, double longitud,
                 double latitud, TipoLugar tipo , int telefono, String url, String comentario,
                 int valoracion) {
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(longitud, latitud);
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.url = url;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.tipo = tipo;
    }
    public Lugar() {
        fecha = System.currentTimeMillis();
        posicion = new GeoPunto(0.0, 0.0);
        tipo = TipoLugar.OTROS;
    }

    public String getNombre(){
        return nombre;
    }

    public TipoLugar getTipo() {
        return tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getUrl() {
        return url;
    }

    public String getComentario() {
        return comentario;
    }

    public long getFecha() {
        return fecha;
    }

    public float getValoracion() {
        return valoracion;
    }

    public void setValoracion(float valor) {
        valoracion = valor;
    }
}