package ca.smartsauga.utilities;

import java.util.ArrayList;
import java.util.List;

import ca.smartsauga.beans.Locations;

public class LocationList {
	
	List<Locations> locationList = new ArrayList<Locations>();
	
	public List<Locations> locations() {
		
		locationList.add(new Locations("Lorne Park Library",43.53, 79.62));
		locationList.add(new Locations("Benares Estate Visitor Centre",610201.019,4820363.828));
		locationList.add(new Locations("Bradley Museum-Heritage",612235.291,4818614.671));
		locationList.add(new Locations("Braeben Golf Course Academy",605813.283,4828050.344));
		locationList.add(new Locations("Burnhamthorpe Community Centre & Arena",613046.276,4830922.18));
		locationList.add(new Locations("Burnhamthorpe Library & Maja Prentice Theatre",612735.325,4830758.172));
		locationList.add(new Locations("Cawthra Community Centre & Arena",614909.956,4826028.942));
		locationList.add(new Locations("Chappell Estate House-Heritage",607061.405,4824415.642));
		locationList.add(new Locations("Churchill Meadows Branch Library & Activity Centre",601230.744,4822968.011));
		locationList.add(new Locations("Clarkson Community Centre Library & Arena",609063.726,4818487.493));
		
		return locationList;
	}

}
