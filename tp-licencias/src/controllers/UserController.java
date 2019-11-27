package controllers;

import audit.UserMovement;
import dao.UserDAO;
import dao.UserDAOSQL;
import dao.UserMovementDAO;
import dao.UserMovementDAOSQL;
import domain.User;
import dto.UserDTO;

/**
 * Esta es la clase controladora de Usuarios. Singleton.
 * Implementa los metodos que seran llamados desde la GUI.
 * Se comunica con la capa DAO para recuperar, persistir o actualizar datos. 
 * @author Matias Durand
 */
public class UserController {

	public static UserController INSTANCE = new UserController();
	private UserDAO userDAO = new UserDAOSQL(User.class);
	private UserMovementDAO userMovementDAO = new UserMovementDAOSQL(UserMovement.class);
	
	
	private UserController() {
		
	}
	
	public static UserController getInstance() {
		return INSTANCE;
	}
	                                                                                
	/**
	 * Este metodo localiza un usuario en la base de datos teniendo en cuenta el
	 * nombre de usuario y la clave del mismo.
	 * Delega la busqueda a la capa DAO.
	 * @param username nombre del usuario a localizar.
	 * @param password clave del usuario a localizar.
	 * @return DTO con los datos del usuario localizado o null si los datos recibidos
	 * no coinciden con algun usuario.
	 */
	public UserDTO userLocator(String username, String password) {
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
	
	/**
	 * Este metodo registra un nuevo usuario y el movimiento asociado al alta del mismo.
	 * Perdura la informacion en la base de datos.
	 * @param userDTO datos del usuario a dar de alta, provenientes de GUI.
	 * @param superUserDTO datos del super usuario que realiza el alta, proveniente de GUI. 
	 */
	public void registerUser(UserDTO userDTO, UserDTO superUserDTO) {
		User newUser = new User.Builder().setUsername(userDTO.getUsername())
									  .setPassword(userDTO.getPassword())
									  .setSuperUser(userDTO.getSuperUser())
									  .build();
		
		registerUserMovement(newUser, superUserDTO, UserMovement.Action.ALTA);
		
		userDAO.save(newUser);
	}
	
	/**
	 * Este metodo valida los datos del nuevo usuario ingresados por pantalla.
	 * Verifica que los mismos no pertenezcan a otro usuario.
	 * @param userDTO datos del usuario a dar de alta, provenientes de GUI.
	 * @return true o false, true si los datos son validos, falso en caso contrario.
	 */
	public Boolean validate(UserDTO userDTO) {
		Boolean valid = true;
		User user = userDAO.findByUsernameAndPassword(userDTO.getUsername(), userDTO.getPassword());
		
		if (user != null) {
			valid = false;
		}
		
		return valid;
	}
	
	/**
	 * Este metodo registra los datos del movimiento asociado al alta
	 * o modificacion de datos de un usuario, para posteriores auditorias del sistema.
	 * Perdura la informacion en la base de datos.
	 * @param user usuario a dar de alta o usuario existente con datos modificados.
	 * @param superUserDTO datos del super usuario que realiza el alta o modificacion
	 * de los datos de un usuario.
	 * @param action representa el tipo de accion que se esta realizando.
	 */
	private void registerUserMovement(User user, UserDTO superUserDTO, UserMovement.Action action) {
		
		User superUser = new User.Builder().setUsername(superUserDTO.getUsername())
									   .setPassword(superUserDTO.getPassword())
									   .setSuperUser(superUserDTO.getSuperUser())
									   .build();
		
		superUser.setId(superUserDTO.getIdUser());
		
		UserMovement userMovement = new UserMovement.Builder().setAction(action)
				   											  .setUser(superUser)
				   											  .setUserModified(user)
				   											  .build();
		
		userMovementDAO.save(userMovement);
		
	}

}
