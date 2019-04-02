package ca.smartsauga.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.Locations;

@RestController
public class LoginController {
	
	
	@CrossOrigin
	@RequestMapping(value = "/validateUser/{email}/{password}", method = RequestMethod.POST)
	public CitizenUser validateStudent(@PathVariable String email, @PathVariable String password) {
		
		//TODO: We want to check the password passed to the DAO that gets the password without instantiating a variable
		//TODO: dao.validateUser will return a decrypted Password
		//If Password is correct, return a CitizenUser else return null, handle at front end
		if(password.contentEquals(dao.validateUser(email))) {
			return dao.getValidatedUser(email);
		} else {
			return null;
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/GetLocations", method = RequestMethod.GET)
	public List<Locations> getLocations() {
		
		//TODO: DAO Get All Locations needs to be in DAO class
		return dao.getAllLocations();
		
	}

}
