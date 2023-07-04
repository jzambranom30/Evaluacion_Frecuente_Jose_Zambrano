package com.example.evaluacion_frecuente_jose_zambrano;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.evaluacion_frecuente_jose_zambrano.Adaptadores.AdaptadorVistaRevista;
import com.example.evaluacion_frecuente_jose_zambrano.Modelos.VistaRevista;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class VistaRevistaActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstOp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_revista);

        Bundle bundle = getIntent().getExtras();

        String url="https://revistas.uteq.edu.ec/ws/issues.php?j_id=" + bundle.getString("id");

        //VISTA
        lstOp1 = (ListView) findViewById(R.id.lwVistaRevista);
        View header = getLayoutInflater().inflate(R.layout.lyheaderrevista, null);
        lstOp1.addHeaderView(header);
        lstOp1.setOnItemClickListener(this);

        RequestQueue queue1 = Volley.newRequestQueue(this);
        StringRequest stringRequest1 = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray JSONlistarevista = null;
                try {
                    JSONlistarevista = new JSONArray(response);
                    ArrayList<VistaRevista> lstrevista = VistaRevista.JsonObjectsBuild(JSONlistarevista);
                    AdaptadorVistaRevista adaptadorVistaRevista = new AdaptadorVistaRevista(VistaRevistaActivity.this,lstrevista);
                    lstOp1.setAdapter(adaptadorVistaRevista);
                } catch (JSONException e) {
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VistaRevistaActivity.this,"¡Oh ha ocurrido algo inesperado! Código de error: "
                        + error.networkResponse.statusCode,Toast.LENGTH_LONG).show();
            }
        });
        queue1.add(stringRequest1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        VistaRevista vistaRevista = (VistaRevista) parent.getItemAtPosition(position);
        Toast.makeText(this,"Ha seleccionado la Revista Volumen "
                +vistaRevista.getVolume().toString() + " Número" + vistaRevista.getNumber().toString()
                ,Toast.LENGTH_SHORT).show();
    }
}