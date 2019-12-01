package es.upv.rausanlo.examentipo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class ReceptorAnuncio extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("ReceptorAnuncio", "SMS");

        Intent i = new Intent(context, Segunda.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }
}

