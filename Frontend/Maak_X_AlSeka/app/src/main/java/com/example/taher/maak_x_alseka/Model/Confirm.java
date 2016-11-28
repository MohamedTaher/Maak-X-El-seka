package com.example.taher.maak_x_alseka.Model;


import org.json.JSONException;
import org.json.JSONObject;

public class Confirm {
	private int confirm_id;
	private int user_id;
	private int request_id;
	private int state;
	public final static int send = 0;// 0 -> send
	public final static int accept = 1;// 1 -> accept
	public final static int refused = 2;// 2 -> refused
	private String from_lat;
	private String from_lng;
	private String to_lat;
	private String to_lng;

    private Confirm(){}

	public Confirm(int user_id, int request_id, int state, String from_lat,
			String from_lng, String to_lat, String to_lng) {
		super();
		this.user_id = user_id;
		this.request_id = request_id;
		this.state = state;
		this.from_lat = from_lat;
		this.from_lng = from_lng;
		this.to_lat = to_lat;
		this.to_lng = to_lng;
	}

	public static int getAccept() {
		return accept;
	}

	public int getConfirm_id() {
		return confirm_id;
	}

	public String getFrom_lat() {
		return from_lat;
	}

	public String getFrom_lng() {
		return from_lng;
	}

	public static int getRefused() {
		return refused;
	}

	public int getRequest_id() {
		return request_id;
	}

	public static int getSend() {
		return send;
	}

	public int getState() {
		return state;
	}

	public String getTo_lat() {
		return to_lat;
	}

	public String getTo_lng() {
		return to_lng;
	}

	public int getUser_id() {
		return user_id;
	}

    public static Confirm toObject(JSONObject object) {
        Confirm confirm = new Confirm();

        try {
            confirm.confirm_id = object.getInt("confirm_id");
            confirm.user_id = object.getInt("user_id");
            confirm.request_id = object.getInt("request_id");
            confirm.state = object.getInt("state");
            confirm.from_lat = object.getString("from_lat");
            confirm.from_lng = object.getString("from_lng");
            confirm.to_lat = object.getString("to_lat");
            confirm.to_lng = object.getString("to_lng");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return confirm;
    }
}
