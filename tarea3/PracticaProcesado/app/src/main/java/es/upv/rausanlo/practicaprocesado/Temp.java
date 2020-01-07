package es.upv.rausanlo.practicaprocesado;

public class Temp {

    private long FECHA;
    private String HABITACION;
    private int TEMPERATURA;

    public Temp(long l, String s, int i) {
        this.FECHA = l;
        this.HABITACION = s;
        this.TEMPERATURA = i;
    }

    public Temp() {
    }
    public  long getFECHA() {
        return FECHA;
    }

    public  void setFECHA(long FECHA) { this.FECHA = FECHA;}

    public  String getHABITACION() {
        return HABITACION;
    }

    public  void setHABITACION(String HABITACION) {
        this.HABITACION = HABITACION;
    }

    public  int getTEMPERATURA() {
        return TEMPERATURA;
    }

    public  void setTEMPERATURA(int TEMPERATURA) {
        this.TEMPERATURA = TEMPERATURA;
    }


}
