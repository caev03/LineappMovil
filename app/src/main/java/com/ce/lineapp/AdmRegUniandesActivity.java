package com.ce.lineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmRegUniandesActivity extends BaseActivity implements View.OnClickListener {

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
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {

    }
}
