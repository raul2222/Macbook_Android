package es.upv.rausanlo.examentipo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity  {

    private EditText edit;
    public static TextView lon;
    public static TextView lat;
    public static final int MY_PERMISSIONS_REQUEST_FINE = 100;

    static String tempe = "{'temperatura': ["+
            " {'fecha':1288122023000, 'habitacion':'cocina', 'valor':18},"+
            " {'fecha':1288122023000, 'habitacion':'baño', 'valor':19},"+
            " {'fecha':1288122023000, 'habitacion':'comedor','valor':20},"+
            " {'fecha':1288122223000, 'habitacion':'cocina', 'valor':17},"+
            " {'fecha':1288122223000, 'habitacion':'baño', 'valor':19},"+
            " {'fecha':1288122223000, 'habitacion':'comedor','valor':17},"+
            " {'fecha':1288122623000, 'habitacion':'cocina', 'valor':19},"+
            " {'fecha':1288122623000, 'habitacion':'baño', 'valor':22},"+
            " {'fecha':1288122623000, 'habitacion':'comedor','valor':22},"+
            "]}";
    List<Temp> lista_habitaciones = new ArrayList<>();

    Set<String> habitaciones = new HashSet<>();

    private void process(){

        try {
            Log.e("total", String.valueOf(lista_habitaciones.size()));

            JSONObject jsonResponse = new JSONObject(tempe);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("temperatura");

            for(int i = 0; i<jsonMainNode.length() - 1 ;i++){
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                Temp t = new Temp();
                t.setFecha(jsonChildNode.optLong("fecha"));
                t.setHabitacion(jsonChildNode.optString("habitacion"));
                habitaciones.add(jsonChildNode.optString("habitacion"));
                t.setValor(jsonChildNode.optInt("valor"));
                lista_habitaciones.add(t);
            }
            Log.e("total", String.valueOf(lista_habitaciones.size()));
            Log.e("set", String.valueOf(habitaciones));

            double cocina = 0;
            int x = 0;
            for (int i = 0 ; i < lista_habitaciones.size(); i++){
                String hab =  lista_habitaciones.get(i).getHabitacion();
                if(hab.equals("cocina")){
                    cocina = cocina + (double)lista_habitaciones.get(i).getValor();
                    x++;
                }
            }
            cocina = cocina / x;
            Log.e("cocina", String.valueOf(cocina));

            double baño = 0;
            x = 0;
            for (int i = 0 ; i < lista_habitaciones.size(); i++){
                String hab =  lista_habitaciones.get(i).getHabitacion();
                if(hab.equals("baño")){
                    baño = baño + (double)lista_habitaciones.get(i).getValor();
                    x++;
                }
            }
            baño = baño / x;
            Log.e("baño", String.valueOf(baño));

            double comedor = 0;
            x = 0;
            for (int i = 0 ; i < lista_habitaciones.size(); i++){
                String hab =  lista_habitaciones.get(i).getHabitacion();
                if(hab.equals("comedor")){
                    comedor = comedor + (double)lista_habitaciones.get(i).getValor();
                    x++;
                }
            }
            comedor = comedor / x;
            Log.e("comedor", String.valueOf(comedor));



        } catch (Exception e) {
            e.toString();
            Log.e("erro", e.toString());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        process();
        lon = findViewById(R.id.lon);
        lat =  findViewById(R.id.lat);

        Button arrancar = findViewById(R.id.B2);
        arrancar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.e("llega", "llega");
                //startService(new Intent(MainActivity.this, ServicioGPS.class));
                // Here, thisActivity is the current activity
                if (ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

                    // Permission is not granted
                    // Should we show an explanation?
                    if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                            Manifest.permission.READ_CONTACTS)) {
                        // Show an explanation to the user *asynchronously* -- don't block
                        // this thread waiting for the user's response! After the user
                        // sees the explanation, try again to request the permission.
                    } else {
                        // No explanation needed; request the permission
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                MY_PERMISSIONS_REQUEST_FINE);

                        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                        // app-defined int constant. The callback method gets the
                        // result of the request.
                    }
                } else {
                    // Permission has already been granted
                    Log.e("per2", "yes");
                    startServicios();
                }

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    startServicios();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void startServicios(){
        startForegroundService(new Intent(MainActivity.this, ServicioGPS.class));
    }



    public void botonUno(View view){
        edit = findViewById(R.id.editText);
        String url = "https://www.google.com/search?q=" + edit.getText();
        Log.e("url", url);
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }




}
