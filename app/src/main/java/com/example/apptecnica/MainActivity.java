package com.example.apptecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCEquipo, btnVerificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVerificacion = (Button) findViewById(R.id.btnVerificacion);
        btnCEquipo = (Button) findViewById(R.id.btnCEquipo);
    }

    public void CrearEquipo (View view){
        Intent crearEquipo = new Intent(MainActivity.this, Equipo.class);
        startActivity(crearEquipo);
    }
}