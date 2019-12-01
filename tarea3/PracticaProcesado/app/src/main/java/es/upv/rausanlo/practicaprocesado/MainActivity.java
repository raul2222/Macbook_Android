package es.upv.rausanlo.practicaprocesado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static long FECHA[]={1288122023000L, 1288122023000L, 1288122223000L, 1288122223000L,
            1288122223000L, 1288122623000L, 1288122623000L, 1288122623000L };
    static String HABITACION[] = {"cocina", "baño", "cocina", "baño",
            "comedor", "cocina","baño", "comedor"};
    static int TEMPERATURA[] = {18, 19, 17, 19,
            17, 19, 22, 22};

    List<Temp> temperaturas = new ArrayList<Temp>();
    Set<String> habitaciones = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        double tempMedia =0;
        for (int i = 0; i < FECHA.length; i++){
            Temp t = new Temp();
            t.FECHA = FECHA[i];
            t.HABITACION = HABITACION[i];
            t.TEMPERATURA = TEMPERATURA[i];

            tempMedia = tempMedia + TEMPERATURA[i];

            temperaturas.add(t);
        }

        Log.e("TempMedia: ", String.valueOf(tempMedia));

        for (int i = 0; i < FECHA.length; i++){
            habitaciones.add(HABITACION[i].toString());
        }

        Log.e("SET", String.valueOf(habitaciones));






    }
}
