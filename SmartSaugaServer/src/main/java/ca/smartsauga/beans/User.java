package ca.smartsauga.beans;
import java.time.LocalDate;

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
	
	@NotNull(message="Name cannot be null")
	@Column(length = 80)
	@Pattern(regexp="^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$", message="Email must be valid")
	private String email;
	
	private String imageFileName;
	
	public User(String email, String imageFileName) {
		this.email = email;
		this.imageFileName = imageFileName;
	}
	public User(String email) {
		this.email = email;
	}
	

}
