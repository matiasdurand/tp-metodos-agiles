package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import domain.License;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz LicenseDAO.
 * @author 
 *
 */
public class LicenseDAOSQL extends GenericDAOSQL<License, Integer> implements LicenseDAO {

	public LicenseDAOSQL(Class<License> type) {
		super(type);
	}
 
	@Override
	public List<License> findAllLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		List<License> licenses = (List<License>) session.createQuery("from License").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return licenses;
	}
	
}