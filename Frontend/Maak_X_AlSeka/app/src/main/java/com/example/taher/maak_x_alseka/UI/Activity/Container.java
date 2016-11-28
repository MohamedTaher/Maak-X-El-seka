package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Fragment.Profile;
import android.support.v4.app.FragmentActivity;

public class Container extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_container);

        Profile profile = new Profile();
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user");
        profile.setUser(user);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, profile)
                    .commit();
        }
    }

    public static Intent getIntent(Context context, User user) {
        Intent intent = new Intent(context,Container.class);
        intent.putExtra("user", user);
        return intent;
    }
}

