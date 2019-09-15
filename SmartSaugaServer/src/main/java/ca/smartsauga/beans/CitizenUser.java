package ca.smartsauga.beans;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CitizenUser extends User {
	
	
//	private LocalDate joinDate;
//	private LocalDate lastLoggedDate;
//	private String address;
	private String userBirthdate;
	private String status;
	public CitizenUser(String name, String email, String password, String userBirthdate, String status, String type) {
		super(email, password, name, type);
		// TODO Auto-generated constructor stub
//		this.address = address;
		this.userBirthdate = userBirthdate;
		this.status = status;
	}
	
	
	
}
