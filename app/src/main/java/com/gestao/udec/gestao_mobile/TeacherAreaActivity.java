package com.gestao.udec.gestao_mobile;

import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class TeacherAreaActivity extends AppCompatActivity implements View.OnClickListener{
    Button escanear;
    Button vincular;
    Button horario;
    Button registrar;
    Button clase;

    Button salas;
    SessionManager sesion;
    RequestQueue requestQueue;
    Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_area);

        SessionManager sesion = new SessionManager(TeacherAreaActivity.this);
        sesion.checkLogin();
        if (FirebaseInstanceId.getInstance().getToken()!= ""){
            comprobartoken(FirebaseInstanceId.getInstance().getToken());
        }
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);
        myToolbar.inflateMenu(R.menu.main);
        myToolbar.setTitle("Gestao");
        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Uri uri;
                Intent intent;
                switch (item.getItemId()) {
                    case R.id.miPaginaWeb:
                        uri = Uri.parse("http://gestao-web.co/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;

                    case R.id.miPerfil:
                        intent = new Intent(TeacherAreaActivity.this, PerfilActivity.class);
                        TeacherAreaActivity.this.startActivity(intent);
                        break;
                    case R.id.miUdecVirtual:
                        uri = Uri.parse("http://udecvirtual.unicundi.edu.co/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.miPlataformaUdec:
                        uri = Uri.parse("http://www.unicundi.edu.co/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.miCerrarSesion:
                        SessionManager sesion;
                        sesion = new SessionManager(TeacherAreaActivity.this);
                        sesion.logoutUser();
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });


        escanear = (Button) findViewById(R.id.btescanear);
        vincular = (Button) findViewById(R.id.btvincular);
        horario = (Button) findViewById(R.id.bthorario);
        registrar = (Button) findViewById(R.id.btreservar);
        clase = (Button) findViewById(R.id.btclase);

        salas = (Button) findViewById(R.id.btSalas);

        escanear.setOnClickListener(this);
        vincular.setOnClickListener(this);
        horario.setOnClickListener(this);
        registrar.setOnClickListener(this);
        clase.setOnClickListener(this);

        salas.setOnClickListener(this);

        String font_path = "fonts/Ubuntu-C.ttf";
        final Typeface TF = Typeface.createFromAsset(getAssets(),font_path);

        escanear.setTypeface(TF);
        vincular.setTypeface(TF);
        horario.setTypeface(TF);
        registrar.setTypeface(TF);
        clase.setTypeface(TF);
        salas.setTypeface(TF);

    }
    protected void comprobartoken(final String token){


        sesion = new SessionManager(TeacherAreaActivity.this);

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        String urlp = "http://gestao-web.co/token_insert.php";

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.POST, urlp, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    JSONArray jArray = jsonResponse.getJSONArray("response");
                    JSONObject id = jArray.getJSONObject(0);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parameters = new HashMap<String, String>();
                parameters.put("from_token", token);
                parameters.put("persona", sesion.getUserDetails().get("id"));

                return parameters;
            }
        };
        requestQueue.add(request);





    }


    @Override
    public void onClick(View v) {
        Intent intent;
        switch(v.getId()){
            case R.id.btescanear:
                intent = new Intent(TeacherAreaActivity.this, ARSimple.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
            case R.id.bthorario:
                intent = new Intent(TeacherAreaActivity.this, HorarioActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
            case R.id.btreservar:
                intent = new Intent(TeacherAreaActivity.this, ReservarActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
            case R.id.btvincular:
                intent = new Intent(TeacherAreaActivity.this, VincularActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
            case R.id.btclase:
                intent = new Intent(TeacherAreaActivity.this, ClaseActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;

            case R.id.btSalas:
                intent = new Intent(TeacherAreaActivity.this, SalasSeleccionActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
        }
    }
}