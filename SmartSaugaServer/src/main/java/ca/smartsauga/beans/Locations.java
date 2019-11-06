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
public class Locations  {
	@Id @GeneratedValue
	private int locationId;
	
	@Column(name="location_name", nullable=false, length=100)
	private String name;
	
	@Column(name="location_address", nullable=false, length=150)
	private String address;
	
	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	private double latitude;
		
	@Column(name="category", nullable=false)
	private String category;
	

	public Locations(String name, String address, double longitude, double latitude,String category) {
		this.name = name;
		this.address = address;
		this.longitude = longitude;
		this.latitude = latitude;
		
		this.category = category;
		
		
	}

	
	
	
	
	
		
}
