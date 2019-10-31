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
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(License.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		List<License> licencias = (List<License>) session.createQuery("from licencia").getResultList(); //Necesito la tabla y no la tengo 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return licencias;
		
	}
	
}