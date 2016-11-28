package com.example.taher.maak_x_alseka.UI.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.taher.maak_x_alseka.Data.Data;
import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Confirm;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Adapter.PagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private final String TAG = "Home";
    private static ArrayList<Request> requests = new ArrayList<>();
    private static ArrayList<Confirm> confirms = new ArrayList<>();

    public static ArrayList<Request> getRequests() {
        return requests;
    }

    public static ArrayList<Confirm> getConfirms() {
        return confirms;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.v(TAG, Data.user.getName() + " ");

        init();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    private void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.profileTitle)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.requestTitle)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.confirmTitle)));

        //tabLayout.getTabAt(5).setIcon(R.drawable.icon3);

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    //tab.setIcon(R.drawable.icon11);
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getRequests(Data.user.getUser_id());
        getConfirms(Data.user.getUser_id());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FromTo.class));
            }
        });
    }

    private void getRequests(int user_id){
        if (requests.size() != 0)return;
        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                try {
                    JSONObject json = new JSONObject(input), temp;
                    JSONArray resultsJson = json.getJSONArray("result");

                    for (int i = 0; i < resultsJson.length(); i++){
                        temp = resultsJson.getJSONObject(i);
                        requests.add(Request.toObject(temp));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = Constants.GET_REQUEST_BY_USER_ID + "?user_id="+ user_id;
        task.execute(url);
    }

    private void getConfirms(int user_id){
        if (confirms.size() != 0)return;
        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                try {
                    JSONObject json = new JSONObject(input), temp;
                    JSONArray resultsJson = json.getJSONArray("result");

                    for (int i = 0; i < resultsJson.length(); i++){
                        temp = resultsJson.getJSONObject(i);
                        confirms.add(Confirm.toObject(temp));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        String url = Constants.GET_CONFIRM_BY_USER_ID + "?user_id="+ user_id;
        task.execute(url);
    }
}
