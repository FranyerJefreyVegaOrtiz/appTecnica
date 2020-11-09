package com.example.apptecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    Button btnCEquipo, btnVerificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerificacion = (Button) findViewById(R.id.btnVerificacion);
        btnCEquipo = (Button) findViewById(R.id.btnCEquipo);
    }

    public void CrearEquipo (View view){
        Intent crearEquipo = new Intent(Inicio.this, Equipo.class);
        startActivity(crearEquipo);
    }
}