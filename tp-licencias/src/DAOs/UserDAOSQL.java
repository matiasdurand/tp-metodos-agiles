package DAOs;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import domain.User;

public class UserDAOSQL extends GenericDAOSQL<User,Integer> implements UserDAO {


	public UserDAOSQL(Class<User> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<User> findAllUsers() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		List<User> usuarios = (List<User>) session.createQuery("from usuario").getResultList(); 
		session.getTransaction().commit();
		session.close();
		factory.close();
		return usuarios;
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		User usuario = (User) session.createQuery("from usuario where nombre_usuario =" + username +"and password = " + password).getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return usuario;
	}
	
	private SessionFactory createFactory() {
		// crear factory
		SessionFactory factory = new Configuration().configure("hibernate-sistema.cfg.xml").addAnnotatedClass(User.class).buildSessionFactory();
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
