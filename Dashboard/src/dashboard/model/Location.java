package dashboard.model;

public class Location {

	private double longitude;
	private double lattitude;
	private String name;
	
	public Location(double longitude,double lattitude,String name){
		this.longitude = longitude;
		this.lattitude = lattitude;
		this.name = name;
	}
	
	public double getLattitude() {
		return lattitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean withinRadius(Location other,double radius){
		double disLo= Math.abs(getLongitude() - other.getLongitude());
		double disLa= Math.abs(getLattitude() - other.getLattitude());
		return (Math.sqrt(disLo*disLo + disLa*disLa) <= radius);
	}
}
