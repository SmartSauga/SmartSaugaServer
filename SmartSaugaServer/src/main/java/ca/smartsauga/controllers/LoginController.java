package ca.smartsauga.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.smartsauga.beans.CitizenUser;

@RestController
public class LoginController {
	
	@RequestMapping(value = "/studentList", method = RequestMethod.GET)
	public List<CitizenUser> getStudents() {
		return dao.getUsers();
	}

}
