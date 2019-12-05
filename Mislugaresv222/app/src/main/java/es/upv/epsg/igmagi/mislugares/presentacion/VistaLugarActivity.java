package es.upv.epsg.igmagi.mislugares.presentacion;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.util.Date;

import es.upv.epsg.igmagi.mislugares.R;
import es.upv.epsg.igmagi.mislugares.casos_uso.CasosUsoLugar;
import es.upv.epsg.igmagi.mislugares.datos.RepositorioLugares;
import es.upv.epsg.igmagi.mislugares.modelo.Lugar;

import static android.text.TextUtils.isEmpty;

public class VistaLugarActivity extends AppCompatActivity {

    final static int RESULTADO_EDITAR = 1;
    final static int RESULTADO_GALERIA = 2;
    final static int RESULTADO_FOTO = 3;

    private RepositorioLugares lugares;
    private CasosUsoLugar usoLugar;
    private int pos;
    private Lugar lugar;

    private ImageView foto;
    private Uri uriUltimaFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_lugar);
        Bundle extras = getIntent().getExtras();
        pos = extras.getInt("pos", 0);
        lugares = ((Aplicacion) getApplication()).lugares;
        usoLugar = new CasosUsoLugar(this, lugares);
        lugar = lugares.elemento(pos);
        foto = findViewById(R.id.foto);

        View x = findViewById(R.id.ll_direccion);
        x.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                verMapa(v);
            }
        });
        View x1 = findViewById(R.id.ll_web);
        x1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                verPgWeb(v);
            }
        });
        View x2 = findViewById(R.id.ll_telefono);
        x2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                llamarTelefono(v);
            }
        });
        View x3 = findViewById(R.id.camara);
        x3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                tomarFoto(v);
            }
        });
        actualizaVistas();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.vista_lugar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.accion_compartir:
                usoLugar.compartir(lugar);
                return true;
            case R.id.accion_llegar:
                usoLugar.verMapa(lugar);
                return true;
            case R.id.accion_editar:
                usoLugar.editar(pos, RESULTADO_EDITAR);
                return true;
            case R.id.accion_borrar:
                usoLugar.borrar(pos);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == RESULTADO_EDITAR) {
            actualizaVistas();
            findViewById(R.id.scrollView1).invalidate(); //¿Hace falta?
        }else if(requestCode == RESULTADO_GALERIA){
            if (resultCode == Activity.RESULT_OK) {
                usoLugar.ponerFoto(pos, data.getDataString(), foto);
            } else {
                Toast.makeText(this, "Foto no cargada",Toast.LENGTH_LONG).show();
            }
        }else if (requestCode == RESULTADO_FOTO) {
            if (resultCode == Activity.RESULT_OK && uriUltimaFoto!=null) {
                lugar.setFoto(uriUltimaFoto.toString());
                usoLugar.ponerFoto(pos, lugar.getFoto(), foto);
            } else {
                Toast.makeText(this, "Error en captura", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void actualizaVistas() {
        TextView nombre = findViewById(R.id.nombre);
        nombre.setText(lugar.getNombre());
        ImageView logo_tipo = findViewById(R.id.logo_tipo);
        logo_tipo.setImageResource(lugar.getTipo().getRecurso());
        TextView tipo = findViewById(R.id.tipo);
        tipo.setText(lugar.getTipo().getTexto());
        //si no hay dirección
        if (isEmpty(lugar.getDireccion())) {
            findViewById(R.id.direccion).setVisibility(View.GONE);
        } else {
            findViewById(R.id.direccion).setVisibility(View.VISIBLE);
            TextView direccion = findViewById(R.id.direccion);
            direccion.setText(lugar.getDireccion());
        }


        //si no hay teléfono
        if (lugar.getTelefono() == 0) {
            findViewById(R.id.telefono).setVisibility(View.GONE);
            findViewById(R.id.logo_telefono).setVisibility(View.GONE);
        } else {
            findViewById(R.id.logo_telefono).setVisibility(View.VISIBLE);
            findViewById(R.id.telefono).setVisibility(View.VISIBLE);
            TextView telefono = findViewById(R.id.telefono);
            telefono.setText(Integer.toString(lugar.getTelefono()));
        }

        //si no hay url
        if (isEmpty(lugar.getUrl())) {
            findViewById(R.id.url).setVisibility(View.GONE);
        } else {
            findViewById(R.id.url).setVisibility(View.VISIBLE);
            TextView url = findViewById(R.id.url);
            url.setText(lugar.getUrl());
        }

        //si no hay comentario
        if (isEmpty(lugar.getComentario())) {
            findViewById(R.id.comentario).setVisibility(View.GONE);
        } else {
            findViewById(R.id.comentario).setVisibility(View.VISIBLE);
            TextView comentario = findViewById(R.id.comentario);
            comentario.setText(lugar.getComentario());
        }


        TextView fecha = findViewById(R.id.fecha);
        fecha.setText(DateFormat.getDateInstance().format(
                new Date(lugar.getFecha())));
        TextView hora = findViewById(R.id.hora);
        hora.setText(DateFormat.getTimeInstance().format(
                new Date(lugar.getFecha())));
        RatingBar valoracion = findViewById(R.id.valoracion);
        valoracion.setRating(lugar.getValoracion());
        valoracion.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar,
                                                float valor, boolean fromUser) {
                        lugar.setValoracion(valor);
                    }
                });
        usoLugar.visualizarFoto(lugar, foto);
    }

    void verMapa(View view) {
        usoLugar.verMapa(lugar);
    }

    void llamarTelefono(View view) {
        usoLugar.llamarTelefono(lugar);
    }

    void verPgWeb(View view) {
        usoLugar.verPgWeb(lugar);
    }

    public void ponerDeGaleria(View view) {
        usoLugar.ponerDeGaleria(RESULTADO_GALERIA);
    }

    void tomarFoto(View view) {
        uriUltimaFoto = usoLugar.tomarFoto(RESULTADO_FOTO);
    }

    public void eliminarFoto(View view) {
        usoLugar.ponerFoto(pos, "", foto);
    }

}