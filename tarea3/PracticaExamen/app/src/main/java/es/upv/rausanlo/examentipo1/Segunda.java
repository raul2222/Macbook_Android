package es.upv.rausanlo.examentipo1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Segunda extends Activity {

    private TextView texto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segunda);


        try {

            texto = findViewById(R.id.TextView01);
            Bundle extras = getIntent().getExtras();

            String s = extras.getString("tel");
            texto.setText(s);
        } catch (Exception ex) {

        }

    }


}