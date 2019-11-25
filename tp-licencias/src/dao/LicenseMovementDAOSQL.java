package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import audit.LicenseMovement;


public class LicenseMovementDAOSQL extends GenericDAOSQL<LicenseMovement, Integer> implements LicenseMovementDAO {

	public LicenseMovementDAOSQL(Class<LicenseMovement> type) {
		super(type);
	}
 
	@Override
	public List<LicenseMovement> findAllLicenseMovements() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		List<LicenseMovement> Movements = (List<LicenseMovement>) session.createQuery("from LicenseMovement").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return Movements;
	}

	
	
}