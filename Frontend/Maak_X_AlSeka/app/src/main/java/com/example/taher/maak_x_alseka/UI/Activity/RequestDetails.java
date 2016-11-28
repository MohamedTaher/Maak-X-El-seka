package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.Service.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RequestDetails extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private LatLng des, src;
    private boolean req = false;
    private LatLngBounds.Builder builder;
    private Request request;
    private String from_lat, from_lng, to_lat, to_lng;

    @InjectView(R.id.date) TextView date;
    @InjectView(R.id.time) TextView time;

    @InjectView(R.id.description) TextView description;

    @InjectView(R.id.user) TextView user1;
    @InjectView(R.id.state) TextView state1;

    @InjectView(R.id.user2) TextView user2;
    @InjectView(R.id.state2) TextView state2;

    @InjectView(R.id.user3) TextView user3;
    @InjectView(R.id.state3) TextView state3;

    @InjectView(R.id.user4) TextView user4;
    @InjectView(R.id.state4) TextView state4;

    @InjectView(R.id.requestBtn) Button requestBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_details);

        ButterKnife.inject(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    public static Intent getIntent(Request request, Context context){
        Intent intent = new Intent(context, RequestDetails.class);
        intent.putExtra("request", request);
        return intent;
    }

    private void init(){
        Intent intent = getIntent();
        request = (Request)intent.getSerializableExtra("request");

        src = new LatLng(Double.parseDouble(request.getFrom_lat()),Double.parseDouble(request.getFrom_lng()));
        des = new LatLng(Double.parseDouble(request.getTo_lat()),Double.parseDouble(request.getTo_lng()));

        date.setText(request.getData());

        time.setText(request.getTime());

        description.setText(request.getDescription());

        String url;
        Task task;

        url = Constants.GET_USER_BY_ID + "?user_id=" + request.getUser_id();
        task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                try {
                    JSONObject json = new JSONObject(input);
                    final User temp = new User(
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
                    user1.setText("1 - " + json.getString("name"));

                    user1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = Container.getIntent(RequestDetails.this, temp);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        task.execute(url);

        if (request.getUser_id2() != 0) {
            url = Constants.GET_USER_BY_ID + "?user_id=" + request.getUser_id2();
            task = new Task(new OnPostExecute() {
                @Override
                public void onPostExecute(String input) {
                    try {
                        JSONObject json = new JSONObject(input);
                        final User temp = new User(
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
                        user2.setText("2 - " + json.getString("name"));

                        user2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = Container.getIntent(RequestDetails.this, temp);
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            task.execute(url);
        } else {
            user2.setText("2 - empty place");
            req =true;
        }

        if (request.getUser_id3() != 0) {
            url = Constants.GET_USER_BY_ID + "?user_id=" + request.getUser_id3();
            task = new Task(new OnPostExecute() {
                @Override
                public void onPostExecute(String input) {
                    try {
                        JSONObject json = new JSONObject(input);
                        final User temp = new User(
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
                        user3.setText("3 - " + json.getString("name"));

                        user3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = Container.getIntent(RequestDetails.this, temp);
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            task.execute(url);
        } else {
            user3.setText("3 - empty place");
            req = true;
        }

        if (request.getUser_id4() != 0) {
            url = Constants.GET_USER_BY_ID + "?user_id=" + request.getUser_id4();
            task = new Task(new OnPostExecute() {
                @Override
                public void onPostExecute(String input) {
                    try {
                        JSONObject json = new JSONObject(input);
                        final User temp = new User(
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
                        user4.setText("4 - " + json.getString("name"));

                        user4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = Container.getIntent(RequestDetails.this, temp);
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            task.execute(url);
        } else {
            user4.setText("4 - empty place");
            req = true;
        }

        if (request.getUser_id() == Data.user.getUser_id()){
            requestBtn.setText("Show Requests");
        }

        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 if (request.getUser_id() == Data.user.getUser_id()) { //show requests
                    Intent intent = new Intent(RequestDetails.this, ConfirmsForRequest.class);
                    intent.putExtra("request_id", request.getRequest_id());
                    startActivity(intent);

                } else { // request

                    // TODO: 28/11/16 Check if user make request at previous time

                    if (!RequestDetails.this.req) {
                        Toast.makeText(RequestDetails.this, "No Available places", Toast.LENGTH_LONG).show();
                    } else {
                        String url = Constants.REQUEST_TO_REQUEST + "?user_id=" + Data.user.getUser_id() +
                                "&request_id=" + request.getRequest_id()
                                + "&state=0"
                                + "&from_lat=" + from_lat
                                + "&from_lng=" + from_lng
                                + "&to_lat=" + to_lat
                                + "&to_lng=" + to_lng;

                        Task task = new Task(new OnPostExecute() {
                            @Override
                            public void onPostExecute(String input) {
                                try {
                                    JSONObject object = new JSONObject(input);
                                    if (object.getInt("state") == -1) {
                                        Toast.makeText(RequestDetails.this, "Failed", Toast.LENGTH_LONG).show();
                                    } else {
                                        requestBtn.setClickable(false);
                                        requestBtn.setText("Your request is sent");
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                        task.execute(url);
                    }

                }

            }
        });


        mMap.addMarker(new MarkerOptions().position(src).icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions().position(des).icon(BitmapDescriptorFactory
                .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        builder = new LatLngBounds.Builder();
        builder.include(src);
        builder.include(des);
        LatLngBounds bounds = builder.build();

        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {

            @Override
            public void onCameraChange(CameraPosition arg0) {
                // Move camera.
                mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 60));
                // Remove listener to prevent position reset on camera move.
                mMap.setOnCameraChangeListener(null);
            }
        });

        if (FromTo.getSrc() != null) {
            from_lat = FromTo.getSrc().latitude + "";
            from_lng = FromTo.getSrc().longitude + "";
            to_lat = FromTo.getDes().latitude + "";
            to_lng = FromTo.getDes().longitude + "";
        }



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        init();
    }
}
