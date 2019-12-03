package es.upv.rausanlo.examentipo1;

import org.json.JSONObject;

public class Temp {
    private long Fecha;
    private String Habitacion;
    private int Valor;


    public long getFecha() {
        return Fecha;
    }

    public void setFecha(long fecha) {
        Fecha = fecha;
    }

    public String getHabitacion() {
        return Habitacion;
    }

    public void setHabitacion(String habitacion) {
        Habitacion = habitacion;
    }

    public int getValor() {
        return Valor;
    }

    public void setValor(int valor) {
        Valor = valor;
    }
}
