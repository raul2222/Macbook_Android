package es.upv.procesadob3;

public class Clase {
    private String dato1;
    private String dato2;

    public Clase(String dato1, String dato2) {
        this.dato1 = dato1;
        this.dato2 = dato2;
    }

    public Clase(){}

    public String getDato1() {
        return dato1;
    }

    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }

    public String getDato2() {
        return dato2;
    }

    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }

    public double getCampo1() {
        return 10.1;
    }
}
