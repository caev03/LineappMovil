package com.ce.lineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UniversidadesActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Adding our layout to parent class frame layout.
         */
        getLayoutInflater().inflate(R.layout.activity_universidades, frameLayout);



        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
    }
}
