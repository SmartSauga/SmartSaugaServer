package ca.smartsauga.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CitizenUser;
import ca.smartsauga.beans.CorporateLocation;
import ca.smartsauga.beans.Locations;

public class LocationDao {
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").buildSessionFactory();
	
	public int getLocation(Locations location){
		Locations loc = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Locations WHERE location_address =:address");
		query.setParameter("address", location.getAddress());
		List<Locations> locList = (List<Locations>) query.getResultList();
		if(locList.size() == 1) {
			loc = locList.get(0);
		}
		session.getTransaction().commit();
		session.close();
		try {
			if(loc.getAddress().contentEquals(location.getAddress())) {
				return 1;
			} else {
				return 0;
			}
		} catch(NullPointerException e) {
			return 0;
		}
		
	}
	
	public int addCorporateLocation(Locations location) {
		if(getLocation(location) == 1) {
			return 1;
		} else {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(location);
			session.getTransaction().commit();
			session.close();
			return 0;
		}
	}
	
	

}
