package controllers;

import audit.UserMovement;
import dao.UserDAO;
import dao.UserDAOSQL;
import dao.UserMovementDAO;
import dao.UserMovementDAOSQL;
import domain.User;
import dto.UserDTO;

public class UserController {

	public static UserController INSTANCE = new UserController();
	private UserDAO userDAO = new UserDAOSQL(User.class);
	private UserMovementDAO userMovementDAO = new UserMovementDAOSQL(UserMovement.class);
	
	
	private UserController() {
		
	}
	
	public static UserController getInstance() {
		return INSTANCE;
	}
	
	public UserDTO userLocator(String username, String password) {
		//TODO Ver como saber si el usuario es super usuario.
		User user = userDAO.findByUsernameAndPassword(username, password);
		UserDTO userDTO = null;
		
		if (user != null) {
			userDTO = new UserDTO();
			userDTO.setIdUser(user.getId());
			userDTO.setUsername(username);
			userDTO.setPassword(password);
		}
		
		return userDTO;
	}
	
	public void registerUser(String username, String password, UserDTO superUser) {
		User user = new User.Builder().setUsername(username)
									  .setPassword(password)
									  .build();
		
		User sUser = new User.Builder().setUsername(superUser.getUsername())
									  	   .setPassword(superUser.getPassword())
									  	   .build();
		sUser.setId(superUser.getIdUser());
		
		userDAO.save(user);
		userMovementDAO.save(new UserMovement.Builder().setAction(UserMovement.Action.ALTA)
													   .setUser(sUser)
													   .setUserModified(user)
													   .build());
	}
	
	public Boolean validate(String username, String password) {
		//TODO ¿Validar formato de los datos ingresados?
		Boolean valid = true;
		User user = userDAO.findByUsernameAndPassword(username, password);
		
		if (user != null) {
			valid = false;
		}
		
		return valid;
	}
	
	
	/*public UserDTO findUser(Integer idUser) {
		userDAO.find(idUser)
	}*/
	
	/*public void modifyUser(Integer idUser) {
		userDAO.update()
	}*/
	

	

	
	
	
}
