package com.example.mislugares.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.mislugares.Aplicacion;
import com.example.mislugares.R;
import com.example.mislugares.RepositorioLugares;
import com.example.mislugares.casos_uso.CasosUsoActividades;
import com.example.mislugares.casos_uso.CasosUsoLugar;
import com.example.mislugares.presentacion.AcercaDeActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button bAcercaDe;

    private RepositorioLugares lugares;
    private CasosUsoLugar usoLugar;
    private CasosUsoActividades casos;

    MediaPlayer mp;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares);

        bAcercaDe = findViewById(R.id.button5);
        /*
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //lanzarAcercaDe(null);
                casos = new CasosUsoActividades();
                casos.lanzarAcercaDe(view);
            }
        });
        */

        mp = MediaPlayer.create(this, R.raw.audio);
        mp.start();
    }

    @Override protected void onStop() {
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
        mp.pause();
        //mp.start();

        super.onStop();
    }

    @Override protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onResume() {
        super.onResume();
        mp.start();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override protected void onPause() {
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        mp.pause();
        super.onPause();
    }

    String var;
    int pos;
    @Override protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);
        //guardarEstado.putString("variable", var);
        //guardarEstado.putInt("posicion", pos);
        int pos = mp.getCurrentPosition();
        guardarEstado.putInt("posicion", pos);
    }
    @Override protected void onRestoreInstanceState(Bundle recEstado) {
        super.onRestoreInstanceState(recEstado);
        //var = recEstado.getString("variable");
        int pos = recEstado.getInt("posicion");

    }

    /*
    public void lanzarAcercaDe (View view){
        Intent i = new Intent(this, AcercaDeActivity.class);
        startActivity(i);
    }
    */

}

