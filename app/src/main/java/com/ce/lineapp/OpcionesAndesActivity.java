package com.ce.lineapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import Mundo.Estudiante;
import Mundo.RestClient;
import Mundo.Util;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OpcionesAndesActivity extends BaseActivity implements View.OnClickListener {


    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_opciones_andes, frameLayout);

        mDrawerList.setItemChecked(position, true);
        setTitle("Universidad de los Andes");

        Button btnAdmYReg = (Button) findViewById(R.id.btnAdmYReg);
        btnAdmYReg.setOnClickListener(this);
        Button btnApoyoFin = (Button) findViewById(R.id.btnApoyoFin);
        btnApoyoFin.setOnClickListener(this);
        Button btnCartera = (Button) findViewById(R.id.btnCartera);
        btnCartera.setOnClickListener(this);
        Button btnFacturacion = (Button) findViewById(R.id.btnFacturacion);
        btnFacturacion.setOnClickListener(this);
        Button btnMatriculas = (Button) findViewById(R.id.btnMatriculas);
        btnMatriculas.setOnClickListener(this);
        Button btnCajaMenor = (Button) findViewById(R.id.btnCajaMenor);
        btnCajaMenor.setOnClickListener(this);
        Button btnCajaIng = (Button) findViewById(R.id.btnCajaIng);
        btnCajaIng.setOnClickListener(this);

        mContext = this;



    }

    @Override
    public void onClick(View v)
    {
        Intent i;
        String id = Estudiante.darEstudiante().getCorreo();
        RestClient abc = RestClient.darInstancia();
        switch (v.getId())
        {
            case R.id.btnAdmYReg:
                i = new Intent(this, AdmRegUniandesActivity.class);
                startActivity(i);
                break;
            case R.id.btnApoyoFin:
                abc.tieneTurno(id,"Apoyo Financiero",mContext);
                break;
            case R.id.btnCartera:
                abc.tieneTurno(id,"Cartera",mContext);
                break;
            case R.id.btnFacturacion:
                abc.tieneTurno(id,"Facturación",mContext);
                break;
            case R.id.btnMatriculas:
                abc.tieneTurno(id,"Matriculas",mContext);
                break;
            case R.id.btnCajaMenor:
                abc.tieneTurno(id,"Caja Menor",mContext);
                break;
            case R.id.btnCajaIng:
                abc.tieneTurno(id,"Caja de Ingresos",mContext);
                break;
            default:
                Log.d("DEBUG",v.getId()+"");
                break;
        }

    }


}
