package es.upv.rausanlo.examentipo1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;



public class ReceptorAnuncio extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");

        final SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++) {
            messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
        }

        String tel = messages[0].getOriginatingAddress();

        Log.e("ReceptorAnuncio", tel);

        Log.e("ReceptorAnuncio", "SMS");

        Intent i = new Intent(context, Segunda.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("tel", tel);
        context.startActivity(i);

    }
}

