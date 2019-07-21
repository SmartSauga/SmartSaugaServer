package ca.smartsauga.beans;
import lombok.*;
import javax.persistence.*;

import ca.smartsauga.enums.LocationStatus;
import ca.smartsauga.enums.LocationType;
@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Locations {
	@Id @GeneratedValue
	private int locationId;
	
	@Column(name="location_name", nullable=false, length=100)
	private String name;
	
	@Column(name="location_address", nullable=false, length=150)
	private String address;
	
	private int locUserRating;
	
	private int locNumRaters;
	
	private int wifiRating;
	
	private LocationType type;
	
	private String locationImageFile;
	
	
	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	private double latitude;
	
	//@Column(name="message_strings",nullable=true,length=150)
	//private String[] messageStrings;
	/*
	private int ratings;
	
	@Column(nullable=true)
	private String image;
	
	private String category;
	
	private int avgWifiRating;
	*/
	public Locations(String name, String address, double longitude, double latitude, int locUserRating, int wifiRating,
			LocationType locType, String fileImage) {
		this.name = name;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locUserRating = locUserRating;
		this.wifiRating  = wifiRating;
		this.type = locType;
		this.locationImageFile = fileImage;
		this.locNumRaters = 0;
	}

	public Locations(String name, double longitude, double latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
	
	
		
}
