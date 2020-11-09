package com.example.apptecnica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Verificacion extends AppCompatActivity {

    Button btnBuscarCaja;
    TextView txtMegger, txtTierra;
    EditText etBuscarCaja;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacion);

        btnBuscarCaja = (Button)findViewById(R.id.btnBuscarCaja);
        txtMegger = (TextView)findViewById(R.id.txtMegger);
        txtTierra = (TextView)findViewById(R.id.txtTierra);
        etBuscarCaja = (EditText)findViewById(R.id.etBuscarCaja);

        btnBuscarCaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCaja("https://apptecnica.000webhostapp.com/crud/BuscarCaja.php?codigoCaja="+etBuscarCaja.getText()+"");
            }
        });
    }

    public void buscarCaja(String URL){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        etBuscarCaja.setText(jsonObject.getString("codigoCaja"));
                        txtMegger.setText(jsonObject.getString("meggerCaja"));
                        txtTierra.setText(jsonObject.getString("tierraCaja"));
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}