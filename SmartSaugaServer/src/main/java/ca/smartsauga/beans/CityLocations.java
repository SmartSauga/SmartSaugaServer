package ca.smartsauga.beans;

import java.util.ArrayList;

import javax.persistence.Entity;

import ca.smartsauga.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CityLocations extends Locations {
	
	public String maintenanceMessage;
	private boolean status;
	private ArrayList<Integer> uniqueUsers; 
	private int userCount;
	
	public CityLocations(int locationId, String name, String address, int locUserRating, int locNumRaters, int wifiRating,
			String type, String locationImageFile, double longitude, double latitude) {
		
		super(locationId, name, address, locUserRating, locNumRaters, wifiRating, type, locationImageFile, longitude, latitude);
		this.status = true;
		this.uniqueUsers = new ArrayList<Integer>();
		this.userCount = 0;
	}

}
