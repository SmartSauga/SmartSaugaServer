package ca.smartsauga.enums;

public enum LocationStatus {
	
	PROPOSED("PROPOSED"), 
	PENDINGAPPROVAL("PENDING APPROVAL"), 
	APPROVED("APPROVED"), 
	INVALIDIMAGE("INVALID IMAGE"), 
	DENIED("DENIED"), 
	REMOVED("REMOVED"), 
	BLOCKED("BLOCKED"), 
	CITYAPPROVED("CITY APPROVED");
	
	private final String status;
	private LocationStatus(String status) {
		this.status = status;
	}
	public static LocationStatus toStatus(String status) {
		
		switch(status) {
		
		case "PROPOSED":
			return LocationStatus.PROPOSED;
		case "PENDINGAPPROVAL":
			return LocationStatus.PENDINGAPPROVAL;
		case "APPROVED":
			return LocationStatus.APPROVED;
		case "INVALIDIMAGE":
			return LocationStatus.INVALIDIMAGE;
		case "DENIED":
			return LocationStatus.DENIED;
		case "REMOVED":
			return LocationStatus.REMOVED;
		case "BLOCKED":
			return LocationStatus.BLOCKED;
		case "CITYAPPROVED":
			return LocationStatus.CITYAPPROVED;
		}
		return LocationStatus.PROPOSED;
	}
	
	public static String getStatus(LocationStatus status) {
		
		switch(status) {
		
		case PROPOSED:
			return "PROPOSED";
		case PENDINGAPPROVAL:
			return "PENDING APPROVAl";
		case APPROVED:
			return"APPROVED";
		case INVALIDIMAGE:
			return "INVALID IMAGE";
		case DENIED:
			return "DENIED";
		case REMOVED:
			return "REMOVED";
		case BLOCKED:
			return "BLOCKED";
		case CITYAPPROVED:
			return "CITY APPROVED";
		}
		return "PROPOSED";
	}
}
