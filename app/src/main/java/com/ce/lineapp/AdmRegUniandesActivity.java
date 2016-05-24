package com.ce.lineapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class AdmRegUniandesActivity extends BaseActivity implements View.OnClickListener {

    static AdmRegUniandesActivity instancia;
    Context mContext;

    public static AdmRegUniandesActivity darInstancia()
    {
        if(instancia==null)
        {
            instancia = new AdmRegUniandesActivity();
        }
        return instancia;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_adm_reg_uniandes, frameLayout);
        mDrawerList.setItemChecked(position, true);
        setTitle("Admisiones y Registro");

        Button btnEntrCertDip = (Button) findViewById(R.id.btnEntrCertDip);
        btnEntrCertDip.setOnClickListener(this);
        Button btnEntCar = (Button) findViewById(R.id.btnEntCar);
        btnEntCar.setOnClickListener(this);
        Button btnInfGrl = (Button) findViewById(R.id.btnInfGrl);
        btnInfGrl.setOnClickListener(this);
        Button btnSolRecDoc = (Button) findViewById(R.id.btnSolRecDoc);
        btnSolRecDoc.setOnClickListener(this);
        Button btnRadCorr = (Button) findViewById(R.id.btnRadCorr);
        btnRadCorr.setOnClickListener(this);
        mContext = this;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v)
    {
        String id = Estudiante.darEstudiante().getCorreo();
        RestClient abc = RestClient.darInstancia();
        switch (v.getId())
        {
            case R.id.btnEntrCertDip:
                abc.tieneTurno(id, "Entrega de Certificados y Diplomas",mContext);
                break;
            case R.id.btnEntCar:
                abc.tieneTurno(id, "Entrega de Carnets",mContext);
                break;
            case R.id.btnInfGrl:
                abc.tieneTurno(id, "Información General Tramites Registro",mContext);
                break;
            case R.id.btnSolRecDoc:
                abc.tieneTurno(id, "Solicitudes y Recepción de Documentos",mContext);
                break;
            case R.id.btnRadCorr:
                abc.tieneTurno(id, "Radicación Correspondencia",mContext);
                break;
        }
    }




}
