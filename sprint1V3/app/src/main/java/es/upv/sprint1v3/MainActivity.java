package es.upv.sprint1v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class MainActivity extends AppCompatActivity {
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("TAG", "Lista de UART disponibles: " + ArduinoUart.disponibles());
        Log.i("TAG", "UART: ");
        ArduinoUart uart = new ArduinoUart("UART0", 9600);

        /*
        Log.d("TAG", "Mandado a Arduino: H");
        uart.escribir("H");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Log.w("TAG", "Error en sleep()", e);
        }
        */
        salida = this.findViewById(R.id.data);
        while(true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.w("TAG", "Error en sleep()", e);
            }
            String s = uart.leer();
            if (s != ""){
                salida.setText(s);
                Log.d("TAG", "Recibido de Arduino: "+s);
            }
            //Log.d("TAG", "Recibido de Arduino: "+s);

        }



    }
}
