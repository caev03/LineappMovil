package com.ce.lineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OpcionesAndesActivity extends BaseActivity implements View.OnClickListener {



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




    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnAdmYReg:
                Intent i = new Intent(this, AdmRegUniandesActivity.class);
                startActivity(i);
                break;
            default:
                turnGiver(v.getId());
        }
    }

    public void turnGiver(int id)
    {
        finish();
    }
}
