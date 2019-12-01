package es.upv.comunicacionactividades;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;


public class SegActivity extends Activity {

    private TextView texto;
    private Button aceptar;
    private Button rechazar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seg_activity);
        Bundle extras = getIntent().getExtras();
        String s = extras.getString("usuario");
        texto = findViewById(R.id.textView3);
        texto.setText("Hola, " + s + ", ¿Aceptas las condiciones?");
        aceptar = findViewById(R.id.aceptar);
        rechazar = findViewById(R.id.rechazar);

        aceptar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cerrar1(null);
            }
        });

        rechazar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                cerrar2(null);
            }
        });




    }


    public void cerrar1 (View view){

        Intent intent = new Intent();
        intent.putExtra("resultado","Aceptado");
        setResult(RESULT_OK, intent);
        finish();

    }
    public void cerrar2 (View view){

        Intent intent = new Intent();
        intent.putExtra("resultado","Cancelado");
        setResult(RESULT_OK, intent);
        finish();

    }

}