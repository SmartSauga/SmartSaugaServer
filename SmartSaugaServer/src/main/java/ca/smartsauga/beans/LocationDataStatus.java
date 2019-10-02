package ca.smartsauga.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class LocationDataStatus  {
	
	@Id
	private int locationId;
	
	@Column(name="location_name", nullable=false, length=100)
	private String name;
	
	@Column(name="location_address", nullable=false, length=150)
	private String address;
	
	
	private String ipAddress;
	
	private String macAddress;
	
	private String description;
	
	private String downloadSpeed;
	
	private String uploadSpeed;
	
	private String ping;
	
	private String status;
	
	
	
}
