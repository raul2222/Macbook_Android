package es.upv.permisos_u5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private View vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vista = findViewById(R.id.content_main);
    }

    public void onClick(View view) {
        borrarLlamada();
        Snackbar.make(view, "Replace with your …", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    void borrarLlamada() {
        getContentResolver().delete(CallLog.Calls.CONTENT_URI,
                "number='555555555'", null);
        Snackbar.make(vista, "Llamadas borradas del registro.",
                Snackbar.LENGTH_SHORT).show();
    }

}

