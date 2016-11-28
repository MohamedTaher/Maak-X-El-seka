package com.example.taher.maak_x_alseka.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Request implements Serializable {
	private int user_id;
	private int request_id;
	private String from_lat;
	private String from_lng;
	private String to_lat;
	private String to_lng;
	private String description;
	private String state;
	private String data;
	private String time;
	private int user_id2;
	private int user_id3;
	private int user_id4;

	private Request() {
	}

	public Request(int user_id, String from_lat, String from_lng,
			String to_lat, String to_lng, String description, String state,
			String data, String time) {
		super();
		this.user_id = user_id;
		this.from_lat = from_lat;
		this.from_lng = from_lng;
		this.to_lat = to_lat;
		this.to_lng = to_lng;
		this.description = description;
		this.state = state;
		this.data = data;
		this.time = time;
	}

	public static Request toObject (JSONObject json){
        Request request = new Request();

        try {
            request.request_id = json.getInt("request_id");
            request.from_lat = json.getString("from_lat");
            request.from_lng = json.getString("from_lng");
            request.to_lat = json.getString("to_lat");
            request.to_lng = json.getString("to_lng");
            request.description = json.getString("description");
            request.state = json.getString("state");
            request.data = json.getString("data");
            request.time = json.getString("time");
            request.user_id = json.getInt("user_id");
            request.user_id2 = json.getInt("user_id2");
            request.user_id3 = json.getInt("user_id3");
            request.user_id4 = json.getInt("user_id4");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return request;
    }

    public String getData() {
        return data;
    }

    public String getDescription() {
        return description;
    }

    public String getFrom_lat() {
        return from_lat;
    }

    public String getFrom_lng() {
        return from_lng;
    }

    public int getRequest_id() {
        return request_id;
    }

    public String getState() {
        return state;
    }

    public String getTime() {
        return time;
    }

    public String getTo_lat() {
        return to_lat;
    }

    public String getTo_lng() {
        return to_lng;
    }

    public int getUser_id2() {
        return user_id2;
    }

    public int getUser_id3() {
        return user_id3;
    }

    public int getUser_id4() {
        return user_id4;
    }

    public int getUser_id() {
        return user_id;
    }
}
