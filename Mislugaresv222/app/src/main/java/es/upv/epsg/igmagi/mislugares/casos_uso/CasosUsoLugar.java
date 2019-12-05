package es.upv.epsg.igmagi.mislugares.casos_uso;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import es.upv.epsg.igmagi.mislugares.datos.RepositorioLugares;
import es.upv.epsg.igmagi.mislugares.modelo.GeoPunto;
import es.upv.epsg.igmagi.mislugares.modelo.Lugar;
import es.upv.epsg.igmagi.mislugares.presentacion.EdicionLugarActivity;
import es.upv.epsg.igmagi.mislugares.presentacion.VistaLugarActivity;

public class CasosUsoLugar {
    private Activity actividad;
    private RepositorioLugares lugares;

    public CasosUsoLugar(Activity actividad, RepositorioLugares lugares) {
        this.actividad = actividad;
        this.lugares = lugares;
    }

    // OPERACIONES BÁSICAS
    public void mostrar(int pos) {
        Intent i = new Intent(actividad, VistaLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivity(i);
    }

    public void editar(int pos, int codigoSolicitud) {
        Intent i = new Intent(actividad, EdicionLugarActivity.class);
        i.putExtra("pos", pos);
        actividad.startActivityForResult(i, codigoSolicitud);
    }

    public void guardar(int id, Lugar nuevoLugar) {
        lugares.actualiza(id, nuevoLugar);
    }

    public void borrar(final int id) {

        new AlertDialog.Builder(actividad)
                .setTitle("Borrado del lugar")
                .setMessage("¿Está seguro que quiere eliminar este lugar?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        lugares.borrar(id);
                        actividad.finish();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();


    }

    // INTENCIONES
    public void compartir(Lugar lugar) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_TEXT,
                lugar.getNombre() + " - " + lugar.getUrl());
        actividad.startActivity(i);
    }

    public void llamarTelefono(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + lugar.getTelefono())));
    }

    public void verPgWeb(Lugar lugar) {
        actividad.startActivity(new Intent(Intent.ACTION_VIEW,
                Uri.parse(lugar.getUrl())));
    }

    public final void verMapa(Lugar lugar) {
        double lat = lugar.getPosicion().getLatitud();
        double lon = lugar.getPosicion().getLongitud();
        Uri uri = lugar.getPosicion() != GeoPunto.SIN_POSICION
                ? Uri.parse("geo:" + lat + ',' + lon)
                : Uri.parse("geo:0,0?q=" + lugar.getDireccion());
        actividad.startActivity(new Intent("android.intent.action.VIEW", uri));
    }

    public void ponerDeGaleria(int codigoSolicitud) {
        String action;
        if (android.os.Build.VERSION.SDK_INT >= 19) { // API 19 - Kitkat
            action = Intent.ACTION_OPEN_DOCUMENT;
        } else {
            action = Intent.ACTION_PICK;
        }
        Intent intent = new Intent(action,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        actividad.startActivityForResult(intent, codigoSolicitud);
    }

    public void ponerFoto(int pos, String uri, ImageView imageView) {
        Lugar lugar = lugares.elemento(pos);
        lugar.setFoto(uri);
        visualizarFoto(lugar, imageView);
    }

    public void visualizarFoto(Lugar lugar, ImageView imageView) {
        if (lugar.getFoto() != null && !lugar.getFoto().isEmpty()) {
            //imageView.setImageURI(Uri.parse(lugar.getFoto()));
            imageView.setImageBitmap(reduceBitmap(actividad,lugar.getFoto(),1024,1024));
        } else {
            imageView.setImageBitmap(null);
        }
    }

    public Uri tomarFoto(int codidoSolicitud) {
        try {
            Uri uriUltimaFoto;
            File file = File.createTempFile(
                    "img_" + (System.currentTimeMillis() / 1000), ".jpg",
                    actividad.getExternalFilesDir(Environment.DIRECTORY_PICTURES));
            if (Build.VERSION.SDK_INT >= 24) {
                uriUltimaFoto = FileProvider.getUriForFile(
                        actividad, "es.upv.epsg.igmagi.mislugares.fileProvider", file);
            } else {
                uriUltimaFoto = Uri.fromFile(file);
            }
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uriUltimaFoto);
            actividad.startActivityForResult(intent, codidoSolicitud);
            return uriUltimaFoto;
        } catch (IOException ex) {
            Toast.makeText(actividad, "Error al crear fichero de imagen",
                    Toast.LENGTH_LONG).show();
            return null;
        }
    }

    private Bitmap reduceBitmap(Context contexto, String uri,
                                int maxAncho, int maxAlto) {
        try {
            InputStream input = null;
            Uri u = Uri.parse(uri);
            if (u.getScheme().equals("http") || u.getScheme().equals("https")) {
                input = new URL(uri).openStream();
            } else {
                input = contexto.getContentResolver().openInputStream(u);
            }
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            options.inSampleSize = (int) Math.max(
                    Math.ceil(options.outWidth / maxAncho),
                    Math.ceil(options.outHeight / maxAlto));
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(input, null, options);
        } catch (FileNotFoundException e) {
            Toast.makeText(contexto, "Fichero/recurso de imagen no encontrado",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            Toast.makeText(contexto, "Error accediendo a imagen",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        }
    }



}
