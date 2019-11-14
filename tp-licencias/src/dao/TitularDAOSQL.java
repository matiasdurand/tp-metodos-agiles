package dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import domain.Titular;
import domain.TypeId;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz LicenseDAO.
 * @author 
 *
 */
public class TitularDAOSQL extends GenericDAOSQL<Titular,Integer> implements TitularDAO {

	public TitularDAOSQL(Class<Titular> type) {
		super(type);
	}

	@Override
	public Titular findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
		
		Titular titular = (Titular) session.createQuery("from Titular where typeId= '" + typeId +"' and personalId= '" + personalId + "'").getSingleResult();
		return titular;
		}catch(NoResultException e) {
			return null;
		}
		finally {
			session.getTransaction().commit();
			session.close();
			factory.close();
		}	
	}
	
	@Override
	public List<Titular> findAllTitulars() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			List<Titular> titulares = (List<Titular>) session.createQuery("from Titular").getResultList(); 
			return titulares;
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