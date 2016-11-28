package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Intent;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.R;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;



public class Signup extends AppCompatActivity {
    private static final String TAG = "Signup";
    private static int state;
    // TODO: 28/11/16 add gender and true_icon
    @InjectView(R.id.input_name) EditText _nameText;
    @InjectView(R.id.input_email) EditText _emailText;
    @InjectView(R.id.input_password) EditText _passwordText;
    @InjectView(R.id.input_MobileNum) EditText _mobileNumText;
    @InjectView(R.id.input_NationalId) EditText _nationalIdText;

    @InjectView(R.id.btn_signup) Button _signupButton;
    @InjectView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, state+"");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(Signup.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String mobileNumber = _mobileNumText.getText().toString();
        String nationalId = _nationalIdText.getText().toString();

        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                try {
                    JSONObject json = new JSONObject(input);
                    state = json.getInt("state");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = Constants.SIGNUP + "?email="+email+"&password="+ password + "&national_id=" + nationalId + "&mobile_num=" + mobileNumber+ "&name=" + name.replace(" ", "%20");
        task.execute(url);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(state != -1)
                            onSignupSuccess();
                        else
                            onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        String mobileNumber = _mobileNumText.getText().toString();
        String nationalId = _nationalIdText.getText().toString();

        if (name.isEmpty()) {
            _nameText.setError(getString(R.string.nameError));
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        if (nationalId.isEmpty() || nationalId.length() != 14) {
            _nameText.setError(getString(R.string.nameError));
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (mobileNumber.isEmpty() || mobileNumber.length() != 11) {
            _nameText.setError(getString(R.string.nameError));
            valid = false;
        } else {
            _nameText.setError(null);
        }

        return valid;
    }
}
