package es.upv.procesadob3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Adaptador adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Query query = FirebaseFirestore.getInstance()
                .collection("temperatura")
                //.orderBy("campo", Query.Direction.DESCENDING)
                .limit(50);
        FirestoreRecyclerOptions<Clase> opciones = new FirestoreRecyclerOptions
                .Builder<Clase>().setQuery(query, Clase.class).build();
        adaptador = new Adaptador(this, opciones);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public static void guardarEnFirestore(String documento, int dato1, String dato2) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> datos = new HashMap<>();
        datos.put("dato1", dato1);
        datos.put("dato2", dato2);
        db.collection("coleccion").document(documento).set(datos);
    }

    @Override public void onStart() {
        super.onStart();
        adaptador.startListening();
    }
    @Override public void onStop() {
        super.onStop();
        adaptador.stopListening();
    }


}
