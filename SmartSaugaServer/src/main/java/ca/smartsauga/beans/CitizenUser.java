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
	
	public CitizenUser(String email, String imageFileName) {
		super(email, imageFileName);
		this.joinDate = LocalDate.now();
		this.lastLoggedDate = LocalDate.now();
		
	}
	
	public CitizenUser(String email) {
		super(email);
		this.joinDate = LocalDate.now();
		this.lastLoggedDate = LocalDate.now();
		
	}
	
	

}
