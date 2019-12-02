package es.upv.rausanlo.examentipo1;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class ServicioGPS extends Service {

    private NotificationManager notificationManager;
    static final String CANAL_ID = "mi_canal";
    static final int NOTIFICACION_ID = 1;

    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public int onStartCommand(Intent intenc, int flags, int idArranque) {
        Toast.makeText(this, "Servicio arrancado " + idArranque,
                Toast.LENGTH_SHORT).show();


        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    CANAL_ID, "Mis Notificaciones",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Descripcion del canal");
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificacion =
                new NotificationCompat.Builder(this, CANAL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("GPS")
                        .setContentText("Tracker.");
        //notificationManager.notify(NOTIFICACION_ID, notificacion.build());
        startForeground(NOTIFICACION_ID, notificacion.build());

        showData();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio detenido",
                Toast.LENGTH_SHORT).show();
        Log.e("Servicio detenido: ", "Service down");
        //notificationManager.cancel(NOTIFICACION_ID);
        stopForeground(false);

    }

    @Override
    public IBinder onBind(Intent intencion) {
        return null;
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
                    //e.printStackTrace();
                }
            }
        };
        timer.start();

    }


}



