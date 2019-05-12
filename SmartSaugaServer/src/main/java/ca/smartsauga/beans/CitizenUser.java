package ca.smartsauga.beans;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CitizenUser extends User {
	
	//Needs Status Enum eventually
	private LocalDate joinDate;
	private LocalDate lastLoggedDate;
	
	private String name;
	private String streetAddress;
	private String cityName;
	private String province;
	private LocalDate userBirthdate;
	private String email;
	
	public CitizenUser(String email, String password) {
		super(email, password);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
