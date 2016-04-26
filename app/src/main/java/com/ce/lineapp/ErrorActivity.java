package com.ce.lineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class ErrorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_error, frameLayout);


        /**
         * Setting title and itemChecked
         */
        mDrawerList.setItemChecked(position, true);
    }
}
