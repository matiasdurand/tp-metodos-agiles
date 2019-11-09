package dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Esta clase es una implementacion generica para SQL (utilizando Hibernate) de la interfaz GenericDAO
 * de esta clase heredaran las implementaciones de cada interfaz DAO, de esta manera se evita duplicar codigo
 * en cada clase.
 * @author 
 * 
 * @param <T> tipo de dato generico que representa la entidad del dominio.  
 * @param <ID> tipo de dato generico que representa un parametro de busqueda.
 */
public class GenericDAOSQL<T, ID extends Serializable> implements GenericDAO<T,ID> {
	
	private final Class<T> type;
	
	
	public GenericDAOSQL(Class<T> type){
	     this.type = type;
	   }
	
	@Override
	public void save(T entity) {
		// crear factory
		SessionFactory sf = new Configuration().configure("hibernate-sistema.cfg.xml").buildSessionFactory();
		// crear sesi�n
		Session session = sf.openSession();
		// usar el objeto session
		session.beginTransaction();
		session.save(entity);
		session.getTransaction().commit();
		session.close();
		sf.close();
	}

	@Override
	public T update(T entity) {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		// crear sesi�n
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return entity;
	}

	@Override
	public T find(ID id) {
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		// crear sesi�n
		Session session = factory.getCurrentSession(); // usar el objeto session
		session.beginTransaction();
		T t = session.get(type, id);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return t;
		
	}

	protected SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		return factory;
	}
	
	protected Session createSession(SessionFactory factory) {
		// crear sesi�n
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		return session;
	}

}
