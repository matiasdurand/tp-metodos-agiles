package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.License;

public class LicenseDAOSQL extends GenericDAOSQL<License, Integer> implements LicenseDAO {

	public LicenseDAOSQL(Class<License> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
 
	@Override
	public List<License> findAllLicenses() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		List<License> licencias = (List<License>) session.createQuery("from License").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return licencias;
	}
	
	private SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(License.class).buildSessionFactory();
		return factory;
	}
	
	private Session createSession(SessionFactory factory) {
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		return session;
	}
	
}