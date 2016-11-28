package com.example.taher.maak_x_alseka.UI.Fragment;

/**
 * Created by taher on 25/11/16.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Activity.Home;
import com.example.taher.maak_x_alseka.UI.Activity.RequestDetails;
import com.example.taher.maak_x_alseka.UI.Adapter.RequestsAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Requests extends Fragment {

    @InjectView(R.id.requestsList) ListView _requestList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.request_fragment, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    void init(){
        RequestsAdapter adapter = new RequestsAdapter(getActivity(), R.layout.request_row, Home.getRequests());
        _requestList.setAdapter(adapter);

        _requestList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("reqFragmnet" , "---------------onItemClick--------------");
                Intent intent = RequestDetails.getIntent(Home.getRequests().get(position), getContext());
                startActivity(intent);
            }
        });
    }
}
