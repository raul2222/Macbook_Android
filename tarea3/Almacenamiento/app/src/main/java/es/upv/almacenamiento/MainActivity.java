package es.upv.almacenamiento;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import es.upv.comun.Imagen;

import static android.provider.MediaStore.Images.Media.getBitmap;

public class MainActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private AdaptadorImagenes adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 1234);
            }
        });


        //bajarFichero();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Query query = FirebaseFirestore.getInstance()
                .collection("images")
                .orderBy("tiempo", Query.Direction.DESCENDING)
                .limit(50);
        FirestoreRecyclerOptions<Imagen> opciones = new FirestoreRecyclerOptions
                .Builder<Imagen>().setQuery(query, Imagen.class).build();
        adaptador = new AdaptadorImagenes(this, opciones);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




    }

    @Override public void onStart() {
        super.onStart();
        adaptador.startListening();
    }
    @Override public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }


    private void bajarFichero() {
        File localFile = null;
        try {
            localFile = File.createTempFile("image", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final String path = localFile.getAbsolutePath();
        Log.d("Almacenamiento", "creando fichero: " + path);
        StorageReference ficheroRef = mStorageRef.child("images/image");
        ficheroRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Log.d("Almacenamiento", "Fichero bajado");
                        ImageView imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(BitmapFactory.decodeFile(path));
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.e("Almacenamiento", "ERROR: bajando fichero");
            }
        });
    }

    public void delete() {

        StorageReference referenciaImagen = mStorageRef.child("images/image");

       /*
        referenciaImagen.delete()
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Fichero borrado correctmente
                    }})
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        //Error al subir el fichero
                    }});
                    */

    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1234) {
                //subirFichero(data.getData(), "images/image");
                String nombreFichero = UUID.randomUUID().toString();
                subirFichero(data.getData(), "images/" + nombreFichero);
                try {
                    Bitmap bitmap = getBitmap(getContentResolver(), data.getData());
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void subirFichero(Uri fichero, String referencia) {
        final StorageReference ref = mStorageRef.child(referencia);
        UploadTask uploadTask = ref.putFile(fichero);
        Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull
                                          Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful())
                    throw task.getException();
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    Log.e("Almacenamiento", "URL: " + downloadUri.toString());
                    //registrarImagen("Subida por Móvil", downloadUri.toString());
                    registrarImagen("Subida por Móvil", downloadUri.toString());
                } else {
                    Log.e("Almacenamiento", "ERROR: subiendo fichero");
                }
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.delete) {
            delete();
        }

        return super.onOptionsItemSelected(item);
    }

    static void registrarImagen(String titulo, String url) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Imagen imagen = new Imagen(titulo, url);
        db.collection("images").document().set(imagen);
    }


}


