package ca.smartsauga.beans;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
	
	@Column(name="location_rating")
	private double locUserRating;
	
	@Column(name="wifi_rating")
	private double wifiRating;
	
	@Column(name="category", nullable=false)
	private String category;
	

	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	private double latitude;
	
	@Column(name="location_status")
	private String status;
	
	@Column(name="internet_speed", nullable=false)
	private double internetSpeed;
	//
	public Locations(String name, String address, double longitude, double latitude, double locUserRating, double wifiRating,
			String category, String status, double internetSpeed) {
		this.name = name;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		this.locUserRating = locUserRating;
		this.wifiRating  = wifiRating;
		this.category = category;
		this.status = status;
		
	}

	
	
	
	
	
		
}
