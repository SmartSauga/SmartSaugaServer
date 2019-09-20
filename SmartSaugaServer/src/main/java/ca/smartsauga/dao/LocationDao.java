package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.CorporateLocation;
import ca.smartsauga.beans.Locations;
import ca.smartsauga.enums.LocationStatus;
import ca.smartsauga.enums.LocationType;

public class LocationDao {
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").buildSessionFactory();
	
	public int getLocation(String address){
		Locations loc = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations WHERE address =:address");
		query.setParameter("address", address);
		List<Locations> locList = (List<Locations>) query.getResultList();
		try{
			if(locList.size() == 1) {
		
			loc = locList.get(0);
			session.getTransaction().commit();
			session.close();
			return 1;
		}else {
			return 0;
		}
		}catch(NullPointerException e) {
			return 0;
		}
		
	}
	
//	public int addCorporateLocation(Locations location) {
//		if(getLocation(location.getAddress()) == 1) {
//			return 0;
//		} else {
//			Session session = sessionFactory.openSession();
//			session.beginTransaction();
//			session.save(location);
//			session.getTransaction().commit();
//			session.close();
//			return 1;
//		}
//	}
	
	public int addCorporateLocation(Locations location) {
	
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(location);
			session.getTransaction().commit();
			session.close();
			return 1;
		}
	
	
	public int removeLocation(int id) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Locations delLocation = (Locations) session.get(Locations.class, id);
		session.delete(delLocation);
		session.getTransaction().commit();
		session.close();
		return 0;

	}
	
	//Need Validation for SQL errors, such as nothing found
	public int curateLocation(String id, LocationStatus status) {
		CorporateLocation loc = null;
		int locId = Integer.parseInt(id);
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations WHERE id =:id");
		query.setParameter("id", locId);
		List<CorporateLocation> locList = (List<CorporateLocation>) query.getResultList();
		if(locList.size() == 1) {
			loc = locList.get(0);
			loc.setLocStatus(LocationStatus.getStatus(status));
		} else {
			return 1;
		}
		session.getTransaction().commit();
		session.close();
		return 0;
	}
	
	public int rateLocation(int id, int rating) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Locations rateLoc = (Locations) session.get(Locations.class, id);
		/* TODO: Fix Ratings on Locations to be a list
		 * Change setLocUserRating to send to a method that takes the list of ratings and calculates the average
		 * This is actually wrong, the object needs a LIST of ratings to get an average, but for now, this just sets it
		*/
		int newRating = (rateLoc.getLocUserRating()+(rating/rateLoc.getLocNumRaters())) * (1-(1/rateLoc.getLocNumRaters()));
		rateLoc.setLocNumRaters(rateLoc.getLocNumRaters() + 1);
		rateLoc.setLocUserRating(newRating);
		session.getTransaction().commit();
		session.close();
		return 0;
	}
	
	public int changeLocInfo(CorporateLocation corpLocation) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		CorporateLocation modLoc = (CorporateLocation) session.get(CorporateLocation.class, corpLocation.getLocationId());
		modLoc.setAddress(corpLocation.getAddress());
		modLoc.setLatitude(corpLocation.getLatitude());
		modLoc.setLongitude(corpLocation.getLongitude());
		modLoc.setLocationImageFile(corpLocation.getLocationImageFile());
		modLoc.setLocStatus(corpLocation.getLocStatus());
		modLoc.setName(corpLocation.getName());
		modLoc.setLocType(corpLocation.getLocType());
		
		session.getTransaction().commit();
		session.close();
		
		
		return 0;
	}
	
	

}
