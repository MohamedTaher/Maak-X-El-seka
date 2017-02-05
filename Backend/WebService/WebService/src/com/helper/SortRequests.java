package com.helper;

import java.util.Comparator;
import com.Model.Request;

public class SortRequests implements Comparator<Request>{

	private final double radius = 6378137;   // approximate Earth radius, *in meters*
	private double from_lat, from_lng, to_lat, to_lng;
	
	public SortRequests(String from_lat, String from_lng, String to_lat,
			String to_lng) {
		super();
		this.from_lat = Double.parseDouble(from_lat);
		this.from_lng = Double.parseDouble(from_lng);
		this.to_lat = Double.parseDouble(to_lat);
		this.to_lng = Double.parseDouble(to_lng);
	}

	@Override
	public int compare(Request r1, Request r2) {
		
		double r1_f_lat = Double.parseDouble(r1.getFrom_lat())
		, r1_f_lng = Double.parseDouble(r1.getFrom_lng())
		, r1_t_lat = Double.parseDouble(r1.getTo_lat())
		, r1_t_lng = Double.parseDouble(r1.getTo_lng())
		
		, r2_f_lat = Double.parseDouble(r2.getFrom_lat())
		, r2_f_lng = Double.parseDouble(r2.getFrom_lng())
		, r2_t_lat = Double.parseDouble(r2.getTo_lat())
		, r2_t_lng = Double.parseDouble(r2.getTo_lng());
		
		double r1_diff = distance(r1_f_lat, r1_f_lng, from_lat, from_lng)
				+ distance(r1_t_lat, r1_t_lng, to_lat, to_lng);
		
		double r2_diff = distance(r2_f_lat, r2_f_lng, from_lat, from_lng)
				+ distance(r2_t_lat, r2_t_lng, to_lat, to_lng);
		
		return (int) (r1_diff - r2_diff);
	}
	
	public double distance(double fromLat, double fromLon, double toLat, double toLon) {
        double deltaLat = toLat - fromLat;
        double deltaLon = toLon - fromLon;
        double angle = 2 * Math.asin( Math.sqrt(
                Math.pow(Math.sin(deltaLat/2), 2) +
                        Math.cos(fromLat) * Math.cos(toLat) *
                                Math.pow(Math.sin(deltaLon/2), 2) ) );
        return radius * angle;
    }

}
