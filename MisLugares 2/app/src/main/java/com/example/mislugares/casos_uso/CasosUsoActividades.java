package com.example.mislugares.casos_uso;

import android.content.Intent;
import android.view.View;

import com.example.mislugares.presentacion.AcercaDeActivity;

import static androidx.core.content.ContextCompat.startActivity;

public class CasosUsoActividades {

    public void lanzarAcercaDe (View view){
        Intent i = new Intent(view.getContext(), AcercaDeActivity.class);
        view.getContext().startActivity(i);
    }


    public void lanzarPreferencias (View view){
        Intent i = new Intent(view.getContext(), AcercaDeActivity.class);
        view.getContext().startActivity(i);
    }

}
