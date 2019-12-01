package es.upv.serviciomusica;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

public class ReceptorLlamadas extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {

        // Sacamos información de la intención
        String estado = "", numero = "";
        Bundle extras = intent.getExtras();
        Log.e("llega","hola");

        if (extras != null) {
            estado = extras.getString(TelephonyManager.EXTRA_STATE);
            if (estado.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                numero = extras.getString(
                        TelephonyManager.EXTRA_INCOMING_NUMBER);
                String info = estado + " " + numero;
                Log.e("ReceptorAnuncio", info + " intent=" + intent);
                NotificationCompat.Builder notificacion = new
                        NotificationCompat.Builder(context)
                        .setContentTitle("Llamada entrante ")
                        .setContentText(info)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(PendingIntent.getActivity(context, 0,
                                new Intent(context, MainActivity.class), 0));
                ((NotificationManager) context.getSystemService(Context.
                        NOTIFICATION_SERVICE)).notify(1,notificacion.build());
            }
        }
    }
}