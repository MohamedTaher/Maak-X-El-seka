package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Confirm;
import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Adapter.ConfirmsForRequestAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConfirmsForRequest extends AppCompatActivity {

    @InjectView(R.id.confirms_list)
    ListView _confirmsList;
    private ArrayList<Confirm> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirms_for_request);

        ButterKnife.inject(this);
        init();
    }

    void init() {

        Intent intent = getIntent();
        int request_id = intent.getIntExtra("request_id", -1);
        if (request_id == -1) {
            Toast.makeText(this,"Failed",Toast.LENGTH_LONG);
            return;
        }

        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                Log.v("Input ----> " , input);
                try {
                    JSONObject json = new JSONObject(input), temp;
                    JSONArray resultsJson = json.getJSONArray("result");

                    for (int i = 0; i < resultsJson.length(); i++){
                        temp = resultsJson.getJSONObject(i);
                        data.add(Confirm.toObject(temp));



                    }

                    ConfirmsForRequestAdapter adapter = new ConfirmsForRequestAdapter(ConfirmsForRequest.this,R.layout.confirm_operation_row, data);
                    _confirmsList.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = Constants.GET_CONFIRMS_BY_REQUEST + "?request_id="+ request_id;
        task.execute(url);
    }
}
