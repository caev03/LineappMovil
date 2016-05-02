package Mundo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;

import com.ce.lineapp.LoginActivity;

import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

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

    public boolean isTerminoLogin()
    {
        return terminoLogin;
    }

    public String getRespuestaLogin() {
        return respuestaLogin;
    }

    public static RestClient darInstancia()
    {
        if(instancia==null)
        {
            instancia = new RestClient();
        }
        return instancia;
    }


    public void createUser(String nombre, String correo, String contraseña, boolean preferencial) throws Exception
    {
        String[] abc = {CREAR_USER,correo,nombre,contraseña,""+preferencial};
        new RetrieveFeedTask().execute(abc);
    }

    public void loginUser(String correo, String contrasena, final Context context, final LoginActivity loginActivity)
    {
        String[] abc = {LOG_IN,correo,contrasena};
        //new RetrieveFeedTask().execute(abc);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(RetrieveFeedTask.BaseURL+"login/"+abc[1]+"-"+abc[2])
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException
            {
                Looper.prepare();
                String abc = response.body().string();
                Log.d("ABC",abc);
                LoginActivity la = LoginActivity.darInstancia();
                la.terminoLogin(abc, context, loginActivity);
            }
        });

    }

    public void finalizoLogin(String d)
    {
        terminoLogin = true;
        respuestaLogin = d;
        Log.d("abc","Holi");
    }
}

class RetrieveFeedTask extends AsyncTask<String, Void, String>
{

    private Exception exception;
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    static OkHttpClient client = new OkHttpClient();
    public final static String BaseURL = "http://157.253.210.159:3000/";


    protected String doInBackground(String... urls)
    {
        if (urls[0].equals(RestClient.CREAR_USER))
        {
            try {
                JSONObject json = new JSONObject();
                json.put("idUsuario",((int)(Math.random()*500)));
                json.put("correo",urls[1]);
                json.put("nombre",urls[2]);
                json.put("contraseña",urls[3]);
                json.put("preferencial", new Boolean(urls[4]));

                RequestBody body = RequestBody.create(JSON, json.toString());
                Request request = new Request.Builder()
                        .url(BaseURL+"usuarios")
                        .post(body)
                        .build();
                Response response = client.newCall(request).execute();
                Log.d("ABCHUEHUE",response.body().string());
                return response.body().string();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }
        if(urls[0].equals(RestClient.LOG_IN))
        {
            try
            {
                Request request = new Request.Builder()
                        .url(BaseURL+"login/"+urls[1]+"-"+urls[2])
                        .build();
                Response response = client.newCall(request).execute();
                Log.d("ABCHUEHUEHUE",response.body().string());
                return response.body().string();
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }
        return "";
    }

    @Override
    protected void onPostExecute(String s)
    {
        Log.d("as","as");
        RestClient.darInstancia().finalizoLogin(s);
    }
}