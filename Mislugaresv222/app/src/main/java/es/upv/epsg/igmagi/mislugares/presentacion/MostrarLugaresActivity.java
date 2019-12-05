package es.upv.epsg.igmagi.mislugares.presentacion;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.upv.epsg.igmagi.mislugares.R;
import es.upv.epsg.igmagi.mislugares.casos_uso.CasosUsoLugar;
import es.upv.epsg.igmagi.mislugares.datos.LugaresLista;
import es.upv.epsg.igmagi.mislugares.datos.RepositorioLugares;

public class MostrarLugaresActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    public AdaptadorLugares adaptador;
    private CasosUsoLugar usoLugar;
    private RepositorioLugares lugares;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_vistas);
        Bundle extras = getIntent().getExtras();
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares);

        adaptador = ((Aplicacion) getApplication()).adaptador;
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adaptador);

        adaptador.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                usoLugar.mostrar(pos);
            }
        });
    }
}
