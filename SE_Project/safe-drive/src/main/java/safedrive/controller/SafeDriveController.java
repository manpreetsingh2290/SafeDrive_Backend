package safedrive.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

import safedrive.beans.UserDetails;
import safedrive.beans.LocationInfo;
import safedrive.beans.LocationInfoResponse;
import safedrive.services.LocationService;
import safedrive.services.SignUpService;



@RestController
public class SafeDriveController {

	private final String  ROOT="";
	
	@Autowired
	SignUpService signUpService;
	
	@Autowired
	LocationService locationService;

	
	//Post request
	
	  @RequestMapping(method=RequestMethod.POST, value="/signUp") 
	  public String  signUp(@RequestBody UserDetails userDetail) 
	  { 
		  System.out.println(userDetail);
	  
		  return signUpService.saveUserDetails(userDetail); 
	 }
	 
	/*@RequestMapping(method=RequestMethod.POST, value="/signUp")
	public String signUp(@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,
			@RequestParam("mobileNumber") String mobileNumber,
			@RequestParam("vehicleType") String vehicleType,
			@RequestParam("emailID") String emailID,
			@RequestParam("vehicleType") String password)
	{
		System.out.println(firstName);
		UserDetails userDetail= new UserDetails(null, firstName, lastName, mobileNumber, vehicleType, emailID, password);
		
		return signUpService.saveUserDetails(userDetail);
	}
	*/
	
	
		@RequestMapping(method=RequestMethod.POST, value=ROOT+"/login")
		public UserDetails login(@RequestBody UserDetails userDetail)
		{
			System.out.println(userDetail);
			
			return signUpService.checkLogin(userDetail);
		}
	
	  @RequestMapping(ROOT+"/signUp") 
	  public UserDetails signUp() { 
		  return	signUpService.getUserDetails(); 
		  }
	  
	  	//post request for LocationInfo
		@RequestMapping(method=RequestMethod.POST, value="/LocationInfo")
		public  LocationInfoResponse LocationInfo(@RequestBody LocationInfo locationInfo)
		{		
			System.out.println(locationInfo);
			return locationService.saveLocationInfo(locationInfo);
		}
	 
}
