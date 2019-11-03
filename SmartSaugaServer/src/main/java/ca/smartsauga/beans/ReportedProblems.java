package ca.smartsauga.beans;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
public class ReportedProblems {

	@Id @GeneratedValue
	private int id;
	
	@Column(name="location_name", nullable=false, length=100)
	private String locationName;
	
	@Column(name="problem_type", nullable=false, length=100)
	private String problemType;
	
	@Column(nullable=false, length=100)
	private String severity;
	
	@Column(name="problem_description", nullable=false, length=100)
	private String problemDescription;
	
	@Column(name="problem_status", nullable=false, length=100)
	private String problemStatus;
	
	public ReportedProblems(String locationName, String problemType, String severity, String problemDescription, String problemStatus) {
		this.locationName = locationName;
		this.problemType = problemType;
		this.severity = severity;
		this.problemDescription = problemDescription;
		this.problemStatus = problemStatus;
	}
	
}
