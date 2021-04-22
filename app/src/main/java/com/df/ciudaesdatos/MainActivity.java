package com.df.ciudaesdatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvListar;
    private EditText txtNombre;
    private EditText txtId;
    private EditText txtPoblacion;
    private EditText txtLatitud;
    private EditText txtLongitud;
    private Button btnIngresar;
    private Button btnListar;
    private ArrayAdapter<String> adapter;
    MyDbCiudades db = new MyDbCiudades(this);
    ArrayList<Ciudad> ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvListar = findViewById(R.id.lvListar);
        txtNombre = findViewById(R.id.txtNombre);
        txtId = findViewById(R.id.txtId);
        txtPoblacion = findViewById(R.id.txtPoblacion);
        txtLatitud = findViewById(R.id.txtLatitud);
        txtLongitud = findViewById(R.id.txtLongitud);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnListar = findViewById(R.id.btnListar);

        btnIngresar.setOnClickListener(this);
        btnListar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnIngresar) {
            InsertarCiudad();
        }
        if (v.getId() == R.id.btnListar) {
            ciudades = db.selecinarCiudad(db.getWritableDatabase());
            adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ciudades);
            lvListar.setAdapter(adapter);
        }
    }

    private void limpiar() {
        txtId.getText().clear();
        txtLatitud.getText().clear();
        txtLongitud.getText().clear();
        txtNombre.getText().clear();
        txtPoblacion.getText().clear();
    }

    private void InsertarCiudad() {
        int id = Integer.parseInt(txtId.getText().toString());
        String nombre = txtNombre.getText().toString();
        int poblacion = Integer.parseInt(txtPoblacion.getText().toString());
        double latitud = Double.parseDouble(txtLatitud.getText().toString());
        double longitud = Double.parseDouble(txtLongitud.getText().toString());

        Ciudad ciudad = new Ciudad(id, nombre, poblacion, latitud, longitud);
        db.insertarCiudad(db.getWritableDatabase(), ciudad);
        limpiar();
    }
}