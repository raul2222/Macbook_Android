package com.example.mislugares;

import android.app.Application;
import android.util.Log;

import com.example.mislugares.datos.LugaresLista;
import com.example.mislugares.datos.RepositorioLugares;

public class Aplicacion extends Application {
    //public AdaptadorLugares adaptadorLugares = new AdaptadorLugares(getLugares());
    public RepositorioLugares lugares = new LugaresLista();
    @Override
    public void onCreate() {
        super.onCreate();
    }
    public RepositorioLugares getLugares() { //Quitar
        return lugares;
    }
}