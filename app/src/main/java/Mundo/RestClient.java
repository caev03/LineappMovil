package Mundo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.ce.lineapp.DifTurnoService;
import com.ce.lineapp.LoginActivity;
import com.ce.lineapp.TurnoActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cami on 5/1/16.
 */
public class RestClient
{
    public final static String CREAR_USER = "crear_usuario";
    public static final String LOG_IN = "log_in";
    private static RestClient instancia;
    private boolean terminoLogin;
    private String respuestaLogin;
    public final static String BaseURL = "http://157.253.208.64:3000/";
    Map<String, String> dependencias = new HashMap<String, String>();

    public boolean isTerminoLogin()
    {
        return terminoLogin;
    }

    public String getRespuestaLogin()
    {
        return respuestaLogin;
    }

    public static RestClient darInstancia()
    {
        if (instancia == null)
        {
            instancia = new RestClient();
        }
        return instancia;
    }

    public RestClient()
    {
        dependencias.put("Apoyo Financiero", "AF");
        dependencias.put("Cartera", "CR");
        dependencias.put("Facturación", "FC");
        dependencias.put("Matriculas", "MT");
        dependencias.put("Caja Menor", "CM");
        dependencias.put("Caja de Ingresos", "CI");
        dependencias.put("Entrega de Certificados y Diplomas", "ECD");
        dependencias.put("Entrega de Carnets", "EC");
        dependencias.put("Información General Tramites Registro", "IG");
        dependencias.put("Solicitudes y Recepción de Documentos", "SRD");
        dependencias.put("Radicación Correspondencia", "RC");
    }


    public void createUser(String nombre, String correo, String contraseña, boolean preferencial) throws Exception
    {
        String[] abc = {CREAR_USER, correo, nombre, contraseña, "" + preferencial};
        new RetrieveFeedTask().execute(abc);
    }

    public void loginUser(String correo, String contrasena, final Context context, final LoginActivity loginActivity)
    {
        String[] abc = {LOG_IN, correo, contrasena};
        //new RetrieveFeedTask().execute(abc);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BaseURL + "login/" + abc[1] + "-" + abc[2])
                .build();

        client.newCall(request).enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Looper.prepare();
                String abc = response.body().string();
                Log.d("ABC", abc);
                LoginActivity la = LoginActivity.darInstancia();
                la.terminoLogin(abc, context, loginActivity);
            }
        });

    }

    public void tieneTurno(final String idUsuario, final String departamento, final Context mContext)
    {
        try
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BaseURL + "turnos/" + idUsuario)
                    .get()
                    .build();
            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    String resp = response.body().string();
                    response.body().close();
                    Log.d("DEBUG-TT", resp);
                    int cantTurnos = Integer.parseInt(resp);
                    if (cantTurnos != 0)
                    {
                        Looper.prepare();
                        ((Activity) mContext).runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(mContext, "Ya tiene un turno asociado", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                    else
                    {
                        pedirTurno(idUsuario, departamento, mContext);
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getTurno(final String idUsuario, final Context mContext)
    {
        try
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BaseURL + "turnoActual/" + idUsuario)
                    .get()
                    .build();
            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    String respuesta = response.body().string();
                    response.body().close();
                    if (respuesta.equals("[]"))
                    {
                        Intent i = new Intent(mContext, DifTurnoService.class);
                        mContext.startService(i);
                    }
                    else
                    {
                        int abc = respuesta.lastIndexOf("numero");
                        int inicial = abc+9;
                        int finall = respuesta.lastIndexOf("depart")-3;
                        respuesta = respuesta.substring(inicial,finall);
                        Estudiante.darEstudiante().setTurno(respuesta);
                        Intent i = new Intent(mContext, DifTurnoService.class);
                        mContext.startService(i);

                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void pedirTurno(final String idUsuario, final String departamento, final Context mContext)
    {
        try
        {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BaseURL + "turnosDep/" + departamento)
                    .get()
                    .build();
            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    String respuesta = response.body().string();
                    Log.d("DEBUG", respuesta);
                    response.body().close();
                    if (respuesta.contains("start"))
                    {
                        String turnoFin = dependencias.get(departamento) + "-1";
                        pedirTurno(idUsuario, departamento, turnoFin, mContext);
                    }
                    else
                    {
                        respuesta = respuesta.substring(1, respuesta.length() - 1);
                        String[] turno = respuesta.split("-");
                        int turnirijillo = Integer.parseInt(turno[1]) + 1;
                        pedirTurno(idUsuario, departamento, turno[0] + "-" + turnirijillo, mContext);

                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void pedirTurno(String idUsuario, String departamento, final String turno, final Context mContext)
    {
        try
        {

            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();
            Log.d("Debug", idUsuario);
            json.put("idUsuario", idUsuario);
            json.put("numero", turno);
            json.put("departamento", departamento);
            json.put("atendido", false);
            Log.d("DEBUG", json.toString());
            RequestBody body = RequestBody.create(RetrieveFeedTask.JSON, json.toString());
            Request request = new Request.Builder()
                    .url(BaseURL + "turnos/")
                    .post(body)
                    .build();

            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    Estudiante.darEstudiante().setTurno(turno);
                    getTurnoActual(mContext);
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void cancelTurn()
    {
        try
        {

            OkHttpClient client = new OkHttpClient();
            JSONObject json = new JSONObject();
            RequestBody body = RequestBody.create(RetrieveFeedTask.JSON, json.toString());
            Request request = new Request.Builder()
                    .url(BaseURL + "turnos/" + Estudiante.darEstudiante().getCorreo())
                    .put(body)
                    .build();

            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {

                }
            });
        }
        catch (Exception e)
        {

        }
    }

    public void getTurnoActual(final Context mContext)
    {

        try
        {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BaseURL + "turnoActualDep/" + Estudiante.darEstudiante().getCorreo())
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback()
            {
                @Override
                public void onFailure(Call call, IOException e)
                {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException
                {
                    String respuesta = response.body().string();
                    respuesta = respuesta.substring(1, respuesta.length() - 1);
                    response.body().close();
                    if (respuesta.equals("start"))
                    {
                        Looper.prepare();
                        ((Activity) mContext).runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                Toast.makeText(mContext.getApplicationContext(), "Todavia no tiene un turno", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    else
                    {
                            Intent i = new Intent(mContext, TurnoActivity.class).putExtra("turnoActual", respuesta);
                            mContext.startActivity(i);
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}

class RetrieveFeedTask extends AsyncTask<String, Void, String>
{

    private Exception exception;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client = new OkHttpClient();


    protected String doInBackground(String... urls)
    {
        if (urls[0].equals(RestClient.CREAR_USER))
        {
            try
            {
                JSONObject json = new JSONObject();
                json.put("idUsuario", ((int) (Math.random() * 500)));
                json.put("correo", urls[1]);
                json.put("nombre", urls[2]);
                json.put("contraseña", urls[3]);
                json.put("preferencial", new Boolean(urls[4]));

                RequestBody body = RequestBody.create(JSON, json.toString());
                Request request = new Request.Builder()
                        .url(RestClient.BaseURL + "usuarios")
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                Log.d("ABCHUEHUE", response.body().string());
                return response.body().string();
            }
            catch (Exception e)
            {
                this.exception = e;
                return null;
            }
        }
        if (urls[0].equals(RestClient.LOG_IN))
        {
            try
            {
                Request request = new Request.Builder()
                        .url(RestClient.BaseURL + "login/" + urls[1] + "-" + urls[2])
                        .build();
                Response response = client.newCall(request).execute();
                Log.d("ABCHUEHUEHUE", response.body().string());
                return response.body().string();
            }
            catch (Exception e)
            {
                this.exception = e;
                return null;
            }
        }
        return "";
    }
}