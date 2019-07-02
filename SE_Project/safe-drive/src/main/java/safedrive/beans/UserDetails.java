package safedrive.beans;

public class UserDetails {

	private String userID;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String vehicleType;
	private String emailID;
	private String password;
	
	public UserDetails()
	{
		
	}
	
	public UserDetails(String userID, String firstName, String lastName, String mobileNumber, String vehicleType,
			String emailID, String password) {
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.vehicleType = vehicleType;
		this.emailID = emailID;
		this.password = password;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDetails [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", vehicleType=" + vehicleType + ", emailID=" + emailID
				+ ", password=" + password + "]";
	}
	
	
	
}
