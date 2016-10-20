package com.gestao.udec.gestao_mobile;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class TeacherAreaActivity extends AppCompatActivity implements View.OnClickListener{
    Button escanear;
    Button vincular;
    Button horario;
    Button registrar;
    Button clase;
    Button perfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_area);

        SessionManager sesion = new SessionManager(TeacherAreaActivity.this);
        sesion.checkLogin();
        escanear = (Button) findViewById(R.id.btescanear);
        vincular = (Button) findViewById(R.id.btvincular);
        horario = (Button) findViewById(R.id.bthorario);
        registrar = (Button) findViewById(R.id.btreservar);
        clase = (Button) findViewById(R.id.btclase);
        perfil = (Button) findViewById(R.id.btPerfil);

        escanear.setOnClickListener(this);
        vincular.setOnClickListener(this);
        horario.setOnClickListener(this);
        registrar.setOnClickListener(this);
        clase.setOnClickListener(this);
        perfil.setOnClickListener(this);

        String font_path = "fonts/Ubuntu-C.ttf";
        final Typeface TF = Typeface.createFromAsset(getAssets(),font_path);

        escanear.setTypeface(TF);
        vincular.setTypeface(TF);
        horario.setTypeface(TF);
        registrar.setTypeface(TF);
        clase.setTypeface(TF);
        perfil.setTypeface(TF);

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
            case R.id.btPerfil:
                intent = new Intent(TeacherAreaActivity.this, PerfilActivity.class);
                TeacherAreaActivity.this.startActivity(intent);
                break;
        }
    }
}