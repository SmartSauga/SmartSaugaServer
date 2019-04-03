package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.Locations;


public class Dao {
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").buildSessionFactory();
	
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
	
	public CitizenUser getValidatedUser(String email){
		CitizenUser user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User WHERE email =: email");
		query.setParameter("email", email);
		List<CitizenUser> userList = (List<CitizenUser>) query.getResultList();
		if(userList.size() == 1) {
			user = userList.get(0);
		}
		session.getTransaction().commit();
		session.close();
		return user;
		
		
	}
	
	public void registerUser(CitizenUser user, String password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.save(password);
		session.getTransaction().commit();
		session.close();
		
	}
	
	
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
	
	
	
}
