package safedrive.databaseconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import safedrive.beans.LocationInfo;
import safedrive.beans.UserDetails;



public class AccessDatabase {

	
	public int  saveSignUpData(UserDetails userDetail)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		int status=-1;
		try {
			int userId=getNewUserId();
			PreparedStatement statement=con.prepareStatement("INSERT INTO "
					+ "user_details (user_id,first_name,last_name,email_id,default_vehicle_type,password,phone_number)" 
					+"Values(?,?,?,?,?,?,?)");
			statement.setInt(1,userId);//1 specifies the first parameter in the query  
			statement.setString(2,userDetail.getFirstName());
			statement.setString(3,userDetail.getLastName());			
			statement.setString(4,userDetail.getEmailID());
			statement.setString(5,userDetail.getVehicleType());
			statement.setString(6,userDetail.getPassword());
			statement.setString(7,userDetail.getMobileNumber());						
			status=statement.executeUpdate();
			statement.close();
			con.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return status;
	}
	
	public int getNewUserId()
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		int userId=-1; 
		try {
			PreparedStatement statement=con.prepareStatement("select max(user_id) from user_details");
								
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				userId=rs.getInt(1)+1;
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userId;
	}
	
	public UserDetails checkLogin(String emailID,String password)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		UserDetails userInfo= new UserDetails();
		String userID;
		String firstName;
		String lastName;
		String mobileNumber;
		String vehicleType;
		Connection con=dbcon.getDatabaseConnection();
		try {
			PreparedStatement statement=con.prepareStatement("select user_id,first_name,last_name,email_id,"
					+ "default_vehicle_type,"
					+ "password,phone_number "
					+ "from user_details where email_id=? and password=?");
			statement.setString(1,emailID);
			statement.setString(2,password);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				userID=rs.getString("user_id");
				firstName=rs.getString("first_name");
				lastName=rs.getString("last_name");
				mobileNumber=rs.getString("phone_number");
				vehicleType=rs.getString("default_vehicle_type");
				emailID=rs.getString("email_id");
				
				userInfo= new UserDetails(userID,firstName, lastName, mobileNumber,vehicleType,emailID, password);
				
				
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return userInfo;
	}
	
	public ArrayList<LocationInfo>  saveLocationInfo(LocationInfo locationInfo)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		ArrayList<LocationInfo> data= new ArrayList<LocationInfo>();
		int status=-1;
		try {
			//int userId=getNewUserId();
			
			int regionID= saveRegionInfo(locationInfo.getCity(), locationInfo.getState(), locationInfo.getCountry());
			
			locationInfo.setLocationTime(null);
			PreparedStatement statement=con.prepareStatement("INSERT INTO "
					+ "Location_Info (user_id,Vehicle_Id,Longitude,Latitude,region_id)" 
					+"Values(?,?,?,?,?)");
			statement.setInt(1,locationInfo.getUserID());
			statement.setString(2,locationInfo.getVehicleID());		
			statement.setDouble(3,locationInfo.getLongitude());
			statement.setDouble(4,locationInfo.getLatitude());	
			statement.setDouble(5,regionID);	
			//statement.setString(5,locationInfo.getLocationTime());					
			status=statement.executeUpdate();
			statement.close();
			con.close();
			
			data= getSameTimeStampVehicles(String.valueOf(locationInfo.getUserID()),locationInfo.getVehicleID(),regionID);
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return data;
	
	}
	
	public ArrayList<LocationInfo> getSameTimeStampVehicles(String user_ID, String vehicle_ID, int regionID)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		ArrayList<LocationInfo> data= new ArrayList<LocationInfo>();
		LocationInfo obj=null;
		Connection con=dbcon.getDatabaseConnection();
		int userID;
		String vehicleID;
		double longitude;
		double latitude;
		String locationTime;
		try {
			String timeStamp = getLatestTimeStamp(user_ID, vehicle_ID);
			Statement statement=con.createStatement();
			int minutesDiff=50;
			String sqlQuery= "select User_Id,Vehicle_Id,Longitude,Latitude,"
					+ "to_char(Location_Time,'DD-MM-YY HH:MI:SS:FF AM') Location_Time  from Location_Info "
					+ "where  (User_Id,Vehicle_Id,Location_Time) in "
					+ "(select User_Id,Vehicle_Id,max(Location_Time) loc_time  "
					+ "from Location_Info where region_id='"+regionID+"' and "
					+ "((location_time between to_timestamp('"+timeStamp+"','DD-MM-YY HH:MI:SS:FF AM') "
							+ "and to_timestamp('"+timeStamp+"','DD-MM-YY HH:MI:SS:FF AM') + interval '"+minutesDiff+"' minute) OR "
									+ "(location_time between ((to_timestamp('"+timeStamp+"','DD-MM-YY HH:MI:SS:FF AM') - interval '"+minutesDiff+"' minute)) "
											+ "AND to_timestamp('"+timeStamp+"','DD-MM-YY HH:MI:SS:FF AM'))) "
									+ "group by  User_Id,Vehicle_Id)";
			
			ResultSet rs=statement.executeQuery(sqlQuery);
			//System.out.println(sqlQuery);
			while(rs.next())
			{
				obj= new LocationInfo();
				
				userID =rs.getInt("User_Id");
				vehicleID =rs.getString("Vehicle_Id");
				longitude =rs.getDouble("Longitude");
				latitude  =rs.getDouble("Latitude");
				locationTime =rs.getString("Location_Time");
								
				obj.setLatitude(latitude);
				obj.setLocationTime(locationTime);
				obj.setLongitude(longitude);
				obj.setUserID(userID);
				obj.setVehicleID(vehicleID);
				data.add(obj);
				
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return data;
	}
	
	public String getLatestTimeStamp(String userID,String vehicleID)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		String locationTime="";
		try {
			PreparedStatement statement=con.prepareStatement("select to_char(Location_Time,'DD-MM-YY HH:MI:SS:FF AM')  from Location_Info where user_Id=? and vehicle_id=? order by Location_Time desc");
			statement.setString(1,userID);
			statement.setString(2,vehicleID);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				locationTime=rs.getString(1);
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return locationTime;
	}
	
	public int saveRegionInfo(String city,String state, String country)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		int regionID=-1;
		try {
			
			regionID= getRegionID(city, state, country);
			if(regionID==-1)
			{
				regionID=getNewRegionId();
				PreparedStatement statement=con.prepareStatement("INSERT INTO region_details (region_id,city_name,state_name,country_name) " 
						+"Values(?,?,?,?)");
				statement.setInt(1,regionID); 
				statement.setString(2,city); 
				statement.setString(3,state); 
				statement.setString(4,country); 						
				int status=statement.executeUpdate();
				statement.close();
				con.close();			
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		return regionID;
	}
	public int getRegionID(String city,String state, String country)
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		int region_id=-1;
		try {
			if(city==null) city="";
			if(state==null) state="";
			if(country==null) country="";
			PreparedStatement statement=con.prepareStatement("select region_id  from region_details where city_name=? and state_name=? and country_name=?");
			statement.setString(1,city);
			statement.setString(2,state);
			statement.setString(3,country);
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				region_id=rs.getInt(1);
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return region_id;
	}
	
	public int getNewRegionId()
	{
		DataBaseConnection dbcon = new DataBaseConnection();
		Connection con=dbcon.getDatabaseConnection();
		int regionID=-1; 
		try {
			PreparedStatement statement=con.prepareStatement("select max(region_id) from region_details");
								
			ResultSet rs=statement.executeQuery();
			if(rs.next())
			{
				regionID=rs.getInt(1)+1;
			}
			statement.close();
			con.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return regionID;
	}
}
