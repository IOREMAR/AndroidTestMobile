package mariosdevelop.androidtestmobile.Controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;

import android.support.v7.app.ActionBar;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


import android.os.Build;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;

import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mariosdevelop.androidtestmobile.Controllers.Fragments.RecargaDialog;
import mariosdevelop.androidtestmobile.Controllers.HTTPConnection.httpConect;
import mariosdevelop.androidtestmobile.Controllers.Interface.HttpMain;
import mariosdevelop.androidtestmobile.R;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class Login_Agent_Activity extends AppCompatActivity implements HttpMain  {


    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__agent_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.ToolbarSeccion);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);
        ActionBar ActionBR = getSupportActionBar();

        ActionBR.setDisplayHomeAsUpEnabled(true);
        ActionBR.setHomeButtonEnabled(true);


        // toolbar.setOverflowIcon(getResources().getDrawable(R.drawable.ic_settings));
        //actionBar.setIcon();
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.User);


        mPasswordView = (EditText) findViewById(R.id.password);


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
            mEmailView.setError(getString(R.string.error_field_required));

        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));

        }

        showEditDialog();
        /*
try{

    httpConect hpConect = new httpConect(this,this);
    hpConect.RequestPost(JSOnMaker(mEmailView.getText().toString(),mPasswordView.getText().toString()),"yolo");

    }
    catch (JSONException EX){
            EX.printStackTrace();
    }
    */

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



    }

    private JSONObject JSOnMaker(String User, String Contraseña)throws JSONException {

        JSONObject ObjectData = new JSONObject(); ;
        JSONObject ObjectInterntalData = new JSONObject();

        ObjectInterntalData.put("pass",Contraseña);
        ObjectInterntalData.put("user",User);
        ObjectData.put("data",ObjectInterntalData);


        Log.v("JSon :" ,ObjectData.toString());

        return ObjectData;
    }

    private void showEditDialog() {
          FragmentManager FM =    getSupportFragmentManager();
        RecargaDialog editNameDialog = new RecargaDialog();
        editNameDialog.show(FM,"TAG");
        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }


        return super.onOptionsItemSelected(item);
    }
}

