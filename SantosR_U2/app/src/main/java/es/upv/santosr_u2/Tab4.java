package es.upv.santosr_u2;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Tab4 extends Fragment {

    private TextView display;
    private ArrayList<Double> lista;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.tab4, container, false);
        View v = inflater.inflate(R.layout.tab4,container,false);
        display = v.findViewById(R.id.display);

        lista = new ArrayList<>();

        Button uno = v.findViewById(R.id.uno);
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button dos = v.findViewById(R.id.dos);
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button tres = v.findViewById(R.id.tres);
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button cuatro = v.findViewById(R.id.cuatro);
        cuatro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button cinco = v.findViewById(R.id.cinco);
        cinco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button seis = v.findViewById(R.id.seis);
        seis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button siete = v.findViewById(R.id.siete);
        siete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button ocho = v.findViewById(R.id.ocho);
        ocho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button nueve = v.findViewById(R.id.nueve);
        nueve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });
        Button cero = v.findViewById(R.id.cero);
        cero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sePulsa(view);
            }
        });

        //el mas
        Button mas = v.findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pulsaMas(view);
            }
        });
        //el igual
        Button igual = v.findViewById(R.id.igual);
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsaIgual(v);
            }
        });
        // pts
        Button pts = v.findViewById(R.id.pts);
        pts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsaPts(v);
            }
        });
        // c
        Button c = v.findViewById(R.id.c);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsaC(v);
            }
        });

        return v;
    }

    public void pulsaC(View view) {
        display.setText("");
        lista.clear();
    }

    public void pulsaPts(View view) {
        if(display.getText().toString() != "") {
            double total = 0;
            double cantidad = Double.parseDouble(display.getText().toString());
            total = cantidad * 166.386;
            display.setText(Double.toString(total));
        }
    }

    public void pulsaIgual(View view) {
        pulsaMas(view);
        double total =0;
        for (int i = 0; i<lista.size();i++){
            total = total + lista.get(i);
        }
        display.setText(Double.toString(total));
        lista.clear();
    }

    public void pulsaMas(View view) {
        if(display.getText().toString() != "") {
            String num = display.getText().toString();
            System.out.println(num);
            lista.add(Double.parseDouble(num));
            display.setText("");
        }
    }

    public void sePulsa(View view){
        //System.out.println(((Button)view).getText());
        //display.setText("1");
        System.out.println(display.getText());

        display.setText(display.getText() + (String)((Button)view).getText() );
    }


}