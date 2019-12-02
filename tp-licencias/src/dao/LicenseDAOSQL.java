package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.NoResultException;

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
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License").getResultList(); 
			return licenses;
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			session.getTransaction().commit();
			session.close();
			factory.close();
	
		}
	}
	
	@Override
	public List<License> findExpiredLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License where fecha_vencimiento < current_date").getResultList(); 
			return licenses;
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			session.getTransaction().commit();
			session.close();
			factory.close();
	
		}
	}
	
	@Override
	public List<License> findActiveLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<License> licenses = (List<License>) session.createQuery("from License where fecha_vencimiento > current_date").getResultList(); 
			return licenses;
		}
		catch(NoResultException e) {
			return null;
		}
		finally {
			session.getTransaction().commit();
			session.close();
			factory.close();
	
		}
	}
}