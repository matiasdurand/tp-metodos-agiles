package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import audit.TitularMovement;


public class TitularMovementDAOSQL extends GenericDAOSQL<TitularMovement, Integer> implements TitularMovementDAO {

	public TitularMovementDAOSQL(Class<TitularMovement> type) {
		super(type);
	}
 
	@Override
	public List<TitularMovement> findAllTitularMovements() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		List<TitularMovement> Movements = (List<TitularMovement>) session.createQuery("from TitularMovement").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return Movements;
	}

	
	
}