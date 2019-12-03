package es.upv.rausanlo.examentipo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ReceptorAnuncios extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("receptor","anuncio");

        String lat = intent.getStringExtra("lat");
        String lon = intent.getStringExtra("lon");
        //Log.e("receptor lat",lat);
        MainActivity.lon.setText(lon);
        MainActivity.lat.setText(lat);
    }
}
