package es.upv.epsg.igmagi.mislugares.casos_uso;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Toast;

import es.upv.epsg.igmagi.mislugares.presentacion.AcercaDeActivity;
import es.upv.epsg.igmagi.mislugares.presentacion.MostrarLugaresActivity;
import es.upv.epsg.igmagi.mislugares.presentacion.PreferenciasActivity;

public class CasosUsoActividades {
    public Activity actividad;

    public CasosUsoActividades(Activity actividad) {
        this.actividad = actividad;
    }

    public void lanzarAcercaDe(View view) {
        Intent i = new Intent(actividad, AcercaDeActivity.class);
        actividad.startActivity(i);
    }

    public void lanzarPreferencias(View v) {
        Intent i = new Intent(actividad, PreferenciasActivity.class);
        actividad.startActivity(i);
    }

    public void lanzarMostrarLugares(View v) {
        Intent i = new Intent(actividad, MostrarLugaresActivity.class);
        actividad.startActivity(i);
    }

    public void mostrarPreferencias(){
        SharedPreferences pref =
                PreferenceManager.getDefaultSharedPreferences(actividad);
        String s = "Notificaciones: " + pref.getBoolean("notif",true)
                +", Ordenación: " + pref.getString("ordenacion","Creation");
        Toast.makeText(actividad, s, Toast.LENGTH_SHORT).show();
    }
}
