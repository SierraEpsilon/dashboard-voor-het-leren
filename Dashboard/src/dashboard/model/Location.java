package dashboard.model;

import java.io.Serializable;

public class Location implements Serializable{

	private static final long serialVersionUID = 54213451;
	private double longitude;
	private double latitude;
	private int accuracy;
	private String adres;
	private String alias;
	
	public Location(double longitude,double latitude,int accuracy,String adres,String alias){
		this.longitude = longitude;
		this.latitude = latitude;
		this.adres = adres;
		this.alias = alias;
		this.accuracy = accuracy;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public double getAccuracy() {
		return accuracy;
	}
	
	public String getAlias() {
		return alias;
	}
	
	public String getAdres() {
		return adres;
	}
	
	public boolean withinRadius(Location other,double radius){
		double distance = distance(other);
		return (distance<=radius);
	}
	
	public double distance(Location other){
		double theta = Math.toRadians(other.getLongitude()-this.longitude);
		double phi = Math.toRadians(other.getLatitude()-this.latitude);
		double earthRadius = 6371000;
		//formula distance between spheric coordinates with same radius
		//distance = R*sqrt(tan²(theta)*cos²(phi)+sin²(phi))
		double distance = earthRadius*Math.sqrt(Math.pow(Math.tan(theta)*Math.cos(phi),2)+Math.pow(Math.sin(phi),2));
		return distance;
	}
	
	public double distanceWorstCase(Location other){
		double theta = Math.toRadians(other.getLongitude()-this.longitude);
		double phi = Math.toRadians(other.getLatitude()-this.latitude);
		double earthRadius = 6371000;
		//formula distance between spheric coordinates with same radius
		//distance = R*sqrt(tan²(theta)*cos²(phi)+sin²(phi))
		double distance = earthRadius*Math.sqrt(Math.pow(Math.tan(theta)*Math.cos(phi),2)+Math.pow(Math.sin(phi),2));
		return distance+this.accuracy+other.getAccuracy();
	}
}
