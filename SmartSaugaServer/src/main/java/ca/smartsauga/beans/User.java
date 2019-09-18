
package ca.smartsauga.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	
	
	@NotNull(message="Name cannot be empty")
	@Column(length = 20)
	private String firstname;
	
	@NotNull(message="Name cannot be empty")
	@Column(length = 20)
	private String lastname;
	
	@NotNull(message="Email cannot be empty")
	@Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message="Email must be valid")
	private String email;
	
	@NotNull(message="Password cannot be null")
	@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,8}$", message="Invalid password")
	private String password;
	
	@NotNull(message="Type cannot be null")
	private String type;
	
	
	
	public User(String email, String password, String firstname, String lastname, String type) {
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.type = type;
		
	}
	

}
