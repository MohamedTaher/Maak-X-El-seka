package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.Service.GPSTracker;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class FromTo extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static LatLng des, src;
    private Button go, cancel;

    public static LatLng getDes() {
        return des;
    }

    public static void setDes(LatLng des) {
        FromTo.des = des;
    }

    public static LatLng getSrc() {
        return src;
    }

    public static void setSrc(LatLng src) {
        FromTo.src = src;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_to);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init();
    }

    void init(){
        go = (Button)findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (des == null) {
                    Toast.makeText(getApplicationContext(), "Pleas enter your destination", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Requests.class);
                    intent.putExtra("from_lat", src.latitude + "");
                    intent.putExtra("from_lng", src.longitude + "");
                    intent.putExtra("to_lat", des.latitude + "");
                    intent.putExtra("to_lng", des.longitude + "");

                    startActivity(intent);
                }
            }
        });

        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        /*
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/

        currentLocation();

        showEntryAlert(this);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng point) {
                des = point;
                mMap.clear();
                currentLocation();
                mMap.addMarker(new MarkerOptions().position(point).icon(BitmapDescriptorFactory
                        .defaultMarker(BitmapDescriptorFactory.HUE_BLUE )));

                LatLngBounds.Builder builder = new LatLngBounds.Builder();
                builder.include(src);
                builder.include(des);
                LatLngBounds bounds = builder.build();
                int padding = 60; // offset from edges of the map in pixels
                CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
                mMap.animateCamera(cu);
            }
        });
    }

    private void currentLocation(){
        GPSTracker gps = new GPSTracker(this);
        double latitude, longitude;
        if(gps.canGetLocation()){
            latitude = gps.getLatitude();
            longitude = gps.getLongitude();
            LatLng curLoc = new LatLng(latitude, longitude);
            src = curLoc;
            mMap.moveCamera(CameraUpdateFactory.newLatLng(curLoc));
            mMap.addMarker(new MarkerOptions().position(curLoc).title("Your are here :D"));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));

        }else{
            gps.showSettingsAlert();
        }
        gps.stopUsingGPS();
    }

    public void showEntryAlert(Context context){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle("Instruction");

        // Setting Dialog Message
        alertDialog.setMessage("Pleas enter your destination");

        alertDialog.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
