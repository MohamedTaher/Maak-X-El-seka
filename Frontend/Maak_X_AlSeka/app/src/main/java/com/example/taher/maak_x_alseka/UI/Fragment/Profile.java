package com.example.taher.maak_x_alseka.UI.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taher on 25/11/16.
 */
public class Profile extends Fragment {

    // TODO: 28/11/16 rating and gender

    @InjectView(R.id.name)
    TextView _name;
    @InjectView(R.id.mobile)
    TextView _mobile;
    @InjectView(R.id.email)
    TextView _email;
    @InjectView(R.id.nationalId)
    TextView _nationalID;
    @InjectView(R.id.ratingBar)
    RatingBar _rate;

    private static User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ButterKnife.inject(this, view);

        _name.setText(user.getName());
        _email.setText(user.getEmail());
        _mobile.setText(user.getMobile_num());
        _nationalID.setText(user.getNational_id());

        _rate.setRating(5);

        return view;
    }

    public void setUser(User user){
        this.user = user;
    }

}
