package es.upv.comunicacionactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button verificar;
    private EditText entrada;
    private TextView textoRes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificar = findViewById(R.id.button);
        entrada = findViewById(R.id.editText);

        textoRes=findViewById(R.id.textView2);

        verificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                lanzarVer(null);
            }
        });
    }

    public void lanzarVer (View view){
        /*
        Intent i = new Intent(this, SegActivity.class);
        startActivity(i);*/

        Intent intent = new Intent(this, SegActivity.class);
        intent.putExtra("usuario", entrada.getText().toString());
        //intent.putExtra("edad", 27);
        //startActivity(intent);
        startActivityForResult(intent, 1234);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //texto2.findViewById(R.id.textView2);
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1234 && resultCode == RESULT_OK) {
            String res = data.getExtras().getString("resultado");
            System.out.println(res);
            textoRes.setText(res);
        }
    }
}
