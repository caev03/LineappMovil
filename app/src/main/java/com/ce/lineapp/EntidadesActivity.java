package com.ce.lineapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class EntidadesActivity extends BaseActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_entidades, frameLayout);

        Button btnUniversidades = (Button) findViewById(R.id.btnUniversidades);
        btnUniversidades.setOnClickListener(this);
        Button btnBancos = (Button) findViewById(R.id.btnBancos);
        btnBancos.setOnClickListener(this);
        Button btnEntPub = (Button) findViewById(R.id.btnEntPub);
        btnEntPub.setOnClickListener(this);
        Button btnCenSal = (Button) findViewById(R.id.btnCenSal);
        btnCenSal.setOnClickListener(this);


        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle(getString(R.string.app_name));

        Intent i = new Intent(this, DifTurnoService.class);
        startService(i);
    }
    public void onClick(View v)
    {
        Intent i;
        switch (v.getId())
        {
            case R.id.btnUniversidades:
                i = new Intent(this, UniversidadesActivity.class);
                startActivity(i);
                break;
            default:
                i = new Intent(this, ErrorActivity.class);
                startActivity(i);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
