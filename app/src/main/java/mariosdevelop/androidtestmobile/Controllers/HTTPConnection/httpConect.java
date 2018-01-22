package mariosdevelop.androidtestmobile.Controllers.HTTPConnection;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import mariosdevelop.androidtestmobile.Controllers.Interface.HttpMain;
import mariosdevelop.androidtestmobile.Controllers.LoginActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by marioaguilar on 17/01/18.
 */

    public class httpConect {

    public static HttpMain Httpmain;

    public static Activity MainClass;
        public httpConect (HttpMain httpmain, Activity loginActivity ){
            this.Httpmain = httpmain;
            MainClass=loginActivity;
        }

      //  static final String BaseStirng = "{'data':{'pass':'password','user':'Luis'}}";

        static final String BaseULR = "https://agentemovil.pagatodo.com/AgenteMovil.svc/agMov/login";

        static OkHttpClient client ;

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void RequestPost (JSONObject PostBody, Object Header) throws JSONException{
        Start();
       // RequestBody body = RequestBody.create(JSON,PostBody.toString());
        RequestBody body = RequestBody.create(JSON,PostBody.toString());

        Log.v("JSOnBody ", body.contentType().toString());

      final   Request  request = new Request.Builder().url(BaseULR).post(body)
              .addHeader("OS","Android")
              .addHeader("Version","2.5.2")
              .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                Log.v("OnfAilure","Fail");
                Log.v("OnfAilure",e.getMessage());
               // call.request().body().toString();
                e.printStackTrace();

               // Httpmain.Responce(e.getMessage());
                MainClass.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Httpmain.Responce(e.getMessage());
                    }
                });
                call.cancel();

            }

            @Override
            public void onResponse(Call call,final Response response) throws IOException {
                //Log.v("Respone",response.body().string());
                Log.v("Respone httpcode ",response.code()+"");

                MainClass.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Httpmain.Responce(response.body().string());
                        }
                        catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                });

                call.cancel();
               // Httpmain.Responce(response.body().string());


            }
        });


    }

    private static void Start(){
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
