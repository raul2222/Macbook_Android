package es.upv.rausanlo.examentipo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorAnuncios extends BroadcastReceiver {

    public static final String ACTION_RESP =
            "com.example.intentservice.intent.action.RESPUESTA_OPERACION";
    @Override
    public void onReceive(Context context, Intent intent) {
        Double res = intent.getDoubleExtra("resultado", 2);
        //salida.append(" " + res + "\n");
    }
}
