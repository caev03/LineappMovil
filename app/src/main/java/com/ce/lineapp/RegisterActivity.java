package com.ce.lineapp;

import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtNombre.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtNombre.setHintTextColor(getResources().getColor(R.color.white));
        EditText txtCorreo = (EditText) findViewById(R.id.txtCorreo);
        txtCorreo.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtCorreo.setHintTextColor(getResources().getColor(R.color.white));
        EditText txtContrasena = (EditText) findViewById(R.id.txtContrasena);
        txtContrasena.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtContrasena.setHintTextColor(getResources().getColor(R.color.white));
        EditText txtFechaNac = (EditText) findViewById(R.id.txtFechaNac);
        txtFechaNac.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtFechaNac.setHintTextColor(getResources().getColor(R.color.white));

    }
}
