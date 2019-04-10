package ca.smartsauga.beans;
import lombok.*;
import javax.persistence.*;
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
	
	@Column(nullable=false)
	private double longitude;
	
	@Column(nullable=false)
	private double latitude;
	
	//@Column(name="message_strings",nullable=true,length=150)
	//private String[] messageStrings;
	
	private int ratings;
	
	@Column(nullable=true)
	private String image;
	
	private String category;
	
	private int avgWifiRating;
	/*
	public Locations(String name, String address, String[] messageStrings, int ratings, String image, String category, int avgWifiRating ) {
		this.name = name;
		this.address = address;
		this.messageStrings = messageStrings;
		this.ratings = ratings;
		this.image = image;
		this.category = category;
		this.avgWifiRating = avgWifiRating;
	}
	*/

	public Locations(String name, double longitude, double latitude) {
		this.name = name;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	
		
}
