package safedrive.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import safedrive.beans.LocationInfo;
import safedrive.beans.LocationInfoResponse;
import safedrive.databaseconnection.AccessDatabase;

@Service
public class LocationService {
	public  LocationInfoResponse saveLocationInfo(LocationInfo locationInfo)
	{
		AccessDatabase obj = new AccessDatabase();
		/*ArrayList<LocationInfo> data =obj.saveLocationInfo(locationInfo);
		
		data= filterNearByVehicles(locationInfo, data);
		
		LocationInfoResponse locData= new LocationInfoResponse();
		locData.setLocationInfoList(data);
		return locData;
		*/
		
		LocationInfoResponse locData= new LocationInfoResponse();
		locData.setLocationInfoList(getStaticLocation(locationInfo.getLocationID()));
		
		return locData;
		
	}
	
	public ArrayList<LocationInfo> filterNearByVehicles(LocationInfo currentVehileLoc,ArrayList<LocationInfo> nearByLocVehicles)
	{
		ArrayList<LocationInfo> newList = new ArrayList<LocationInfo>();
		double distance=0;
		double DISTANCE_LIMIT=15;//meters
		for(LocationInfo obj:nearByLocVehicles)
		{
			//Remove same vehicle's data
			if(obj.getUserID() == currentVehileLoc.getUserID() && obj.getVehicleID().equalsIgnoreCase(currentVehileLoc.getVehicleID()))
			{
				continue;
			}
			distance= distance(currentVehileLoc.getLatitude(), currentVehileLoc.getLongitude(), obj.getLatitude(), obj.getLongitude());
			if(distance<=DISTANCE_LIMIT)
			{
				newList.add(obj);
			}
			
		}		
		System.out.println("Near by Vehicles:");
		System.out.println(newList);
		return newList;
	}
	
	//Reference https://stackoverflow.com
	public double distance(double lat1, double lon1, double lat2, double lon2) {
		 double p = Math.PI / 180;  
		 double a = 0.5 -  Math.cos((lat2 - lat1) * p)/2 + 
				  Math.cos(lat1 * p) *  Math.cos(lat2 * p) * 
		          (1 -  Math.cos((lon2 - lon1) * p))/2;

		  return 12742 * Math.asin(Math.sqrt(a));
		}
	
	
	/**
	 * Calculate distance between two points in latitude and longitude taking
	 * into account height difference. If you are not interested in height
	 * difference pass 0.0. Uses Haversine method as its base.
	 * 
	 * lat1, lon1 Start point lat2, lon2 End point el1 Start altitude in meters
	 * el2 End altitude in meters
	 * @returns Distance in Meters
	 */
	public static double distanceNew(double lat1, double lat2, double lon1,
	        double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	   // double height = el1 - el2;

	    distance = Math.pow(distance, 2);

	    return Math.sqrt(distance);
	}
	
	public ArrayList<LocationInfo> getStaticLocation(int i)
	{
		ArrayList<LocationInfo> staticLocations= new ArrayList<LocationInfo>();
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.304873, -83.063427, "", "Windsor", "Ontario", "Canada",0));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.304948, -83.063306, "", "Windsor", "Ontario", "Canada",1));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305028, -83.063065, "", "Windsor", "Ontario", "Canada",2));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305091, -83.062867, "", "Windsor", "Ontario", "Canada",3));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305174, -83.062684, "", "Windsor", "Ontario", "Canada",4));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.30521, -83.062464, "", "Windsor", "Ontario", "Canada",5));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305285, -83.062255, "", "Windsor", "Ontario", "Canada",6));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305377, -83.061987, "", "Windsor", "Ontario", "Canada",7));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305444, -83.061698, "", "Windsor", "Ontario", "Canada",8));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305563, -83.061371, "", "Windsor", "Ontario", "Canada",9));
		staticLocations.add(new LocationInfo(100000005, "Vehicle1", 42.305577, -83.061391, "", "Windsor", "Ontario", "Canada",10));
		
		ArrayList<LocationInfo> locationsList= new ArrayList<LocationInfo>();
		locationsList.add(staticLocations.get(i+1));
		
		System.out.println("Near by Vehicles:");
		System.out.println(locationsList);
		
		return locationsList;
	}
}
