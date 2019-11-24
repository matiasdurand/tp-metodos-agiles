package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.NoResultException;

import domain.TypeId;
import dto.TaxPayerDTO;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz TaxPayerDAO. 
 * @author 
 *
 */
public class TaxPayerDAOSQL implements TaxPayerDAO {

	public TaxPayerDAOSQL(Class<TaxPayerDTO> class1) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public TaxPayerDTO find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(TaxPayerDTO contribuyente) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
	    session.save(contribuyente);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}
	
	@Override
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			TaxPayerDTO contribuyente = (TaxPayerDTO) session.createQuery("from TaxPayerDTO where typeId= '" + typeId +"' and personalId= '" + personalId + "'").getSingleResult();
			return contribuyente;
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
	public List<TaxPayerDTO> findAllTaxPayers() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<TaxPayerDTO> taxPayers = (List<TaxPayerDTO>) session.createQuery("from TaxPayerDTO").getResultList();
			return taxPayers;
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

	private SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayerDTO.class).buildSessionFactory();
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