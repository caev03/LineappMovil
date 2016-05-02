package com.ce.lineapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TurnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_turno, frameLayout);

        mDrawerList.setItemChecked(position, true);
        setTitle(getString(R.string.app_name));

        String[] data = getIntent().getExtras().getString("Info").split(";");
        TextView txtTurno = (TextView) findViewById(R.id.txtTurno);
        txtTurno.setText(data[0]);
        TextView txtTurnoActual = (TextView) findViewById(R.id.txtTurnoActual);
        txtTurnoActual.setText(data[1]);
        TextView txtTmpEst = (TextView) findViewById(R.id.txtTmpEst);
        txtTmpEst.setText(data[2]+" min");
    }
}
