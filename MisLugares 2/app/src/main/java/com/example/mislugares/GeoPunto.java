package com.example.mislugares;

public class GeoPunto {
    //declaración de atributos
    private double longitud, latitud;

    //declaración de constructor
    public GeoPunto(double longitud, double latitud){
        this.longitud = longitud;
        this.latitud = latitud;
    }

    /*
    retorna un string con la información del GeoPunto
     */
    public String toString(){
        return "longitud: " + longitud + " latutud: " + latitud;
    }

    public double distancia(GeoPunto punto){
        final double RADIO_TIERRA = 6371000; // en metros
        double dLat = Math.toRadians(latitud - punto.latitud);
        double dLon = Math.toRadians(longitud - punto.longitud);
        double lat1 = Math.toRadians(punto.latitud);
        double lat2 = Math.toRadians(latitud);
        double a =    Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.sin(dLon/2) * Math.sin(dLon/2) *
                        Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return c * RADIO_TIERRA;
    }


}
