package es.upv.santosr_u2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;

public class Tab3 extends Fragment {

    private EditText entrada;
    private TextView salida;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.tab3);
        //entrada = findViewById(R.id.entrada);
        //salida = findViewById(R.id.salida);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab3, container, false);
        View v = inflater.inflate(R.layout.tab3, container, false);
        entrada = v.findViewById(R.id.entrada);
        salida = v.findViewById(R.id.salida);
        Button boton1 = v.findViewById(R.id.button);
        boton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                sePulsa(view);

            }
        });

        Button boton2 = v.findViewById(R.id.button0);
        boton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                sePulsa0(view);

            }
        });

        return v;
    }



    public void sePulsa(View view){
        //Toast.makeText(this, "Pulsado", Toast.LENGTH_SHORT).show();
        try {
            if (entrada == null) {
                entrada = entrada.findViewById(R.id.entrada);
            }
            salida.setText(String.valueOf(Float.parseFloat(entrada.getText().toString()) * 2.0));
        } catch (Exception ex){
            //Toast.makeText(es.upv.santosr_u2.Tab3,"pon un número!!",Toast.LENGTH_LONG).show();
        }
    }

    public void sePulsa0(View view){
        //entrada.setText(entrada.getText()+"0");

        entrada.setText(entrada.getText()+(String)view.getTag());
    }
}
