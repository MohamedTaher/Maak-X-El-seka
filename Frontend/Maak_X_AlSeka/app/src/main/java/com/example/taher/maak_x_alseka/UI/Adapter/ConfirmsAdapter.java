package com.example.taher.maak_x_alseka.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taher.maak_x_alseka.HTTPTasks.Constants;
import com.example.taher.maak_x_alseka.HTTPTasks.OnPostExecute;
import com.example.taher.maak_x_alseka.HTTPTasks.Task;
import com.example.taher.maak_x_alseka.Model.Confirm;
import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by taher on 26/11/16.
 */
public class ConfirmsAdapter extends ArrayAdapter<Confirm> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Confirm> data = null;
    private static Request request;

    public ConfirmsAdapter(Context context, int layoutResourceId, ArrayList<Confirm> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ItemHolder holder = new ItemHolder();;

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        row = inflater.inflate(layoutResourceId, parent, false);

        holder.des = (TextView)row.findViewById(R.id.desRq);
        holder.state = (ImageView)row.findViewById(R.id.stateRq);



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

                request = Request.toObject(json);
                holder.des.setText(request.getDescription());
            }
        });

        Confirm item = data.get(position);
        String url = Constants.GET_REQUEST_BY_ID + "?request_id=" + item.getRequest_id();
        task.execute(url);


        int state = item.getState();
        if (state == 1){
            holder.state.setImageResource(R.drawable.true_icon);

        } else if (state == 2) {
            holder.state.setImageResource(R.drawable.cross);
        } else
            holder.state.setImageResource(R.drawable.sent_icon);


        return row;
    }


    static class ItemHolder
    {
        TextView des;
        ImageView state;
    }


}