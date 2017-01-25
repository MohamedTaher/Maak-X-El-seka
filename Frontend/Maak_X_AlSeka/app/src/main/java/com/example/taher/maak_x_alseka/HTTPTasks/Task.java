package com.example.taher.maak_x_alseka.HTTPTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Toast;

import com.example.taher.maak_x_alseka.Helper.Check;
import com.example.taher.maak_x_alseka.R;
import com.example.taher.maak_x_alseka.app.MyApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by taher on 25/11/16.
 */


public class Task extends AsyncTask<String, Integer, String> {

    private OnPostExecute onPostExecute;

    public Task(OnPostExecute onPostExecute)
    {
        this.onPostExecute = onPostExecute;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Context mContext = MyApplication.getContext();
        boolean online = Check.isOnline(mContext);

        if (!online) {
            this.cancel(true);
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    // params[0] is url
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String jsonStr = null;

        try {
            String baseUrl = params[0];
            Log.v("TASK", baseUrl);
            URL url = new URL(baseUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) return null;

            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null)
                buffer.append(line + "\n");


            if (buffer.length() == 0) return null;

            jsonStr = buffer.toString();

        }
        catch (IOException e) {

            jsonStr = null;
            Log.e("TASK EXCEPTION",e.toString());

        } finally {

            if (urlConnection != null)
                urlConnection.disconnect();

            if (reader != null)
                try {
                    reader.close();
                } catch (final IOException e) {

                }

        }
        return jsonStr;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        onPostExecute.onPostExecute(str);
    }
}
