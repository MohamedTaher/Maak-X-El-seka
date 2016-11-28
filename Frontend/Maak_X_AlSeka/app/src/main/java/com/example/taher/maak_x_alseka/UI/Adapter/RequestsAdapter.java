package com.example.taher.maak_x_alseka.UI.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taher.maak_x_alseka.Model.Request;
import com.example.taher.maak_x_alseka.R;

import java.util.ArrayList;

/**
 * Created by taher on 23/07/16.
 */
public class RequestsAdapter extends ArrayAdapter<Request> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Request> data = null;

    public RequestsAdapter(Context context, int layoutResourceId, ArrayList<Request> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemHolder();
            holder.des = (TextView)row.findViewById(R.id.des);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        Request item = data.get(position);
        holder.des.setText(item.getDescription());

        return row;
    }

    static class ItemHolder
    {
        TextView des;
    }


}
