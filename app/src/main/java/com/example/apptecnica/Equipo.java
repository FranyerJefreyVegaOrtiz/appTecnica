package com.example.apptecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Equipo extends AppCompatActivity {


    //dbEquipo.ConexionSQliteHelper con;
    Button btnRegistrar;
    EditText etNombreEquipo, etCodigo, etMegger, etTierra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipo);

       //ll con = new dbEquipo.ConexionSQliteHelper(this, "bd_Equipo", null, 1);
        etNombreEquipo = (EditText)findViewById(R.id.etNombreEquipo);
        etCodigo = (EditText)findViewById(R.id.etCodigo);
        etMegger = (EditText)findViewById(R.id.etMegger);
        etTierra = (EditText)findViewById(R.id.etTierra);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);
    }


    public void Registrar (View view){
        ejecutarServicio("http://192.168.56.1/apptecnica/equipo");
    }

    private void ejecutarServicio (String URL){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "OPERACION EXITOSA", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String, String>();
                parametros.put("Nombre", etNombreEquipo.getText().toString());
                parametros.put("Codigo", etCodigo.getText().toString());
                parametros.put("Megger", etMegger.getText().toString());
                parametros.put("Tierra", etTierra.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }




    /*public void CrearEquipo (View view){
        Intent crearEquipo = new Intent(MainActivity.this, Equipo.class);
        startActivity(crearEquipo);
    }*/

   /* public void onClick(View view){
        RegistrarEquipo();
        Toast.makeText(getApplicationContext(), "El Equipo Se Ha Registrado", Toast.LENGTH_SHORT).show();


    }

    private void RegistrarEquipo(){
        dbEquipo.ConexionSQliteHelper conn = new dbEquipo.ConexionSQliteHelper(this, "bd_Equipo", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(dbEquipo.NombreEquipo, NombreEquipo.getText().toString());
        values.put(dbEquipo.Codigo, Codigo.getText().toString());
        values.put(dbEquipo.Meger, Meger.getText().toString());
        values.put(dbEquipo.Tierra, Tierra.getText().toString());

        long idResultado = db.insert(dbEquipo.Tabla_Equipo,dbEquipo.idEquipo,values);
        Toast.makeText(getApplicationContext(), "Id Registrado" +idResultado, Toast.LENGTH_SHORT).show();
        db.close();
    }*/
}