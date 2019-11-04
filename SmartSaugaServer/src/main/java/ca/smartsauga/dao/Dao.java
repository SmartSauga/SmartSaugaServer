package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.Feedback;
import ca.smartsauga.beans.LocationDataStatus;
import ca.smartsauga.beans.Locations;
import ca.smartsauga.beans.ReportedProblems;
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
	
	public List<LocationDataStatus> getAllLocationsData(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//get location  object for this one ======pending
		Query query = session.createQuery("from LocationDataStatus");
		List<LocationDataStatus> locationDList = (List<LocationDataStatus>)query.getResultList();
		session.getTransaction().commit();
		session.close();
		return locationDList;
	}
	// returns all citizen users in the database
	public List<CitizenUser> getAllUsers(){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		//get location  object for this one ======pending
		Query query = session.createQuery("from User");
		List<CitizenUser> userList = (List<CitizenUser>)query.getResultList();
		session.getTransaction().commit();
		session.close();
		try {
			return userList;
		}catch(NullPointerException e) {
			return userList;
		}
			
	}
	// user data for admin
	public List<User> userDataForAdmin(){
		List<CitizenUser> exList = getAllUsers();
		List<User> cuList = null;
		for(int i=0; i< exList.size(); i++) {
			User cu = new CitizenUser(exList.get(i).getUserId(), exList.get(i).getFirstname(),
					exList.get(i).getLastname(), exList.get(i).getEmail(), 
					exList.get(i).getUserBirthdate(), exList.get(i).getType(), exList.get(i).getStatus());
			cuList.add(cu);
		}
		return cuList;
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
		try {
			if(userList.size() == 1) {
				user = userList.get(0);
				session.getTransaction().commit();
				session.close();
				return user;
			}else {
				return null;
			}
		}catch(NullPointerException e) {
			return null;
		}
		
		
	}
	public CitizenUser getValidatedAdminUser(String email){
		CitizenUser user = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from User WHERE email =:email");
		query.setParameter("email", email);
		List<CitizenUser> userList = (List<CitizenUser>) query.getResultList();
		try {
			if(userList.size() == 1) {
				user = userList.get(0);
				session.getTransaction().commit();
				session.close();
				if(user.getType() == "Admin") {
					return user;
				}else {
					return null;
				}
			}else {
				return null;
			}
		}catch(NullPointerException e) {
			return null;
		}
		
		
	}
	
	public void registerCitizenUser(User user) {
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
//	public CitizenUser modifyProfile(CitizenUser cu) {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		
//
//		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
//		if(!cu.getName().isEmpty()) {
//			modUser.setName(cu.getName().trim());
//		}
//		if(!cu.getBirthdate().isEmpty()) {
//			modUser.setBirthdate(cu.getBirthdate().trim());
//		}
//		if(!cu.getAbout().isEmpty()) {
//			modUser.setAbout(cu.getAbout().trim());
//		}
//		if(!cu.getFacebookUrl().isEmpty()) {
//			modUser.setFacebookUrl(cu.getFacebookUrl().trim());
//		}
//		if(!cu.getNotes().isEmpty()) {
//			modUser.setNotes(cu.getNotes().trim());
//		}
//		session.getTransaction().commit();
//		session.close();
//		
//		return modUser;
//	}
	
	public CitizenUser updateUserProfile(CitizenUser cu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		

		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
		if(!cu.getFirstname().isEmpty()) {
			modUser.setFirstname(cu.getFirstname().trim());
		}
		
		if(!cu.getLastname().isEmpty()) {
			modUser.setLastname(cu.getLastname().trim());
		}
		
		if(!cu.getUserBirthdate().isEmpty()) {
			modUser.setUserBirthdate(cu.getUserBirthdate().trim());
		}
		

		session.getTransaction().commit();
		session.close();
		
		return modUser;
	}
	
	public CitizenUser updateUserByAdmin(String email, String type, String status) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		
		Query query1 = session.createQuery("Update User set type=:type where email=:email");
		query1.setParameter("type",type);
		query1.setParameter("email",email);
		query1.executeUpdate();
		
		Query query2 = session.createQuery("Update User set status=:status where email=:email");
		query2.setParameter("status",status);
		query2.setParameter("email",email);
		query2.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		
		
		return getValidatedUser(email);
		
	}
	public boolean deleteCitizenUser(CitizenUser cu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		

		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
		
		session.delete(modUser);
		
		session.getTransaction().commit();
		session.close();
		
		return true;
	}
	
	public int blockUser(CitizenUser cu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
		modUser.setStatus("Blocked");
		session.getTransaction().commit();
		session.close();
		return 1;
	}
	
	public int unblockUser(CitizenUser cu) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		CitizenUser modUser = (CitizenUser) session.get(CitizenUser.class, cu.getUserId());
		modUser.setStatus("verified");
		session.getTransaction().commit();
		session.close();
		return 1;
	}
	
	public int deleteUser(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query1 = session.createQuery("DELETE  FROM User WHERE email=:email");
		query1.setParameter("email",email);
		query1.executeUpdate();
		session.getTransaction().commit();
		session.close();
		return 1;
	}
	
	//save the problem in the database
	public void reportProblem(ReportedProblems problem) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(problem);
		session.getTransaction().commit();
		session.close();
	}
	//delete the problem by system admin
	public void deleteProblem(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("DELETE  FROM ReportedProblems WHERE id=:id");
		query.setParameter("id",id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	//change the status of problem by admin
	public void changeStatusOfReport(int id, String problemStatus) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("Update ReportedProblems set problemStatus=:problemStatus where id=:id");
		query.setParameter("problemStatus",problemStatus);
		query.setParameter("id",id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
	

	public void addFeedBack(Feedback feedback) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(feedback);
		session.getTransaction().commit();
		session.close();
	}
	
	public void userHasRated(String hasRated, String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("Update User set hasRated=:hasRated where email=:email");
		query.setParameter("hasRated",hasRated);
		query.setParameter("email",email);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();
	}
	
}
