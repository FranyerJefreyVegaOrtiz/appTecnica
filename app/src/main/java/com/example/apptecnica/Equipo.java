package com.example.apptecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexion();
            }
        });
    }

    private void Conexion(){
        String nombre = etNombreEquipo.getText().toString().trim();
        String codigo = etCodigo.getText().toString().trim();
        String megger = etMegger.getText().toString().trim();
        String tierra = etTierra.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        if (nombre.isEmpty()){
            etNombreEquipo.setError("Complete los campos");
        }
        else if (codigo.isEmpty()){
            etCodigo.setError("Complete los campos");
        }
        else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "https://apptecnica.000webhostapp.com/crud/Conexion.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Datos almacenados")) {
                        Toast.makeText(Equipo.this, "Datos Almacenados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        Toast.makeText(Equipo.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Equipo.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params =new HashMap<String, String>();
                    params.put("nombre", nombre);
                    params.put("codigo", codigo);
                    params.put("megger", megger);
                    params.put("tierra", tierra);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Equipo.this);
            requestQueue.add(request);
        }
    }
}