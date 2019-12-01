package es.upv.masvistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entrada = findViewById(R.id.entrada);
        salida = findViewById(R.id.salida);
    }

    public void sePulsa(View view){
        //Toast.makeText(this, "Pulsado", Toast.LENGTH_SHORT).show();
        try {
            salida.setText(String.valueOf(Float.parseFloat(
                    entrada.getText().toString()) * 2.0));
        } catch (Exception ex){
            Toast.makeText(getApplicationContext(),"pon un número!!",Toast.LENGTH_LONG).show();
        }
    }

    public void sePulsa0(View view){
        //entrada.setText(entrada.getText()+"0");
        entrada.setText(entrada.getText()+(String)view.getTag());
    }

}
