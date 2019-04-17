package ca.smartsauga.utilities;

import java.util.ArrayList;
import java.util.List;

import ca.smartsauga.beans.Locations;

public class LocationList {
	
	List<Locations> locationList = new ArrayList<Locations>();
	
	public List<Locations> locations() {
		//name,longitude,latitude
		locationList.add(new Locations("Lorne Park Library",-79.623770, 43.532840)); //done
        locationList.add(new Locations("Benares Estate Visitor Centre",-79.637180,43.527530));
        locationList.add(new Locations("Bradley Museum-Heritage",-79.611480,43.512060));
        locationList.add(new Locations("Braeben Golf Course Academy",-79.695420,43.602560));
        locationList.add(new Locations("Burnhamthorpe Community Centre & Arena",-79.599159,43.623268));
        locationList.add(new Locations("Burnhamthorpe Library & Maja Prentice Theatre",-79.603075,43.621132));
        locationList.add(new Locations("Cawthra Community Centre & Arena",-79.576730,43.578230));
        locationList.add(new Locations("Chappell Estate House-Heritage",-79.673720,43.565630));
        locationList.add(new Locations("Churchill Meadows Branch Library & Activity Centre",-79.730810, 43.563350));
        locationList.add(new Locations("Clarkson Community Centre Library & Arena",-79.640877,43.519218));
		
		return locationList;
	}

}
