package com.ce.lineapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtLogin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtLogin.setHintTextColor(getResources().getColor(R.color.white));
        EditText txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtPassword.setHintTextColor(getResources().getColor(R.color.white));
    }

    public void registrarUsuario(View v)
    {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }
}
