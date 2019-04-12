package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.Locations;
import ca.smartsauga.beans.User;
import ca.smartsauga.utilities.PasswordController;


public class Dao {
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").buildSessionFactory();
	PasswordController passwordController = new PasswordController();
	
	public void addLocation(Locations locations) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(locations);
		session.getTransaction().commit();
		session.close();
	}
	
	public List<Locations> getAllLocations(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//get location  object for this one ======pending
		Query query = session.createQuery("from Locations");
		List<Locations> locationList = (List<Locations>)query.getResultList();
		session.getTransaction().commit();
		session.close();
		return locationList;
	}
	
	public String validateUser(String email) {
		return "password string";
	}
	//It returns user and we are using it to match passwords
	public CitizenUser getValidatedUser(String email){
		CitizenUser user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User WHERE email =:email");
		query.setParameter("email", email);
		List<CitizenUser> userList = (List<CitizenUser>) query.getResultList();
		if(userList.size() == 1) {
			user = userList.get(0);
		}
		session.getTransaction().commit();
		session.close();
		return user;
		
		
	}
	
	public void registerUser(CitizenUser user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		user.setPassword(passwordController.encrypt(user.getPassword()));
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
	}
	
	//returns email for checking if user exists
	public String getUnvalidatedUser(String email) {
		CitizenUser citizenUser;
		String userEmail = "";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User WHERE email =: email");
		query.setParameter("email", email);
		List<CitizenUser> userList = query.getResultList();
		session.getTransaction().commit();
		session.close();
		if(userList.size() == 1) {
			userEmail = userList.get(0).getEmail();
			return userEmail;
		}else {
			return userEmail;
		}
	}
	public CitizenUser modifyProfile(CitizenUser cu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		

		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
		if(!cu.getName().isEmpty()) {
			modUser.setName(cu.getName().trim());
		}
		if(!cu.getBirthdate().isEmpty()) {
			modUser.setBirthdate(cu.getBirthdate().trim());
		}
		if(!cu.getAbout().isEmpty()) {
			modUser.setAbout(cu.getAbout().trim());
		}
		if(!cu.getFacebookUrl().isEmpty()) {
			modUser.setFacebookUrl(cu.getFacebookUrl().trim());
		}
		if(!cu.getNotes().isEmpty()) {
			modUser.setNotes(cu.getNotes().trim());
		}
		session.getTransaction().commit();
		session.close();
		
		return modUser;
	}
	
}
