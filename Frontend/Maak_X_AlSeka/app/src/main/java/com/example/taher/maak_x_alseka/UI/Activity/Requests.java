package com.example.taher.maak_x_alseka.UI.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;


import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Adapter.RequestsAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Requests extends AppCompatActivity {

    @InjectView(R.id.requests) ListView requests;
    @InjectView(R.id.fab) FloatingActionButton req;

    private ArrayList<Request> requestsData = new ArrayList<>();
    private static String date = "", time = "", description = "";
    private String from_lat, from_lng, to_lat, to_lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);

        ButterKnife.inject(this);

        init();
    }

    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Requests");

        Intent intent = getIntent();
        from_lat = intent.getStringExtra("from_lat");
        from_lng = intent.getStringExtra("from_lng");
        to_lat = intent.getStringExtra("to_lat");
        to_lng = intent.getStringExtra("to_lng");

        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                try {
                    JSONObject json = new JSONObject(input), temp;
                    JSONArray resultsJson = json.getJSONArray("result");

                    for (int i = 0; i < resultsJson.length(); i++){
                        temp = resultsJson.getJSONObject(i);
                        requestsData.add(Request.toObject(temp));
                    }

                    RequestsAdapter adapter = new RequestsAdapter(Requests.this,R.layout.request_row,requestsData);
                    requests.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = Constants.GET_REQUESTS + "?from_lat=" + from_lat
                                            + "&from_lng=" + from_lng
                                            + "&to_lat=" + to_lat
                                            + "&to_lng=" + to_lng;
        task.execute(url);


        req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Date");

                newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Time");

                doRequest();


            }
        });

        requests.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = RequestDetails.getIntent(requestsData.get(position), Requests.this);
                startActivity(intent);
            }
        });
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time = hourOfDay + ":" + minute;
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            date = day + "-" + month + "-" + year;
        }
    }

    private void doRequest(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Description");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Finish", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                description = input.getText().toString();

                if (time.length() == 0 || date.length() == 0 ) {
                    Toast.makeText(Requests.this, "Please set full data", Toast.LENGTH_LONG).show();
                    return;
                }


                Task task = new Task(new OnPostExecute() {
                    @Override
                    public void onPostExecute(String input) {

                    }
                });

                String url = Constants.ADD_REQUEST + "?user_id=" + Data.user.getUser_id() +
                        "&from_lat=" + from_lat +
                        "&from_lng=" + from_lng +
                        "&to_lat=" + to_lat +
                        "&to_lng=" + to_lng +
                        "&description=" + description.replace(" ", "%20") +
                        "&state=" + "0" +
                        "&date=" + date +
                        "&time=" + time;
                task.execute(url);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
