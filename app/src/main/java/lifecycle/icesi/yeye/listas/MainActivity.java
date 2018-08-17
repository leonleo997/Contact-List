package lifecycle.icesi.yeye.listas;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombre;
    private EditText et_codigo;
    private Button btn_agregar;
    private ListView lista_estudiantes;
    private YesidAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA}, 11);

        et_nombre = findViewById(R.id.et_nombre);
        et_codigo = findViewById(R.id.et_codigo);
        btn_agregar = findViewById(R.id.btn_agregar);
        lista_estudiantes = findViewById(R.id.lista_estudiantes);


        adaptador = new YesidAdapter(this);
        lista_estudiantes.setAdapter(adaptador);



        adaptador.agregarEstudiante(new Estudiante("Cristian", "3147153076"));
        adaptador.agregarEstudiante(new Estudiante("Chimbi", "3229105674"));


        lista_estudiantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Estudiante estudiante= (Estudiante) adaptador.getItem(position);
                Toast.makeText(MainActivity.this, estudiante.getTelefono(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+estudiante.getTelefono())));

            }
        });

        btn_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = et_nombre.getText().toString();
                String codigo = et_codigo.getText().toString();
                Estudiante estud = new Estudiante(nombre, codigo);
                adaptador.agregarEstudiante(estud);

                et_nombre.setText("");
                et_codigo.setText("");
            }
        });


    }
}

