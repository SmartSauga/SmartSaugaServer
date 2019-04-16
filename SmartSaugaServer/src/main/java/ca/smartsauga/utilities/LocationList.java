package ca.smartsauga.utilities;

import java.util.ArrayList;
import java.util.List;

import ca.smartsauga.beans.Locations;

public class LocationList {
	
	List<Locations> locationList = new ArrayList<Locations>();
	
	public List<Locations> locations() {
		
		locationList.add(new Locations("Lorne Park Library",43.53, 79.62));
        locationList.add(new Locations("Benares Estate Visitor Centre",43.5272,79.6373));
        locationList.add(new Locations("Bradley Museum-Heritage",43.5121,79.6114));
        locationList.add(new Locations("Braeben Golf Course Academy",43.6020,79.6952));
        locationList.add(new Locations("Burnhamthorpe Community Centre & Arena",43.6227,79.5987));
        locationList.add(new Locations("Burnhamthorpe Library & Maja Prentice Theatre",43.6210,79.6030));
        locationList.add(new Locations("Cawthra Community Centre & Arena",43.5781,79.5768));
        locationList.add(new Locations("Chappell Estate House-Heritage",43.5651,79.6742));
        locationList.add(new Locations("Churchill Meadows Branch Library & Activity Centre",43.5529, 79.7467));
        locationList.add(new Locations("Clarkson Community Centre Library & Arena",43.5117,79.6506));
		
		return locationList;
	}

}
