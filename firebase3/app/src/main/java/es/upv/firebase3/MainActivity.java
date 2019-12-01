package es.upv.firebase3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = findViewById(R.id.data);

/*
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coleccion").document("documento").get()
                .addOnCompleteListener(
                        new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task){
                                if (task.isSuccessful()) {
                                    //String dato1 = task.getResult().getString("dato_1");
                                    String dato1 ="";
                                    double dato2 = task.getResult().getDouble("dato_2");
                                    Log.d("Firestore", "dato 1:" + dato1 + "dato 2:" + dato2);
                                } else {
                                    Log.e("Firestore", "Error al leer", task.getException());
                                } }
                        });

*/

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("coleccion").document("documento").addSnapshotListener(
        new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot snapshot,
        @Nullable FirebaseFirestoreException e){
        if (e != null) {
        Log.e("Firebase", "Error al leer", e);
        } else if (snapshot == null || !snapshot.exists()) { Log.e("Firebase", "Error: documento no encontrado ");
        } else {
        Log.e("Firestore", "datos:" + snapshot.getData());
        } }
        });

        data.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("data", "onClick: ");

                /*
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                Map<String, Object> datos = new HashMap<>(); datos.put("dato_1", "hola mundo"); datos.put("dato_2", 35);
                db.collection("coleccion").document("documento").set(datos);


                FirebaseFirestore db = FirebaseFirestore.getInstance(); Map<String, Object> datos = new HashMap<>();
                datos.put("dato_2", 48); datos.put("numbero", 3.14159265); datos.put("fecha", new Date()); datos.put("lista", Arrays.asList(1, 2, 3)); datos.put("null", null);
                Map<String, Object> datosAnidados = new HashMap<>(); datosAnidados.put("a", 5);
                datosAnidados.put("b", true); datos.put("objectExample", datosAnidados);
                db.collection("coleccion").document("documento").set(datos);
                */



            }
        });
    }
}
