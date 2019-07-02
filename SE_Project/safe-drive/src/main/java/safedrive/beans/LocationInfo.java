package safedrive.beans;


public class LocationInfo {
	
	private int userID;
	private String vehicleID;
	private double longitude;
	private double latitude;
	private String locationTime;
	private String city;
	private String state;
	private String country;
	private int locationID;
	public LocationInfo()
	{
		
	}
	
	public LocationInfo(int userID, String vehicleID, double longitude, double latitude, String locationTime) {
		super();
		this.userID = userID;
		this.vehicleID = vehicleID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationTime = locationTime;
	}
	
	
	public LocationInfo(int userID, String vehicleID, double longitude, double latitude, String locationTime,
			String city, String state, String country) {
		super();
		this.userID = userID;
		this.vehicleID = vehicleID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationTime = locationTime;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	
	

	public LocationInfo(int userID, String vehicleID, double longitude, double latitude, String locationTime,
			String city, String state, String country, int locationID) {
		super();
		this.userID = userID;
		this.vehicleID = vehicleID;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locationTime = locationTime;
		this.city = city;
		this.state = state;
		this.country = country;
		this.locationID = locationID;
	}

	public int getLocationID() {
		return locationID;
	}

	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getVehicleID() {
		return vehicleID;
	}
	public void setVehicleID(String vehicleID) {
		this.vehicleID = vehicleID;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public String getLocationTime() {
		return locationTime;
	}
	public void setLocationTime(String locationTime) {
		this.locationTime = locationTime;
	}

	@Override
	public String toString() {
		return "LocationInfo [userID=" + userID + ", vehicleID=" + vehicleID + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", locationTime=" + locationTime + ", city=" + city + ", state=" + state
				+ ", country=" + country + ", locationID=" + locationID + "]";
	}

	/*@Override
	public String toString() {
		return "LocationInfo [userID=" + userID + ", vehicleID=" + vehicleID + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", locationTime=" + locationTime + ", city=" + city + ", state=" + state
				+ ", country=" + country + "]";
	}*/

	

	
}
