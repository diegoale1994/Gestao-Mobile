package com.gestao.udec.gestao_mobile;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UserAreaActivity extends AppCompatActivity implements View.OnClickListener {

    Button escanear;
    Button vincular;
    Button horario;
    Button profesores;
    Button perfil;
    Toolbar myToolbar;

    SessionManager sesion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);




        sesion = new SessionManager(UserAreaActivity.this);
        sesion.checkLogin();

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
                        uri = Uri.parse("http://gestao.audiplantas.com/");
                        intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        break;
                    case R.id.miContacto:

                        break;
                    case R.id.miPerfil:
                        intent = new Intent(UserAreaActivity.this, PerfilActivity.class);
                        UserAreaActivity.this.startActivity(intent);
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
                        sesion = new SessionManager(UserAreaActivity.this);
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
        profesores = (Button) findViewById(R.id.btprofesores);
        perfil = (Button) findViewById(R.id.btPerfil);


        String font_path = "fonts/Ubuntu-C.ttf";
        final Typeface TF = Typeface.createFromAsset(getAssets(),font_path);

        escanear.setTypeface(TF);
        vincular.setTypeface(TF);
        horario.setTypeface(TF);
        profesores.setTypeface(TF);
        perfil.setTypeface(TF);




        escanear.setOnClickListener(this);
        vincular.setOnClickListener(this);
        horario.setOnClickListener(this);
        profesores.setOnClickListener(this);
        perfil.setOnClickListener(this);





    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btescanear:
                intent = new Intent(UserAreaActivity.this, ARSimple.class);
                UserAreaActivity.this.startActivity(intent);
                break;
            case R.id.bthorario:
                intent = new Intent(UserAreaActivity.this, HorarioActivity.class);
                UserAreaActivity.this.startActivity(intent);
                break;
            case R.id.btvincular:
                intent = new Intent(UserAreaActivity.this, VincularActivity.class);
                UserAreaActivity.this.startActivity(intent);
                break;
            case R.id.btprofesores:
                intent = new Intent(UserAreaActivity.this, ProfesoresActivity.class);
                UserAreaActivity.this.startActivity(intent);
                break;
            case R.id.btPerfil:
                intent = new Intent(UserAreaActivity.this, PerfilActivity.class);
                UserAreaActivity.this.startActivity(intent);
                break;

        }
    }



}