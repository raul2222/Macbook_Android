package es.upv.rausanlo.examentipo1;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class ServicioGPS extends Service {






    @Override
    public void onCreate() {

        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();

        //double longitude1 = location.getLongitude();
        //double latitude1 = location.getLatitude();

        //Log.e("Long: ", String.valueOf(longitude1));
        //Log.e("Lat: ", String.valueOf(latitude1));

        showData();

    }

    public void showData() {


        Thread timer = new Thread() {
            public void run() {
                try {
                    while (true) {
                        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        double longitude1 = location.getLongitude();
                        double latitude1 = location.getLatitude();

                        Log.e("Long: ", String.valueOf(longitude1));
                        Log.e("Lat: ", String.valueOf(latitude1));
                        sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

    }

    @Override
    public IBinder onBind(Intent intencion) {
        return null;
    }


}
