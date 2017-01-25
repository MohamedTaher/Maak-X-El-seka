package com.example.taher.maak_x_alseka.UI.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.HTTPTasks.*;
import com.example.taher.maak_x_alseka.Helper.Check;
import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Login extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.btn_login) Button _loginButton;
    @InjectView(R.id.link_signup) TextView _signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.login);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {

                try {
                    JSONObject json = new JSONObject(input);
                    Data.user = new User(
                            json.getString("email"),
                            json.getString("image"),
                            json.getString("mobile_num"),
                            json.getString("name"),
                            json.getString("national_id"),
                            json.getString("password"),
                            json.getString("positive_rate"),
                            json.getString("rate_total"),
                            json.getInt("user_id")
                    );
                } catch (JSONException e) {

                }
                Log.v(TAG, input);
            }
        });

        String url = Constants.LOGIN + "?email="+email+"&password="+ password;
        task.execute(url);


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if (Data.user != null)
                            onLoginSuccess();
                        else
                            onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 6 || password.length() > 45) {
            _passwordText.setError(this.getString(R.string.passwordLen));
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public void onLoginSuccess() {

        SharedPreferences.Editor prefsEditor = getSharedPreferences(Data.USER_PREF, MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(Data.user); // myObject - instance of MyObject
        prefsEditor.putString(Data.USER_PREF, json);
        prefsEditor.commit();
/*
        SharedPreferences.Editor editor = getSharedPreferences(Data.USER_PREF, MODE_PRIVATE).edit();
        editor.putString(Data.USER_PREF, Data.user.toString());
        editor.commit();
*/
        _loginButton.setEnabled(true);
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {

        String massage;

        if (!Check.isOnline(this))
            massage = getString(R.string.noInternetConnection);
        else
            massage = getString(R.string.wrongEmailOrPassword);


        Snackbar snackbar = Snackbar.make(getWindow().getCurrentFocus(), massage, Snackbar.LENGTH_LONG);
        snackbar.show();

        _loginButton.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
