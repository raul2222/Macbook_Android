package es.upv.epsg.igmagi.mislugares.presentacion;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import es.upv.epsg.igmagi.mislugares.R;
import es.upv.epsg.igmagi.mislugares.casos_uso.CasosUsoActividades;
import es.upv.epsg.igmagi.mislugares.casos_uso.CasosUsoLugar;
import es.upv.epsg.igmagi.mislugares.datos.RepositorioLugares;
import es.upv.epsg.igmagi.mislugares.modelo.Lugar;

public class MainActivity extends AppCompatActivity {
    private CasosUsoActividades usoActividades;
    private CasosUsoLugar usoLugar;
    private Lugar lugar;
    private RepositorioLugares lugares;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usoActividades = new CasosUsoActividades(this);
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // Botón mostrar lugares
        View v0 = findViewById(R.id.mostrarlugares);
        v0.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                usoActividades.lanzarMostrarLugares(view);
            }
        });
        // Botón ACERCA DE
        View v = findViewById(R.id.btn_acercade);
        v.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                /*
                Intent i = new Intent(view.getContext(), AcercaDeActivity.class);
                startActivity(i);


                lanzarAcercaDe(view);

                 */
                usoActividades.lanzarAcercaDe(view);
            }
        });

        //Botón SALIR
        View v2 = findViewById(R.id.salir);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Botón PREFERENCIAS
        View v3 = findViewById(R.id.preferencias);
        v3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usoActividades.lanzarPreferencias(v);
            }
        });
        v3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                usoActividades.mostrarPreferencias();
                return true;
            }
        });

    }

    public void lanzarVistaLugar(View view) {
        final EditText entrada = new EditText(this);
        entrada.setText("0");
        new AlertDialog.Builder(this)
                .setTitle("Selección de lugar")
                .setMessage("Indica su id:")
                .setView(entrada)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        int id = Integer.parseInt(entrada.getText().toString());
                        usoLugar.mostrar(id);
                    }})
                .setNegativeButton("Cancelar", null)
                .show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.acercaDe) {
            usoActividades.lanzarAcercaDe(null);
            return true;
        }
        if (id == R.id.action_settings) {
            usoActividades.lanzarPreferencias(null);
            return true;
        }
        if (id == R.id.menu_buscar) {
            lanzarVistaLugar(null);
            //usoLugar.mostrar(0);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


}
