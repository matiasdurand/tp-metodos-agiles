package controllers;

import dao.UserDAO;
import dao.UserDAOSQL;

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
		
		UserDTO userDTO = null;
		
		User user = userDAO.findByUsernameAndPassword(username, password);
		
		if (user != null) {
			userDTO = buildUserDTO(user);
		}
		
		return userDTO;
	}
	
	private UserDTO buildUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(user.getId());
		userDTO.setUsername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setTypeId(user.getTypeId());
		userDTO.setPersonalId(user.getPersonalId().toString());
		userDTO.setName(user.getName());
		userDTO.setSurname(user.getSurname());
		return userDTO;
	}
	
	public User buildUser(UserDTO userDTO) {
		return new User.Builder()
				.setUsername(userDTO.getUsername())
				.setPassword(userDTO.getPassword())
				.setSuperUser(userDTO.getSuperUser())
				.setTypeId(userDTO.getTypeId())
				.setPersonalId(Long.parseLong(userDTO.getPersonalId()))
				.setName(userDTO.getName())
				.setSurname(userDTO.getSurname())
				.build();
	}

}
