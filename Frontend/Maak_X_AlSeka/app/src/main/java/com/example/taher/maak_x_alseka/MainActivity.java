package com.example.taher.maak_x_alseka;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.UI.Activity.Login;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public static User user;

    private void init(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {
                startActivity(new Intent(getApplicationContext(), Login.class));
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
