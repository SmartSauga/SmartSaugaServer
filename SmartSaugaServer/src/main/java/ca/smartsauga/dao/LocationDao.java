package ca.smartsauga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ca.smartsauga.beans.CorporateLocation;
import ca.smartsauga.beans.Locations;

public class LocationDao {
	SessionFactory sessionFactory = new Configuration().
			configure("hibernate.cfg.xml").buildSessionFactory();
	
	public int addCorporateLocation(Locations location) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(location);
		session.getTransaction().commit();
		session.close();
		
		return 0;
	}

}
