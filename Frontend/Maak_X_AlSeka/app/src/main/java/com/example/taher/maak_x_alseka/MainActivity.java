package com.example.taher.maak_x_alseka;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.UI.Activity.Home;
import com.example.taher.maak_x_alseka.UI.Activity.Login;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static User user;

    private void init(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                Gson gson = new Gson();
                String json = getSharedPreferences(Data.USER_PREF, MODE_PRIVATE).getString(Data.USER_PREF, null);

                if (json != null){

                    Data.user = gson.fromJson(json, User.class);
                    startActivity(new Intent(getApplicationContext(), Home.class));

                } else {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
            }

        }, 2500);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

}
