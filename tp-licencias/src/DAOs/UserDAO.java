package DAOs;

import java.util.List;
import domain.User;

public interface UserDAO extends GenericDAO<User, Integer> {
	List<User> findAllUsers();
	public User findByUsernameAndPassword(String username, String password);
}
