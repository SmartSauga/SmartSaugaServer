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
	private String userStatus;
	private LocalDate joinDate;
	private LocalDate lastLoggedDate;
	
	public CitizenUser(int userId, String email, String imageFileName, 
			String userStatus, LocalDate joinDate, LocalDate lastLoggedDate) {
		super(userId, email, imageFileName);
		this.userStatus = userStatus;
		this.joinDate = joinDate;
		this.lastLoggedDate = lastLoggedDate;
		
	}
	
	

}
