package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
		List<User> users = (List<User>) session.createQuery("from User").getResultList();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return users;
	}
	
	public User findByUsernameAndPassword(String username, String password) {
		//creamos factory
		SessionFactory factory = createFactory();
		//creamos session BD
		Session session = createSession(factory);
	
		User user = (User) session.createQuery("from User where username= '" + username + "' and  password= '" + password + "'").getSingleResult();
		session.getTransaction().commit();
		session.close();
		factory.close();
		return user;
	}
	
}
