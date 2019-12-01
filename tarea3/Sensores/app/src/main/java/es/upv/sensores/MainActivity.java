package es.upv.sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    private TextView salida;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout. activity_main);
        salida = findViewById(R.id.salida);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> listaSensores = sensorManager.
                getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: listaSensores) {
            log(sensor.getName());
        }


        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (!listaSensores.isEmpty()) {
            Sensor orientationSensor = listaSensores.get(0);
            sensorManager.registerListener(this, orientationSensor,
                    SensorManager.SENSOR_DELAY_UI);}

        listaSensores = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (!listaSensores.isEmpty()) {
            Sensor acelerometerSensor = listaSensores.get(0);
            sensorManager.registerListener(this, acelerometerSensor,
                    SensorManager.SENSOR_DELAY_UI);}
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        if (!listaSensores.isEmpty()) {
            Sensor magneticSensor = listaSensores.get(0);
            sensorManager.registerListener(this, magneticSensor,
                    SensorManager.SENSOR_DELAY_UI);}
        listaSensores = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (!listaSensores.isEmpty()) {
            Sensor proximidadSensor = listaSensores.get(0);
            sensorManager.registerListener(this, proximidadSensor,
                    SensorManager.SENSOR_DELAY_UI);}


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int precision) {}
    @Override
    public void onSensorChanged(SensorEvent evento) {
        switch(evento.sensor.getType()) {
            case Sensor.TYPE_ORIENTATION:
                for (int i=0 ; i<3 ; i++) {
                    log("Orientación "+i+": "+evento.values[i]);
                }
                break;
            case Sensor.TYPE_ACCELEROMETER:
                for (int i=0 ; i<3 ; i++) {log("Acelerómetro "+i+": "+evento.values[i]);
                }
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                for (int i=0 ; i<3 ; i++) {
                    log("Magnetismo "+i+": "+evento.values[i]);
                }
                break;
            default:
                for (int i=0 ; i<evento.values.length ; i++) {
                    log("Proximidad"+i+": "+evento.values[i]);
                }
        }
    }




    private void log(String string) {
        salida.append(string + "\n");
    }
}