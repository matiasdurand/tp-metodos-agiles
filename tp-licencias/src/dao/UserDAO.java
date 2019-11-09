package dao;

import java.util.List;
import domain.User;

public interface UserDAO extends GenericDAO<User, Integer> {
	
	public List<User> findAllUsers();
	public User findByUsernameAndPassword(String username, String password);
	
}
