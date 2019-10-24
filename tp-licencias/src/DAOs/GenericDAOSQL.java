package DAOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;








/* Esta clase implementa de manera generica el acceso a la base de datos SQL (mediante Hibernate),
 * de esta clase heredaran las implementaciones de cada interfaz DAO, de esta manera se evita duplicar codigo en cada entidad*/

public class GenericDAOSQL<T, ID extends Serializable> implements GenericDAO<T,ID> {
	
	
	private final Class<T> type;
	
	public GenericDAOSQL(Class<T> type){
	     this.type = type;
	   }
	

	@Override
	public T create() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(T entity) {
	// crear factory
	SessionFactory sf = new Configuration().configure("hibernate-sistema.xml").buildSessionFactory();
	// crear sesión
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
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		// crear sesión
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
	public T find(int id) {
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession(); // usar el objeto session
		session.beginTransaction();
		T t = session.get(type, id);
		session.getTransaction().commit();
		session.close();
		factory.close();
		return t;
		
	}

	@Override
	public List<T> findAll() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(type).buildSessionFactory();
		// crear sesión
		Session session = factory.getCurrentSession();
		// usar el objeto session
		session.beginTransaction();
		List<T> estados = (List<T>) session.createQuery("from" + type).getResultList(); //Necesito la tabla y no la tengo 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return estados;
		
	}

}
