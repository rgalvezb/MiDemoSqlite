package com.example.administrator.midemosqlite;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.midemosqlite.dao.AlumnoDAO;
import com.example.administrator.midemosqlite.dao.DataBaseHelper;
import com.example.administrator.midemosqlite.entities.Alumno;


public class MainActivity extends ActionBarActivity {

    private EditText etBuscador;
    private Button btBuscador;
    private TextView tvNombre, tvApellido, tvEdad, tvDNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etBuscador = (EditText) findViewById(R.id.etBuscador);
        btBuscador = (Button) findViewById(R.id.btBuscador);
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvApellido = (TextView) findViewById(R.id.tvApellido);
        tvEdad = (TextView) findViewById(R.id.tvEdad);
        tvDNI = (TextView) findViewById(R.id.tvDNI);

        btBuscador.setOnClickListener(btBuscadorOnClickListener);

        try{
            DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
        }catch (Exception ex){
            Toast.makeText(MainActivity.this, "No se pudo copiar la BD",Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener btBuscadorOnClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int IdAlumno = Integer.parseInt(etBuscador.getText().toString());
            Alumno alumno = new AlumnoDAO().getAlumnoById(IdAlumno);
            if(alumno!=null) {
                tvNombre.setText(alumno.getNombre());
                tvApellido.setText(alumno.getApellido());
                tvEdad.setText(String.valueOf(alumno.getEdad()));
                tvDNI.setText(alumno.getDNI());
            }else{
                Toast.makeText(MainActivity.this,"No existe el Alumno",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
