package com.ce.lineapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import Mundo.Estudiante;
import Mundo.RestClient;

public class LoginActivity extends AppCompatActivity {

    EditText txtLogin;
    EditText txtPassword;

    private static LoginActivity instancia;
    Context context;

    public static LoginActivity darInstancia()
    {
        if(instancia==null)
        {
            instancia = new LoginActivity();
        }
        return instancia;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_login);
        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtLogin.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtLogin.setHintTextColor(getResources().getColor(R.color.white));
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtPassword.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtPassword.setHintTextColor(getResources().getColor(R.color.white));
        darInstancia().context = getApplicationContext();
    }

    public void registrarUsuario(View v)
    {
        Intent i = new Intent(this,RegisterActivity.class);
        startActivity(i);
    }

    public void ingresarUsuario(View v)
    {
        Estudiante.darEstudiante().setCorreo(txtLogin.getText().toString());
        RestClient.darInstancia().loginUser(txtLogin.getText().toString(),txtPassword.getText().toString(),LoginActivity.this, this);
    }

    public void terminoLogin(String string, Context context, LoginActivity loginActivity)
    {
        if(!string.contains("redencia"))
        {
            Intent i = new Intent(loginActivity,EntidadesActivity.class);
            this.context = context;
            Estudiante.darEstudiante().setId(string.replaceAll("\"",""));
            loginActivity.startActivity(i);
        }
        else
        {
            final Context iContext = context;
            ((Activity) iContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(iContext, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
