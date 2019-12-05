package es.upv.rausanlo.examentipo_b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceptorAnuncios extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("receptor", "anuncio");

        String cer = intent.getStringExtra("cerca");
        MainActivity.myText.setText(cer);

        //String lat = intent.getStringExtra("lat");
        //String lon = intent.getStringExtra("lon");
        //Log.e("receptor lat",lat);
        //MainActivity.lon.setText(lon);
        //MainActivity.lat.setText(lat);
    }

}
