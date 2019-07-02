package safedrive.services;

import java.util.List;

import org.springframework.stereotype.Service;

import safedrive.beans.UserDetails;
import safedrive.databaseconnection.AccessDatabase;


@Service
public class SignUpService {

	public UserDetails getUserDetails()
	{
		UserDetails obj = new UserDetails("100002", "Manpreet", "Singh", "99999999", "Car", "manpreetsingh2290@gmail.com", "qwerty");
		return obj;
	}
	
	public String saveUserDetails(UserDetails userDetail)
	{
		AccessDatabase obj = new AccessDatabase();
		int val=obj.saveSignUpData(userDetail);
		if(val>0)
		{
			return "True";
		}
		else
		{
			return "False";
		}
	}
	
	public UserDetails checkLogin(UserDetails userDetail)
	{
		AccessDatabase obj = new AccessDatabase();
		UserDetails userDetails=obj.checkLogin(userDetail.getEmailID(), userDetail.getPassword());
		return userDetails;
	}
	
}
