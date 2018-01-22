package mariosdevelop.androidtestmobile.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import mariosdevelop.androidtestmobile.R;

public class SplashActivity extends AppCompatActivity {

    long Retardo = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        Timer RunSplash = new Timer();

        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {

                finish();

                Intent StartLogin = new Intent(SplashActivity.this,LoginActivity.class);

                startActivity(StartLogin);

            }
        };

        RunSplash.schedule(ShowSplash,Retardo);

    }
}
