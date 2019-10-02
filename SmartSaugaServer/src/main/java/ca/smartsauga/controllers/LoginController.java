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
import ca.smartsauga.beans.LocationDataStatus;
import ca.smartsauga.beans.Locations;
import ca.smartsauga.beans.User;
import ca.smartsauga.dao.Dao;
import ca.smartsauga.dao.LocationDao;
import ca.smartsauga.enums.LocationStatus;
import ca.smartsauga.enums.LocationType;

import ca.smartsauga.utilities.PasswordController;

@RestController
public class LoginController {
	
	Dao dao = new Dao();
	LocationDao locDao = new LocationDao();
	PasswordController passwordController = new PasswordController();
	
	@CrossOrigin
	@RequestMapping(value = "/registerUser/{email}/{password}/{firstname}/{lastname}/{status}/{type}/{birthday}", method = RequestMethod.POST)
	public int registerCitizenUser(@PathVariable String email, @PathVariable String password, @PathVariable String firstname,@PathVariable String lastname, @PathVariable String status, @PathVariable String type, @PathVariable String birthday) {

		if(!email.contentEquals(dao.getUnvalidatedUser(email))) {
			User newUser = new CitizenUser(firstname,lastname,email,password,birthday,status,type);
			newUser.setEmail(email);
			newUser.setPassword(password);
			System.out.println("**************** " + newUser.getEmail() + " ***********************************");
			dao.registerCitizenUser(newUser);
			System.out.println(newUser.getEmail());
			return 1;
		} else {
			return 0;
		}
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/updateProfile/{email}/{firstname}/{lastname}/{birthdate}", method = RequestMethod.POST)
	public CitizenUser updateProfile(@PathVariable String email, @PathVariable String firstname, @PathVariable String lastname, @PathVariable String birthdate) {
		
		CitizenUser user = dao.getValidatedUser(email);
		System.out.println(user.getEmail());
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setUserBirthdate(birthdate);
	
		
		return dao.updateUserProfile(user);
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/genLocations", method = RequestMethod.GET)
	public int genLocations() {
//		LocationList newLocations = new LocationList();
		List<Locations> locations = new ArrayList<Locations>();
//		locations = newLocations.locations();
		for(Locations l : locations) {
			dao.addLocation(l);
		}
		
		return 1;
		
	}
	
	@CrossOrigin
	@RequestMapping(value = "/validateUser/{email}/{password}", method = RequestMethod.POST)
	public boolean validateUser(@PathVariable String email, @PathVariable String password) {
		if(dao.getValidatedUser(email) != null) {
			if(passwordController.encrypt(password).contentEquals(dao.getValidatedUser(email).getPassword())) {
				System.out.println(dao.getValidatedUser(email).getEmail());
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/returnValidateUser/{email}", method = RequestMethod.POST)
	public CitizenUser getValidatedUserForFront(@PathVariable String email) {
		CitizenUser user = dao.getValidatedUser(email);
		return user;
		
	}
		
	//******************************
	//This is for a prototype demonstration of the power of the REST server
	//Will not be in final version
//	@CrossOrigin
//	@RequestMapping(value = "/validateUserGET/{email}/{password}", method = RequestMethod.GET)
//	public CitizenUser validateUserGET(@PathVariable String email, @PathVariable String password) {
//		
//		//TODO: We want to check the password passed to the DAO that gets the password without instantiating a variable
//		//TODO: dao.validateUser will return a decrypted Password
//		//If Password is correct, return a CitizenUser else return null, handle at front end
//		//Return a profile
//		if(dao.getValidatedUser(email)email == null) {
//			return null;
//		}
//		else if(passwordController.encrypt(password).contentEquals(dao.getValidatedUser(email).getPassword())) {
//			return dao.getValidatedUser(email);
//		} else {
//			return null;
//		}
//	}
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
//	//1 successful and 0 unsuccessful
//	@CrossOrigin
//	@RequestMapping(value = "/ProposeLocation/{name}/{address}/{wifiRating}/{locRating}/{category}/{longitude}/{latitude}/{speed}", 
//	method = RequestMethod.POST)
//	public int proposeLocation(@PathVariable String name,@PathVariable String address, @PathVariable String wifiRating,
//			@PathVariable String locRating, @PathVariable String category, @PathVariable String longitude, @PathVariable String latitude, @PathVariable double speed) {
//		try {
//			double numLongitude = Double.parseDouble(longitude);
//			double numLatitude = Double.parseDouble(latitude);
//			double numLocRating = Integer.parseInt(locRating);
//			double numWifiRating = Integer.parseInt(wifiRating);
//			String status = "Pending";
//			
//			Locations proposedLocation = new Locations(name, address, numLongitude, numLatitude, numLocRating, 
//					numWifiRating,category, status,speed );
//			int locAdded = locDao.addNewLocation(proposedLocation);
//			if(locAdded == 1) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}catch(NumberFormatException e) {
//			return 0;
//		}
//	
//	}
	//
	
	@CrossOrigin
	@RequestMapping(value = "/ADMINCreateLocation/{name}/{address}/{wifiRating}/{locRating}/{category}/{longitude}/{latitude}/{ipAddress}/{macAddress}/{description}/{downloadSpeed}/{uploadSpeed}/{ping}/{status}", 
	method = RequestMethod.POST)
	public int adminCreateLocation(@PathVariable String name,@PathVariable String address, 
			@PathVariable String wifiRating, @PathVariable String locRating, @PathVariable String category, 
			@PathVariable String longitude, @PathVariable String latitude, @PathVariable String ipAddress, 
			@PathVariable String macAddress,@PathVariable String description, @PathVariable String downloadSpeed, 
			@PathVariable String uploadSpeed, @PathVariable String ping,  @PathVariable String status) {
		try {
			double numLongitude = Double.parseDouble(longitude);
			double numLatitude = Double.parseDouble(latitude);
			double numLocRating = Integer.parseInt(locRating);
			double numWifiRating = Integer.parseInt(wifiRating);
			
			
			Locations  createdLoc = new Locations(name, address, numLongitude, numLatitude, numLocRating, 
					numWifiRating,category);
//			
			int res1 = locDao.addNewLocation(createdLoc);
			
			int loId = locDao.getLocationIdForStatus(createdLoc);
			
			String loName = locDao.getLocationNameForStatus(createdLoc);
			
			String loAdd = locDao.getLocationAddressForStatus(createdLoc);
			
			LocationDataStatus ls = new LocationDataStatus(loId,loName,loAdd,ipAddress, macAddress,description,downloadSpeed,uploadSpeed,ping,status);
			if(res1 == 1) {
				int res2 = locDao.addNewLocationStatus(ls);
				if(res2 == 1) {
					return 1;
				}else {
					return 0;
				}
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
		//locDao.curateLocation(id, thisStatus);
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
			
			
			//locDao.rateLocation(Integer.parseInt(id), Integer.parseInt(rating));
			return 0;
		
		}
	
//	@CrossOrigin
//	@RequestMapping(value = "/ChangeLocInfo/{locid}/{name}/{address}/{password}/{photo}/{wifiRating}/{locRating}/{category}/{comment}/{longitude}/{latitude}/{status}", 
//	method = RequestMethod.POST)
//	public int changeLocationInformation(@PathVariable int locid, @PathVariable String name, @PathVariable String password, @PathVariable String photo, @PathVariable String wifiRating,
//			@PathVariable String locRating, @PathVariable String category, @PathVariable String comment, 
//			@PathVariable String longitude, @PathVariable String latitude, @PathVariable String address, @PathVariable String status) {
//		try {
//			double numLongitude = Double.parseDouble(longitude);
//			double numLatitude = Double.parseDouble(latitude);
//			int numLocRating = Integer.parseInt(locRating);
//			int numWifiRating = Integer.parseInt(wifiRating);
//			LocationType locType = LocationType.toType(category);
//			LocationStatus locStatus = LocationStatus.toStatus(status);
//			
//			
//			CorporateLocation changeLocInfo = new CorporateLocation(name, address, numLongitude, numLatitude, numLocRating, 
//					numWifiRating,LocationType.locTypeToString(locType), photo, LocationStatus.getStatus(locStatus));
//			changeLocInfo.setLocationId(locid);
//			if(locDao.changeLocInfo(changeLocInfo) == 1) {
//				return 1;
//			} else {
//				return 0;
//			}
//		}catch(NumberFormatException e) {
//			return 1;
//		}
//	
//	}
	
	@CrossOrigin
	@RequestMapping(value = "/blockUser/{email}", method = RequestMethod.POST)
	public int blockUser(@PathVariable String email) {
		CitizenUser cUser = dao.getValidatedUser(email);
		dao.blockUser(cUser);
		if(dao.blockUser(cUser) == 1) {
			return 1;
		}else {
			return 2;
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/unblockUser/{email}", method = RequestMethod.POST)
	public int unblockUser(@PathVariable String email) {
		CitizenUser cUser = dao.getValidatedUser(email);
		dao.unblockUser(cUser);
		if(dao.unblockUser(cUser) == 1) {
			return 1;
		}else {
			return 2;
		}
	}
	
}
