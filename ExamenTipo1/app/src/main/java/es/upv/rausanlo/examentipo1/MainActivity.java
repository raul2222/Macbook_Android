package es.upv.rausanlo.examentipo1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity  {

    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button arrancar = findViewById(R.id.B2);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.e("llega", "llega");
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
