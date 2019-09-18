package ca.smartsauga.beans;


import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CitizenUser extends User {
	
	
	//mm-dd-yyyy
	@NotNull(message="Birthdate cannot be null")
	@Pattern(regexp="^(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])-[0-9]{4}$",message="birthday format should be mnth date year")
	private String userBirthdate;
	
	
	private String status;
	public CitizenUser(String firstname, String lastname, String email, String password, String userBirthdate, String status, String type) {
		super(email, password, firstname, lastname, type);
		// TODO Auto-generated constructor stub
//		this.address = address;
		this.userBirthdate = userBirthdate;
		this.status = status;
	}
	
	
	
}
