package es.upv.rausanlo.practicaprocesado;

public class Temp {

    public static long FECHA;
    public static String HABITACION;
    public static int TEMPERATURA;

    public static long getFECHA() {
        return FECHA;
    }

    public static void setFECHA(long FECHA) {
        Temp.FECHA = FECHA;
    }

    public static String getHABITACION() {
        return HABITACION;
    }

    public static void setHABITACION(String HABITACION) {
        Temp.HABITACION = HABITACION;
    }

    public static int getTEMPERATURA() {
        return TEMPERATURA;
    }

    public static void setTEMPERATURA(int TEMPERATURA) {
        Temp.TEMPERATURA = TEMPERATURA;
    }
}
