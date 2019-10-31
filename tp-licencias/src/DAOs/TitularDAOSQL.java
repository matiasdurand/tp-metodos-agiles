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
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(Titular.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		Titular titular = (Titular) session.createQuery("from titular where tipo_documento =" + typeId +"and numero_documento = " + personalId).getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return titular;
			
	}
	
	@Override
	public List<Titular> findAllTitulars() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(Titular.class).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		List<Titular> titulares = (List<Titular>) session.createQuery("from titular").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return titulares;
		
	}
}