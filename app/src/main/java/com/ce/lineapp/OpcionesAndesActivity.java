package com.ce.lineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class OpcionesAndesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_opciones_andes, frameLayout);

        mDrawerList.setItemChecked(position, true);
        setTitle("Universidad de los Andes");

    }
}
