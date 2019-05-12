package ca.smartsauga.enums;

public enum LocationType {
	
	COMMUNITYCENTER, LIBRARY, BUSSTOP, COURTHOUSE, CITYHALL, RESTAURANT, MALL, OTHER;
	
	public static LocationType toType(String type) {
		
		switch(type) {
		
		case "COMMUNITYCENTER":
			return LocationType.COMMUNITYCENTER;
		case "LIBRARY":
			return LocationType.LIBRARY;
		case "BUSSTOP":
			return LocationType.BUSSTOP;
		case "COURTHOUSE":
			return LocationType.COURTHOUSE;
		case "CITYHALL":
			return LocationType.CITYHALL;
		case "RESTAURANT":
			return LocationType.RESTAURANT;
		case "MALL":
			return LocationType.MALL;
		}
		return LocationType.OTHER;
	}

}
