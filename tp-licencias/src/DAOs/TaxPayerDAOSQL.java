package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.License;
import domain.TaxPayer;
import domain.Titular;
import domain.TypeId;

public class TaxPayerDAOSQL extends GenericDAOSQL<TaxPayer,Integer> implements TaxPayerDAO {

	public TaxPayerDAOSQL(Class<TaxPayer> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TaxPayer findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		TaxPayer contribuyente = (TaxPayer) session.createQuery("from TaxPayer where typeId= '" + typeId +"' and personalId= '" + personalId + "'").getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;	
	}

	@Override
	public List<TaxPayer> findAllTaxPayers() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		List<TaxPayer> contribuyentes = (List<TaxPayer>) session.createQuery("from TaxPayer").getResultList();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyentes;
		
	}

	private SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayer.class).buildSessionFactory();
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