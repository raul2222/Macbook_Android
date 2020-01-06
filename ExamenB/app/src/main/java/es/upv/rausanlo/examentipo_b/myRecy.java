package es.upv.rausanlo.examentipo_b;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class myRecy extends AppCompatActivity {

    private RecyclerView reyclerViewUser;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mirecicler);

        reyclerViewUser = (RecyclerView) findViewById(R.id.reyclerViewUser);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        reyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter with the list to show
        mAdapter = new UserAdapter(getData());
        reyclerViewUser.setAdapter(mAdapter);

    }

    // create fake data for this example
    public List<UserModel> getData() {

        List<UserModel> userModels = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();
        /*
        userModels.add(new UserModel("Gustavo"));
        userModels.add(new UserModel("Daniel"));
        userModels.add(new UserModel("Cecilia"));
        userModels.add(new UserModel("Diego"));
        userModels.add(new UserModel("Carlos"));
        userModels.add(new UserModel("Juan"));

        for(int i = 1; i < 10; i++) {
            userModels.add(new UserModel("Name " + i));
        }

         */

        try {
            List<Integer> lista = new ArrayList<>();

            //el set no permite elementos duplicados

            Set<Integer> unidades = new HashSet<>();

            for (int i = 0; i <= 9; i++) {
                lista.add(MainActivity.NUMERO * i);
                unidades.add((MainActivity.NUMERO * i) % 10);

            }
            unidades.add(9);

            Iterator iterator = unidades.iterator();

            while(iterator.hasNext()){
                lista2.add((Integer) iterator.next());
            }

            for (int i = 0 ; i<=lista2.size(); i++){
                Integer ele = lista2.get(i);
                Boolean esPar = false;
                String esP = " + IMPAR";

                if (ele % 2 == 0) {
                    esPar = true;
                    esP = " + PAR";
                }

                userModels.add(new UserModel(ele + esP));
            }

            Log.e("lista", String.valueOf(lista));
            Log.e("Unidades set: ", String.valueOf(unidades));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error ", e.toString());
        }

        return userModels;
    }
}
