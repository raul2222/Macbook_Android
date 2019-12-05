package es.upv.rausanlo.examentipo_b;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.os.IBinder;
import android.util.Log;


public class Servicio1 extends Service {

    //TextView ProximitySensor, data;
    SensorManager mySensorManager;
    Sensor myProximitySensor;

    @Override
    public void onCreate() {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //ProximitySensor = (TextView) findViewById(R.id.proximitySensor);
        //data = (TextView) findViewById(R.id.data);
        mySensorManager = (SensorManager) getSystemService(
                Context.SENSOR_SERVICE);
        myProximitySensor = mySensorManager.getDefaultSensor(
                Sensor.TYPE_PROXIMITY);
        if (myProximitySensor == null) {
            //ProximitySensor.setText("No Proximity Sensor!");
        } else {
            mySensorManager.registerListener(proximitySensorEventListener,
                    myProximitySensor,
                    1000);
        }
    }

    SensorEventListener proximitySensorEventListener = new SensorEventListener() {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            // TODO Auto-generated method stub
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
                //Log.e("prox ", String.valueOf(event.values[0]));
                Boolean cerca = false;

                if (event.values[0] >= 5.0) {
                    //data.setText("Near");
                    Log.e("prox: ", "lejos");
                } else {
                    //data.setText("Away");
                    Log.e("prox: ", "cerca");
                    cerca=true;
                }

                Intent i = new Intent(getApplicationContext(), ReceptorAnuncios.class);

                if(cerca==true){
                    i.putExtra("cerca", "cerca");
                } else {
                    i.putExtra("cerca", "lejos");
                }

                sendBroadcast(i);

            }
        }
    };


    @Override
    public IBinder onBind(Intent intencion) {
        return null;
    }
}
