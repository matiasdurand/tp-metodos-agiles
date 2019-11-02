package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.License;
import domain.TaxPayer;
import domain.TypeId;

public class TaxPayerDAOSQL implements TaxPayerDAO {

	
	public TaxPayerDAOSQL(){
	}

	@Override
	public TaxPayer findByPersonalId(TypeId typeId, Long personalId) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		TaxPayer contribuyente = (TaxPayer) session.createQuery("from contribuyente where tipo_documento =" + typeId +"and nro_documento = " + personalId).getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;	
	}	

	@Override
	public void save(TaxPayer contribuyente) {
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
	public TaxPayer update(TaxPayer contribuyente) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		session.saveOrUpdate(contribuyente);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;
	}

	@Override
	public TaxPayer find(int id) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		TaxPayer contribuyente = session.get(TaxPayer.class, id);
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
		List<TaxPayer> contribuyentes = (List<TaxPayer>) session.createQuery("from contribuyente").getResultList();
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