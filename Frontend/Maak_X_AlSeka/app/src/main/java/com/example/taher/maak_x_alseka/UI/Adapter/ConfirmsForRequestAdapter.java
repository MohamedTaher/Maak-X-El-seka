package com.example.taher.maak_x_alseka.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Confirm;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.Model.User;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.UI.Activity.Container;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by taher on 28/11/16.
 */
public class ConfirmsForRequestAdapter extends ArrayAdapter<Confirm> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Confirm> data = null;
    private static User user;
    private ItemHolder holder = null;

    public ConfirmsForRequestAdapter(Context context, int layoutResourceId, ArrayList<Confirm> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemHolder();
            holder.des = (TextView)row.findViewById(R.id.description);
            holder._true = (ImageView)row.findViewById(R.id.accept);
            holder._false = (ImageView)row.findViewById(R.id.refused);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        final Confirm item = data.get(position);
        final View v = row;

        Task task = new Task(new OnPostExecute() {
            @Override
            public void onPostExecute(String input) {
                Log.v("user ==> ", input);
                JSONObject json = null;
                try {
                    json = new JSONObject(input);
                    holder.des.setText(json.getString("name"));
                    final User user = new User(
                            json.getString("email"),
                            json.getString("image"),
                            json.getString("mobile_num"),
                            json.getString("name"),
                            json.getString("national_id"),
                            json.getString("password"),
                            json.getString("positive_rate"),
                            json.getString("rate_total"),
                            json.getInt("user_id"));

                    v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = Container.getIntent(context, user);
                            context.startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        String url = Constants.GET_USER_BY_ID + "?user_id=" + item.getUser_id();
        task.execute(url);



        holder._true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(new OnPostExecute() {
                    @Override
                    public void onPostExecute(String input) {
                        Toast.makeText(context, "Done",Toast.LENGTH_LONG).show();
                    }
                });

                String url = Constants.ACCEPT_REQUEST + "?request_id=" + item.getRequest_id()
                        + "&user_id=" + item.getUser_id();

                task.execute(url);
            }
        });

        holder._false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = new Task(new OnPostExecute() {
                    @Override
                    public void onPostExecute(String input) {
                        Toast.makeText(context, "Done",Toast.LENGTH_LONG).show();
                    }
                });

                String url = Constants.REFUSED_REQUEST + "?request_id=" + item.getRequest_id()
                        + "&user_id=" + item.getUser_id();

                task.execute(url);
            }
        });

        return row;
    }


    static class ItemHolder
    {
        TextView des;
        ImageView _true;
        ImageView _false;
    }


}
