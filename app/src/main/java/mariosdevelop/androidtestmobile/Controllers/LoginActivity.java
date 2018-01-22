package mariosdevelop.androidtestmobile.Controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mariosdevelop.androidtestmobile.Controllers.HTTPConnection.httpConect;
import mariosdevelop.androidtestmobile.Controllers.Interface.HttpMain;
import mariosdevelop.androidtestmobile.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements HttpMain {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private Activity ActivityContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.User);

        mPasswordView = (EditText) findViewById(R.id.password);

         ActivityContext = this;


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }




    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.Prompt_addContraseña));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.Prompt_addUsuario));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            try {

                httpConect conect =  new httpConect(this,this);

                conect.RequestPost(JSOnMaker(email, password), "Yolo");
            }
            catch (JSONException Ex){
                Ex.printStackTrace();
            }

           // mAuthTask = new UserLoginTask(email, password);
           // mAuthTask.execute((Void) null);
        }
    }



    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    @Override
    public void Responce(String Responce) {

        Log.v("Response",Responce);
        showProgress(false);

        if(Responce.startsWith("{")){

            saveInfo(Responce);

            Intent Marcas = new Intent(ActivityContext,MarcaActivity.class);
            ActivityContext.startActivity(Marcas);

        }else {
            Toast.makeText(ActivityContext,"Peticion Fallida ,Retry",Toast.LENGTH_SHORT).show();
        }

    }

    private JSONObject JSOnMaker(String User,String Contraseña)throws JSONException{

        JSONObject ObjectData = new JSONObject(); ;
        JSONObject ObjectInterntalData = new JSONObject();

        ObjectInterntalData.put("pass",Contraseña);
        ObjectInterntalData.put("user",User);
        ObjectData.put("data",ObjectInterntalData);


        Log.v("JSon :" ,ObjectData.toString());

    return ObjectData;
    }

    private void saveInfo(String response){

        try{
            JSONObject JSonResponse = new JSONObject(response);
            String Agent = "";
            if(!JSonResponse.get("agente").equals(null))
             Agent = JSonResponse.getString("agente");
            String ErrorRes = "";
             if(!JSonResponse.get("error").equals(null))
                 ErrorRes =JSonResponse.getString("error");
            int IDuser =0;
            if(!JSonResponse.get("id_user").equals(null))
                IDuser = JSonResponse.getInt("id_user");
            String Token="";
            if(!JSonResponse.get("token").equals(null))
              Token = JSonResponse.getString("token");



            SharedPreferences sharedPref =  ActivityContext.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("Agente",Agent);
            editor.putString("error",ErrorRes.equals("")? "": ErrorRes );
            editor.putInt("id_user",IDuser);
            editor.putString("token",Token);
            editor.commit();



        }
        catch (JSONException ExJson ){
            ExJson.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        System.exit(0);
    }
}

