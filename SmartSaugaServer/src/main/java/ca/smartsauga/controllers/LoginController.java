package ca.smartsauga.controllers;
//Push Changes
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.CorporateLocation;
import ca.smartsauga.beans.Locations;
import ca.smartsauga.beans.User;
import ca.smartsauga.dao.Dao;
import ca.smartsauga.dao.LocationDao;
import ca.smartsauga.enums.LocationStatus;
import ca.smartsauga.enums.LocationType;
import ca.smartsauga.utilities.LocationList;
import ca.smartsauga.utilities.PasswordController;

@RestController
public class LoginController {
	
	Dao dao = new Dao();
	LocationDao locDao = new LocationDao();
	PasswordController passwordController = new PasswordController();
	
	@CrossOrigin
	@RequestMapping(value = "/registerUser/{email}/{password}", method = RequestMethod.POST)
	public CitizenUser registerUser(@PathVariable String email, @PathVariable String password) {
		
		if(!email.contentEquals(dao.getUnvalidatedUser(email))) {
			CitizenUser newUser = new CitizenUser();
			newUser.setEmail(email);
			newUser.setPassword(password);
			System.out.println("**************** " + newUser.getEmail() + " ***********************************");
			dao.registerUser(newUser);
			System.out.println(newUser.getEmail());
			return newUser;
		} else {
			return null;
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/updateProfile/{email}/{name}/{birthdate}/{address}/{status}/{type}", method = RequestMethod.POST)
	public CitizenUser updateProfile(@PathVariable String email, @PathVariable String name, @PathVariable String birthdate, @PathVariable String address, @PathVariable String Status) {
		
		CitizenUser user = dao.getValidatedUser(email);
		System.out.println(user.getEmail());
		user.setName(name);
		user.setUserBirthdate(birthdate);
		user.setAddress(address);
		user.setStatus("Verified");
//		user.setAbout(about);
//		user.setFacebookUrl(facebookurl);
//		user.setNotes(notes);
		
		return dao.updateProfile(user);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/genLocations", method = RequestMethod.GET)
	public int genLocations() {
		LocationList newLocations = new LocationList();
		List<Locations> locations = new ArrayList<Locations>();
		locations = newLocations.locations();
		for(Locations l : locations) {
			dao.addLocation(l);
		}
		
		return 1;
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/validateUser/{email}/{password}", method = RequestMethod.POST)
	public CitizenUser validateUser(@PathVariable String email, @PathVariable String password) {
		
		//TODO: We want to check the password passed to the DAO that gets the password without instantiating a variable
		//TODO: dao.validateUser will return a decrypted Password
		//If Password is correct, return a CitizenUser else return null, handle at front end
		//Return a profile
		if(passwordController.encrypt(password).contentEquals(dao.getValidatedUser(email).getPassword())) {
			System.out.println(dao.getValidatedUser(email).getEmail());
			return dao.getValidatedUser(email);
		} else {
			return null;
		}
	}
	//******************************
	//This is for a prototype demonstration of the power of the REST server
	//Will not be in final version
	@CrossOrigin
	@RequestMapping(value = "/validateUserGET/{email}/{password}", method = RequestMethod.GET)
	public CitizenUser validateUserGET(@PathVariable String email, @PathVariable String password) {
		
		//TODO: We want to check the password passed to the DAO that gets the password without instantiating a variable
		//TODO: dao.validateUser will return a decrypted Password
		//If Password is correct, return a CitizenUser else return null, handle at front end
		//Return a profile
		if(passwordController.encrypt(password).contentEquals(dao.getValidatedUser(email).getPassword())) {
			return dao.getValidatedUser(email);
		} else {
			return null;
		}
	}
	//**********************************
	
	@CrossOrigin
	@RequestMapping(value = "/GetLocations", method = RequestMethod.GET)
	public List<Locations> getLocations() {
		
		return dao.getAllLocations();
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/DeleteUser/{email}", method = RequestMethod.DELETE)
	public int deleteUser(@PathVariable String email) {
		
		CitizenUser delUser = dao.getValidatedUser(email);
		
		dao.deleteCitizenUser(delUser);
		return 0;
		
	}
	//1 successful and 0 unsuccessful
	@CrossOrigin
	@RequestMapping(value = "/ProposeLocation/{name}/{address}/{password}/{photo}/{wifiRating}/{locRating}/{category}/{comment}/{longitude}/{latitude}", 
	method = RequestMethod.POST)
	public int proposeLocation(@PathVariable String name, @PathVariable String password, @PathVariable String photo, @PathVariable String wifiRating,
			@PathVariable String locRating, @PathVariable String category, @PathVariable String comment, 
			@PathVariable String longitude, @PathVariable String latitude, @PathVariable String address) {
		try {
			double numLongitude = Double.parseDouble(longitude);
			double numLatitude = Double.parseDouble(latitude);
			int numLocRating = Integer.parseInt(locRating);
			int numWifiRating = Integer.parseInt(wifiRating);
			LocationType locType = LocationType.toType(category);
			LocationStatus status = LocationStatus.PENDINGAPPROVAL;
		
			Locations proposedLocation = new CorporateLocation(name, address, numLongitude, numLatitude, numLocRating, 
					numWifiRating, LocationType.locTypeToString(locType), photo, password, LocationStatus.getStatus(status) );
			int locAdded = locDao.addCorporateLocation(proposedLocation);
			if(locAdded == 1) {
				return 1;
			} else {
				return 0;
			}
		}catch(NumberFormatException e) {
			return 0;
		}
	
	}
	
	@CrossOrigin
	@RequestMapping(value = "/ADMINCreateLocation/{name}/{address}/{password}/{photo}/{wifiRating}/{locRating}/{category}/{comment}/{longitude}/{latitude}", 
	method = RequestMethod.POST)
	public int adminCreateLocation(@PathVariable String name, @PathVariable String password, @PathVariable String photo, @PathVariable String wifiRating,
			@PathVariable String locRating, @PathVariable String category, @PathVariable String comment, 
			@PathVariable String longitude, @PathVariable String latitude, @PathVariable String address) {
		try {
			double numLongitude = Double.parseDouble(longitude);
			double numLatitude = Double.parseDouble(latitude);
			int numLocRating = Integer.parseInt(locRating);
			int numWifiRating = Integer.parseInt(wifiRating);
			LocationType locType = LocationType.toType(category);
			
			CorporateLocation  createdLoc = new CorporateLocation(name, address, numLongitude, numLatitude, numLocRating, 
					numWifiRating, LocationType.locTypeToString(locType), photo, password, LocationStatus.getStatus(LocationStatus.CITYAPPROVED));
//			createdLoc.setLocStatus(LocationStatus.CITYAPPROVED);
			
			if(locDao.addCorporateLocation(createdLoc) == 1) {
				return 1;
			} else {
				return 0;
			}
		}catch(NumberFormatException e) {
			return 0;
		}
	
	}
	
	//Curate Location - Need Validation to ensure admin is sending this mapping
	@CrossOrigin
	@RequestMapping(value = "/ADMINCurateLocation/{id}/{status}", 
	method = RequestMethod.POST)
	public int adminCurateLocation(@PathVariable String id, @PathVariable String status) {
		
		LocationStatus thisStatus = LocationStatus.toStatus(status);
		locDao.curateLocation(id, thisStatus);
		return 0;
	
	}
	
	//Remove Location - Need Validation to ensure Admin is sending this mapping
		@CrossOrigin
		@RequestMapping(value = "/ADMINRemoveLocation/{id}/{status}", 
		method = RequestMethod.POST)
		public int adminRemoveLocation(@PathVariable String id) {
			
			locDao.removeLocation(Integer.parseInt(id));
			return 0;
		
		}
	//Rate a Location
		@CrossOrigin
		@RequestMapping(value = "/RateLocation/{id}/{rating}", 
		method = RequestMethod.POST)
		public int rateLocation(@PathVariable String id, @PathVariable String rating) {
			
			
			locDao.rateLocation(Integer.parseInt(id), Integer.parseInt(rating));
			return 0;
		
		}
	
	@CrossOrigin
	@RequestMapping(value = "/ChangeLocInfo/{locid}/{name}/{address}/{password}/{photo}/{wifiRating}/{locRating}/{category}/{comment}/{longitude}/{latitude}/{status}", 
	method = RequestMethod.POST)
	public int changeLocationInformation(@PathVariable int locid, @PathVariable String name, @PathVariable String password, @PathVariable String photo, @PathVariable String wifiRating,
			@PathVariable String locRating, @PathVariable String category, @PathVariable String comment, 
			@PathVariable String longitude, @PathVariable String latitude, @PathVariable String address, @PathVariable String status) {
		try {
			double numLongitude = Double.parseDouble(longitude);
			double numLatitude = Double.parseDouble(latitude);
			int numLocRating = Integer.parseInt(locRating);
			int numWifiRating = Integer.parseInt(wifiRating);
			LocationType locType = LocationType.toType(category);
			LocationStatus locStatus = LocationStatus.toStatus(status);
			
			
			CorporateLocation changeLocInfo = new CorporateLocation(name, address, numLongitude, numLatitude, numLocRating, 
					numWifiRating,LocationType.locTypeToString(locType), photo, password, LocationStatus.getStatus(locStatus));
			changeLocInfo.setLocationId(locid);
			if(locDao.changeLocInfo(changeLocInfo) == 1) {
				return 1;
			} else {
				return 0;
			}
		}catch(NumberFormatException e) {
			return 1;
		}
	
	}
	
	
}
