package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.TypeId;
import dto.TaxPayerDTO;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz TaxPayerDAO. 
 * @author 
 *
 */
public class TaxPayerDAOSQL implements TaxPayerDAO {

	@Override
	public TaxPayerDTO find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		TaxPayerDTO contribuyente = (TaxPayerDTO) session.createQuery("from TaxPayer where typeId= '" + typeId +"' and personalId= '" + personalId + "'").getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;	
	}

	@Override
	public List<TaxPayerDTO> findAllTaxPayers() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		List<TaxPayerDTO> taxPayers = (List<TaxPayerDTO>) session.createQuery("from TaxPayer").getResultList();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return taxPayers;
		
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