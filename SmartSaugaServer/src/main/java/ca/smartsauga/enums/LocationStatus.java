package ca.smartsauga.enums;

public enum LocationStatus {
	
	PROPOSED, PENDINGAPPROVAL, APPROVED, INVALIDIMAGE, DENIED, REMOVED, BLOCKED, CITYAPPROVED;
	
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

}
