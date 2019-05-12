
package ca.smartsauga.beans;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import ca.smartsauga.enums.UserStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	
	@NotNull(message="Name cannot be null")
	@Column(length = 80)
	@Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message="Email must be valid")
	private String email;
	
	private String imageFileName;
	
	@NotNull(message="Password cannot be null")
	@Size(min=8)
	private String password;
	
	@NotNull(message="User must have a status")
	UserStatus status;
	
	//Prototype Data - To Be Deleted ************************
	private String name;
	private String birthdate;
	private String about;
	private String facebookUrl;
	private String notes;
	//********************************************************
	
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.status = UserStatus.UNVERIFIED;
	}
	

}
