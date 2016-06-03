package com.ce.lineapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import Mundo.Estudiante;
import Mundo.RestClient;

public class TurnoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLayoutInflater().inflate(R.layout.activity_turno, frameLayout);

        mDrawerList.setItemChecked(position, true);
        setTitle(getString(R.string.app_name));

        TextView txtTurno = (TextView) findViewById(R.id.txtTurno);
        txtTurno.setText(Estudiante.darEstudiante().getTurno());
        String turnoActual = getIntent().getStringExtra("turnoActual");
        TextView txtTurnoActual = (TextView) findViewById(R.id.txtTurnoActual);
        txtTurnoActual.setText(turnoActual);
        int turno = Integer.parseInt(Estudiante.darEstudiante().getTurno().split("-")[1]);
        int intTurnoActual = Integer.parseInt(turnoActual.split("-")[1]);
        TextView txtTmpEst = (TextView) findViewById(R.id.txtTmpEst);
        txtTmpEst.setText(((turno-intTurnoActual)*5)+" min");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mi_turno, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_cancel:
                RestClient.darInstancia().cancelTurn();
                Intent i = new Intent(this,EntidadesActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
