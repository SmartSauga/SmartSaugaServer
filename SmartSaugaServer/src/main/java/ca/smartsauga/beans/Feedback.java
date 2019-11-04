package ca.smartsauga.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class Feedback {
	
	@Id @GeneratedValue
	private int id;
	private String feedbackComment;
	int rating;
	
//	@OneToOne
//	private CitizenUser citizenUser;
	public Feedback(String feedbackComment, int rating) {
		this.feedbackComment = feedbackComment;
		this.rating = rating;
	}
	
	
	
}
