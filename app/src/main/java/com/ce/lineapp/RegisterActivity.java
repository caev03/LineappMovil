package com.ce.lineapp;

import android.app.DatePickerDialog;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Calendar myCalendar = Calendar.getInstance();

    EditText txtFechaNac;

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
        txtFechaNac = (EditText) findViewById(R.id.txtFechaNac);
        txtFechaNac.getBackground().mutate().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        txtFechaNac.setHintTextColor(getResources().getColor(R.color.white));

        txtFechaNac.setOnClickListener(this);

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        DatePickerDialog dialog = new DatePickerDialog(this, R.style.DatePickerDialog, this, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        txtFechaNac.setText(sdf.format(myCalendar.getTime()));
    }

    public void registrarUsuario(View v)
    {
        finish();
    }
}
