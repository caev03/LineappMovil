package com.ce.lineapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import java.io.IOException;

import Mundo.Estudiante;
import Mundo.RestClient;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DifTurnoService extends IntentService {

    public DifTurnoService() {
        super("DifTurnoService");
    }


    @Override
    protected void onHandleIntent(Intent intent)
    {
        try
        {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(RestClient.BaseURL+"turnoActualDep/"+ Estudiante.darEstudiante().getCorreo())
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override public void onResponse(Call call, Response response) throws IOException
                {
                    String respuesta = response.body().string();
                    respuesta = respuesta.substring(1,respuesta.length()-1);
                    response.body().close();
                    if(!respuesta.contains("start"))
                    {
                        Log.d("DEBUG",respuesta);
                        int turno = Integer.parseInt(Estudiante.darEstudiante().getTurno().split("-")[1]);
                        int intTurnoActual = Integer.parseInt(respuesta.split("-")[1]);
                        if(turno-intTurnoActual<3)
                        {
                            showNotification("Lineapp","Quedan menos de 10 min para tu turno",respuesta);
                        }
                    }
                }
            });
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }


    }

    public void showNotification(String title, String message,String turnoActual) {
        Intent notifyIntent = new Intent(this, TurnoActivity.class);
        notifyIntent.putExtra("turnoActual",turnoActual);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivities(this, 0,
                new Intent[] { notifyIntent }, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build();
        notification.defaults |= Notification.DEFAULT_SOUND;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
}
