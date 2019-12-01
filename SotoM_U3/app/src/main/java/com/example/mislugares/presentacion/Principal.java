package com.example.mislugares.presentacion;

import com.example.mislugares.datos.LugaresLista;
import com.example.mislugares.datos.RepositorioLugares;

class Principal {
    public static void main(String[] main) {
        RepositorioLugares lugares = new LugaresLista();
        for (int i=0; i<lugares.tamanyo(); i++) {
            System.out.println(lugares.elemento(i).toString());
        }
    }
}