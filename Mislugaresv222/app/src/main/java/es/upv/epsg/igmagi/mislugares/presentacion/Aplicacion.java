package es.upv.epsg.igmagi.mislugares.presentacion;

import android.app.Application;

import es.upv.epsg.igmagi.mislugares.datos.LugaresLista;
import es.upv.epsg.igmagi.mislugares.datos.RepositorioLugares;

public class Aplicacion extends Application {

    public RepositorioLugares lugares = new LugaresLista();
    @Override public void onCreate() {
        super.onCreate();
    }
    public RepositorioLugares getLugares() { //Quitar
        return lugares;
    }
    public AdaptadorLugares adaptador = new AdaptadorLugares(lugares);


}
