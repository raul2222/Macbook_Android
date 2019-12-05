package es.upv.epsg.igmagi.mislugares.modelo;

import java.util.Objects;

public class GeoPunto {

    private double longitud, latitud;

    static public GeoPunto SIN_POSICION = new GeoPunto(0.0,0.0);

    public GeoPunto(double longitud, double latitud) {
        this.longitud= longitud;
        this.latitud= latitud;
    }
    public GeoPunto(int longitud, int latitud) {
        this.longitud = (int) (longitud * 1E6);
        this.latitud = (int) (latitud * 1E6);
    }
    public String toString() {
        return new String("longitud:" + longitud + ", latitud:"+ latitud);
    }

    public double distancia(GeoPunto punto) {
        final double RADIO_TIERRA = 6371000; // en metros
        double dLat = Math.toRadians(latitud - punto.latitud);
        double dLon = Math.toRadians(longitud - punto.longitud);
        double lat1 = Math.toRadians(punto.latitud);
        double lat2 = Math.toRadians(latitud);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) *
                        Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * RADIO_TIERRA;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeoPunto)) return false;
        GeoPunto geoPunto = (GeoPunto) o;
        return Double.compare(geoPunto.getLongitud(), getLongitud()) == 0 &&
                Double.compare(geoPunto.getLatitud(), getLatitud()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitud(), getLatitud());
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public static GeoPunto getSinPosicion() {
        return SIN_POSICION;
    }

    public static void setSinPosicion(GeoPunto sinPosicion) {
        SIN_POSICION = sinPosicion;
    }


}
