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
	
	public static String locTypeToString(LocationType type) {
		
		switch(type) {
		
		case COMMUNITYCENTER:
			return "COMMUNITY CENTER";
		case LIBRARY:
			return "LIBRARY";
		case BUSSTOP:
			return "BUS STOP";
		case COURTHOUSE:
			return "COURTHOUSE";
		case CITYHALL:
			return "CITY HALL";
		case RESTAURANT:
			return "RESTAURANT";
		case MALL:
			return "MALL";
		}
		return "OTHER";
	}
	
	
}
