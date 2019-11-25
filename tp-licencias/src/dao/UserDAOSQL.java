package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.NoResultException;

import domain.User;

public class UserDAOSQL extends GenericDAOSQL<User,Integer> implements UserDAO {

	public UserDAOSQL(Class<User> type) {
		super(type);
	}

	@Override
	public List<User> findAllUsers() {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
	    //creamos consulta HQL
		try {
			@SuppressWarnings("unchecked")
			List<User> users = (List<User>) session.createQuery("from User").getResultList();
			return users;
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
	
	public User findByUsernameAndPassword(String username, String password) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
		//creamos consulta HQL
		try {
			User user = (User) session.createQuery("from User where username= '" + username + "' and  password= '" + password + "'").getSingleResult();
			return user;
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
}
