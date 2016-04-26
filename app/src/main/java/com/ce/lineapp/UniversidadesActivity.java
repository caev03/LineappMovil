package com.ce.lineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UniversidadesActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_universidades, frameLayout);

        Button btnAndes = (Button) findViewById(R.id.btnAndes);
        btnAndes.setOnClickListener(this);
        Button btnJaveriana = (Button) findViewById(R.id.btnJaveriana);
        btnJaveriana.setOnClickListener(this);
        Button btnTadeo = (Button) findViewById(R.id.btnTadeo);
        btnTadeo.setOnClickListener(this);
        Button btnExternado = (Button) findViewById(R.id.btnExternado);
        btnExternado.setOnClickListener(this);

        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
        setTitle("Universidades");
    }

    public void onClick(View v)
    {
        Intent i;
        switch (v.getId())
        {
            case R.id.btnAndes:
                i = new Intent(this, OpcionesAndesActivity.class);
                startActivity(i);
                break;
            default:
                i = new Intent(this, ErrorActivity.class);
                startActivity(i);
                break;
        }
    }
}
