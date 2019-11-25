package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import audit.UserMovement;


public class UserMovementDAOSQL extends GenericDAOSQL<UserMovement, Integer> implements UserMovementDAO {

	public UserMovementDAOSQL(Class<UserMovement> type) {
		super(type);
	}
 
	@Override
	public List<UserMovement> findAllUserMovements() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		List<UserMovement> Movements = (List<UserMovement>) session.createQuery("from UserMovement").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return Movements;
	}

	
	
}