package es.upv.rausanlo.practicaprocesado;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static long FECHA[]={1288122023000L, 1288122023000L, 1288122223000L, 1288122223000L,
            1288122223000L, 1288122623000L, 1288122623000L, 1288122623000L };
    static String HABITACION[] = {"cocina", "baño", "cocina", "baño",
            "comedor", "cocina","baño", "comedor"};
    static int TEMPERATURA[] = {18, 19, 17, 19,
            17, 19, 22, 22};
    static long FECHA_ACTUAL = 1288122723000L;

    List<Temp> temperaturas = new ArrayList<Temp>();
    Set<String> habitaciones = new HashSet<String>();

    private RecyclerView reyclerViewUser;
    private RecyclerView.Adapter mAdapter;

    private double tempMediaCocina = 0;
    private double tempMediaBano = 0;
    private double tempMediaComedor = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        for (int i = 0; i < FECHA.length; i++){
            temperaturas.add(new Temp(FECHA[i],HABITACION[i],TEMPERATURA[i]));
        }

        for (int i = 0; i < FECHA.length; i++){
            habitaciones.add(temperaturas.get(i).getHABITACION());
        }
        Log.e("SET", String.valueOf(habitaciones));


        int cuantasCocina = 0;


        for (int i = 0; i < temperaturas.size() ;i++){
            if(temperaturas.get(i).getHABITACION() == "cocina"){
                tempMediaCocina = tempMediaCocina + temperaturas.get(i).getTEMPERATURA();
                cuantasCocina++;
            }
        }
        tempMediaCocina = tempMediaCocina / cuantasCocina;
        Log.e("TempMediaCocina", String.valueOf(tempMediaCocina));


        int cuantasBano = 0;
        for (int i = 0; i < temperaturas.size() ;i++){
            if(temperaturas.get(i).getHABITACION() == "baño"){
                tempMediaBano = tempMediaBano + temperaturas.get(i).getTEMPERATURA();
                cuantasBano++;
            }
        }
        tempMediaBano = tempMediaBano / cuantasCocina;
        Log.e("TempMediaBano", String.valueOf(tempMediaBano));


        int cuantasComedor = 0;
        for (int i = 0; i < temperaturas.size() ;i++){
            if(temperaturas.get(i).getHABITACION() == "comedor"){
                tempMediaComedor = tempMediaComedor + temperaturas.get(i).getTEMPERATURA();
                cuantasComedor++;
            }
        }
        tempMediaComedor = tempMediaComedor / cuantasComedor;
        Log.e("TempMediaComedor", String.valueOf(tempMediaComedor));


        reyclerViewUser = (RecyclerView) findViewById(R.id.reyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        reyclerViewUser.setHasFixedSize(true);

        // use a linear layout manager
        reyclerViewUser.setLayoutManager(new LinearLayoutManager(this));

        // specify an adapter with the list to show
        mAdapter = new UserAdapter(getData());
        reyclerViewUser.setAdapter(mAdapter);


        /*
        double temCocina = 0;
        long tiempoCocinaInicial = 0;
        double tiempoCocina = 0;




        for (int i = 0; i < temperaturas.size(); i++){
            long tiempoCocinaFinal = 0;
            if(temperaturas.get(i).getHABITACION() == "cocina"){
                temCocina = temperaturas.get(i).getTEMPERATURA();
                tiempoCocinaInicial = temperaturas.get(i).getFECHA();
                for (int x = i+1; x < temperaturas.size(); x++){
                    if(temperaturas.get(x).getHABITACION() == "cocina") {
                        tiempoCocinaFinal = temperaturas.get(x).getFECHA();

                        //i = x;
                        break;
                    }
                }
                if (tiempoCocinaFinal == 0) {
                    tiempoCocinaFinal = FECHA_ACTUAL;
                }
                tiempoCocina = tiempoCocina + (((tiempoCocinaFinal - tiempoCocinaInicial)/1000)/60);
                temCocina = temCocina * (((tiempoCocinaFinal - tiempoCocinaInicial)/1000)/60);

            }
        }
        double tempMediaCocina = temCocina / tiempoCocina;
        */


    }



    // create fake data for this example
    public List<UserModel> getData() {

        List<UserModel> userModels = new ArrayList<>();
        userModels.add(new UserModel("Cocina" + " " + tempMediaCocina));
        userModels.add(new UserModel("Baño"+ " " + tempMediaBano));
        userModels.add(new UserModel("comedor"+ " " + tempMediaComedor));

        return userModels;
    }



}
