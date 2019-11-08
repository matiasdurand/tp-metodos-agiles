package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.License;
import domain.TaxPayer;
import domain.Titular;
import domain.TypeId;

public class TitularDAOSQL extends GenericDAOSQL<Titular,Integer> implements TitularDAO {

	
	public TitularDAOSQL(Class<Titular> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	public Titular findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		Titular titular = (Titular) session.createQuery("from Titular where typeId= '" + typeId +"' and personalId= '" + personalId + "'").getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return titular;
			
	}
	
	@Override
	public List<Titular> findAllTitulars() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		List<Titular> titulares = (List<Titular>) session.createQuery("from Titular").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return titulares;
		
	}
	
	private SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(Titular.class).buildSessionFactory();
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