package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.TaxPayer;
import domain.TypeId;

public class TaxPayerDAOSQL implements TaxPayerDAO {

	
	public TaxPayerDAOSQL(){
	}

	
	
	
	@Override
	public TaxPayer findByPersonalId(TypeId typeId, Long personalId) {
	// crear factory
	SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayer.class).buildSessionFactory();
	// crear sesión
	Session session = factory.getCurrentSession();
	// usar el objeto session
	session.beginTransaction();
	TaxPayer contribuyente = (TaxPayer) session.createQuery("from contribuyente where tipo_documento =" + typeId +"and nro_documento = " + personalId).getSingleResult();
	session.getTransaction().commit();
	session.close();
	factory.close();
	return contribuyente;
		
	}	

	
	@Override
	public void save(TaxPayer contribuyente) {
	// crear factory
	SessionFactory sf = new Configuration().configure("hibernate-contribuyentes.cfg.xml").buildSessionFactory();
	// crear sesión
	Session session = sf.openSession();
	// usar el objeto session
	session.beginTransaction();
    session.save(contribuyente);
	session.getTransaction().commit();
	session.close();
	sf.close();
	}

	@Override
	public TaxPayer update(TaxPayer contribuyente) {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayer.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		session.saveOrUpdate(contribuyente);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;
	}

	@Override
	public TaxPayer find(int id) {
		SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayer.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession(); // usar el objeto session
		session.beginTransaction();
		TaxPayer contribuyente = session.get(TaxPayer.class, id);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyente;
		
	}

	@Override
	public List<TaxPayer> findAllTaxPayers() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-contribuyentes.cfg.xml").addAnnotatedClass(TaxPayer.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		List<TaxPayer> contribuyentes = (List<TaxPayer>) session.createQuery("from contribuyente").getResultList();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return contribuyentes;
		
	}

	
	
	
	
	
	
	
}