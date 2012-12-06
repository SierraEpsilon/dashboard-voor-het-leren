package dashboard.model;

import java.io.Serializable;

public class Location implements Serializable{

	private static final long serialVersionUID = 54213451;
	private double longitude;
	private double latitude;
	private String name;
	
	public Location(double longitude,double latitude,String name){
		this.longitude = longitude;
		this.latitude = latitude;
		this.name = name;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean withinRadius(Location other,double radius){
		double disLo= Math.abs(getLongitude() - other.getLongitude());
		double disLa= Math.abs(getLatitude() - other.getLatitude());
		return (Math.sqrt(disLo*disLo + disLa*disLa) <= radius);
	}
}
