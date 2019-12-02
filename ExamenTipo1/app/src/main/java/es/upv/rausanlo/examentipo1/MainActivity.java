package es.upv.rausanlo.examentipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {

    private EditText edit;
    private TextView lon;
    private TextView lat;

    public static final String ACTION_RESP =
            "com.example.intentservice.intent.action.RESPUESTA_OPERACION";
    @Override
    public void onReceive(Context context, Intent intent) {
        //Double res = intent.getDoubleExtra("resultado", 0.0);
        //salida.append(" " + res + "\n");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lon = findViewById(R.id.lon);
        lat =  findViewById(R.id.lat);


        Button arrancar = findViewById(R.id.B2);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.e("llega", "llega");
                //startService(new Intent(MainActivity.this, ServicioGPS.class));
                startForegroundService(new Intent(MainActivity.this, ServicioGPS.class));
            }
        });

    }

    public void botonUno(View view){
        edit = findViewById(R.id.editText);
        String url = "https://www.google.com/search?q=" + edit.getText();
        Log.e("url", url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
