package com.example.taher.maak_x_alseka.UI.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Confirm;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Activity.Home;
import com.example.taher.maak_x_alseka.UI.Activity.RequestDetails;
import com.example.taher.maak_x_alseka.UI.Adapter.ConfirmsAdapter;
import com.example.taher.maak_x_alseka.UI.Adapter.RequestsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by taher on 25/11/16.
 */
public class Confirms extends Fragment {

    @InjectView(R.id.confirmsList) ListView _confirmsList;
    private ArrayList<Request> requests;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.confirms_fragment, container, false);
        ButterKnife.inject(this, view);
        init();
        return view;
    }

    void init(){
        ConfirmsAdapter adapter = new ConfirmsAdapter(getActivity(), R.layout.confirm_row, Home.getConfirms());
        _confirmsList.setAdapter(adapter);

        _confirmsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("confirmFragment", "---------------onItemClick--------------");
                Intent intent = RequestDetails.getIntent(requests.get(position), getContext());
                startActivity(intent);
            }
        });

        requests = getRequests(Home.getConfirms());
    }

    private ArrayList<Request> getRequests(ArrayList<Confirm> data){
        final ArrayList<Request> requests = new ArrayList<>();

        for (Confirm c: data){

            Task task = new Task(new OnPostExecute() {
                @Override
                public void onPostExecute(String input) {
                    Log.v("request ==> ", input);
                    JSONObject json = null;
                    try {
                        json = new JSONObject(input);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    requests.add(Request.toObject(json));
                }
            });


            String url = Constants.GET_REQUEST_BY_ID + "?request_id=" + c.getRequest_id();
            task.execute(url);

        }

        return requests;
    }
}
