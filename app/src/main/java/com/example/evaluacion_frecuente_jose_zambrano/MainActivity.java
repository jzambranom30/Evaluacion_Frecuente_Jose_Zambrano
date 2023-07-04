package com.example.evaluacion_frecuente_jose_zambrano;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.evaluacion_frecuente_jose_zambrano.Adaptadores.AdaptadorRevista;
import com.example.evaluacion_frecuente_jose_zambrano.Modelos.Revista;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lstOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url="https://revistas.uteq.edu.ec/ws/journals.php";


        //VISTA
        lstOp = (ListView) findViewById(R.id.lwRevistas);
        View header = getLayoutInflater().inflate(R.layout.lyheaderrevista, null);
        lstOp.addHeaderView(header);
        lstOp.setOnItemClickListener(this);

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray JSONlistarevista = null;
                try {
                    JSONlistarevista = new JSONArray(response);
                    ArrayList<Revista> lstrevista = Revista.JsonObjectsBuild(JSONlistarevista);
                    AdaptadorRevista adaptadorRevista = new AdaptadorRevista(MainActivity.this,lstrevista);
                    lstOp.setAdapter(adaptadorRevista);
                } catch (JSONException e) {

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"¡Oh ha ocurrido algo inesperado! Código de error: "
                        + error.networkResponse.statusCode,Toast.LENGTH_LONG).show();
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Revista revista = (Revista) parent.getItemAtPosition(position);
        Intent intent= new Intent(MainActivity.this, VistaRevistaActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("id",revista.getJournal_id());
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(this,"Ha seleccionado la Revista N° "
                +revista.getJournal_id().toString(),Toast.LENGTH_SHORT).show();
    }
}