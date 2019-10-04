package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.LocationDataStatus;
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
	
	public int addNewLocation(Locations location) {
	
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			try {
			if(getLocation(location.getAddress()) == 0) {
				session.save(location);
				session.getTransaction().commit();
				session.close();
				return 1;
			}else {
				return 0;
			}
			}catch(Exception e) {
				return 0;
			}
			
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
//	public int curateLocation(String id, LocationStatus status) {
//		CorporateLocation loc = null;
//		int locId = Integer.parseInt(id);
//		
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Query query = session.createQuery("from Locations WHERE id =:id");
//		query.setParameter("id", locId);
//		List<CorporateLocation> locList = (List<CorporateLocation>) query.getResultList();
//		if(locList.size() == 1) {
//			loc = locList.get(0);
//			loc.setLocStatus(LocationStatus.getStatus(status));
//		} else {
//			return 1;
//		}
//		session.getTransaction().commit();
//		session.close();
//		return 0;
//	}
	
//	public int rateLocation(int id, int rating) {
//		Session session = sessionFactory.openSession();
//		session.beginTransaction();
//		Locations rateLoc = (Locations) session.get(Locations.class, id);
//		/* TODO: Fix Ratings on Locations to be a list
//		 * Change setLocUserRating to send to a method that takes the list of ratings and calculates the average
//		 * This is actually wrong, the object needs a LIST of ratings to get an average, but for now, this just sets it
//		*/
//		int newRating = (rateLoc.getLocUserRating()+(rating/rateLoc.getLocNumRaters())) * (1-(1/rateLoc.getLocNumRaters()));
//		rateLoc.setLocNumRaters(rateLoc.getLocNumRaters() + 1);
//		rateLoc.setLocUserRating(newRating);
//		session.getTransaction().commit();
//		session.close();
//		return 0;
//	}
	
	public int updateLocation(int id, String name, String address, String category) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		Query query1 = session.createQuery("Update Locations set address=:address where locationId=:locationId");
		query1.setParameter("address",address);
		query1.setParameter("locationId",id);
		query1.executeUpdate();
		
		Query query5 = session.createQuery("Update Locations set name=:name where locationId=:locationId");
		query5.setParameter("name",name);
		query5.setParameter("locationId",id);
		query5.executeUpdate();
		
		Query query3 = session.createQuery("Update Locations set category=:category where locationId=:locationId");
		query3.setParameter("category", category);
		query3.setParameter("locationId",id);
		query3.executeUpdate();
		
		Query query2 = session.createQuery("Update LocationDataStatus set name=:name where locationId=:locationId");
		query2.setParameter("name",name);
		query2.setParameter("locationId",id);
		query2.executeUpdate();
		
		
		
		Query query4 = session.createQuery("Update LocationDataStatus set address=:address where locationId=:locationId");
		query4.setParameter("address",address);
		query4.setParameter("locationId",id);
		query4.executeUpdate();
		
		
		
		
		
		
		session.getTransaction().commit();
		session.close();
		
		
		return 0;
	}
	
	public int getLocationIdForStatus(String name) {
		int id = 0;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations WHERE name =:name");
		query.setParameter("name", name);
		List<Locations> locationList = (List<Locations>) query.getResultList();
			id = locationList.get(0).getLocationId();
			session.getTransaction().commit();
			session.close();
			return id;			
	
	}
	
	public String getLocationNameForStatus(Locations location) {
		String locName = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations");
		List<Locations> locationList = (List<Locations>) query.getResultList();
			locName = locationList.get(0).getName();
			session.getTransaction().commit();
			session.close();
			return locName;			
	
	}
	public String getLocationAddressForStatus(Locations location) {
		String locAddress = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations");
		List<Locations> locationList = (List<Locations>) query.getResultList();
			locAddress = locationList.get(0).getAddress();
			session.getTransaction().commit();
			session.close();
			return locAddress;			
	
	}
	
	public int addNewLocationStatus(LocationDataStatus locStatus) {
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
		session.save(locStatus);
		session.getTransaction().commit();
		session.close();
		return 1;
		}catch(Exception e) {
			return 0;
		}
		
	}
	
	public Locations getLocationDataFromId(int id) {
		Locations loc;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations where id =:id");
		query.setParameter("id", id);
		List<Locations> locationList = (List<Locations>) query.getResultList();
			loc = locationList.get(0);
			session.getTransaction().commit();
			session.close();
			return loc;	
	}
	
	public int deleteLocation(int locationId) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Query query1 = session.createQuery("DELETE  FROM Locations WHERE locationId=:locationId");
		query1.setParameter("locationId",locationId);
		query1.executeUpdate();
		Query query2 = session.createQuery("DELETE  FROM LocationDataStatus WHERE locationId=:locationId");
		query2.setParameter("locationId",locationId);
		query2.executeUpdate();
		
		session.getTransaction().commit();
		session.close();
		return 1;
	}

}
