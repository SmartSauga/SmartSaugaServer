package ca.smartsauga.beans;

import javax.persistence.Entity;

import ca.smartsauga.enums.LocationStatus;
import ca.smartsauga.enums.LocationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

public class CorporateLocation extends Locations {
	
	public String password;
	
	public LocationStatus locStatus;


	public CorporateLocation(String name, String address, double longitude, double latitude, int locUserRating,
			int wifiRating, LocationType locType, String fileImage, String password, LocationStatus locStatus) {
		super(name, address, longitude, latitude, locUserRating, wifiRating, locType, fileImage);
		this.password = password;
		this.locStatus = locStatus;
		// TODO Auto-generated constructor stub
	}

	
	
	

}
