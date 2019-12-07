package controllers;

import java.util.ArrayList;
import java.util.List;

import audit.UserMovement;

import dao.UserDAO;
import dao.UserDAOSQL;
import dao.UserMovementDAO;
import dao.UserMovementDAOSQL;
import domain.User;

import dto.TitularDTO;
import dto.UserDTO;

import validators.CompositeValidator;
import validators.IdValidator;
import validators.NameValidator;
import validators.Validator;

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
		userDTO.setSuperUser(user.getSuperUser());
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
	
	/**
	 * Este metodo valida los datos del nuevo usuario ingresados por pantalla.
	 * Verifica que los mismos no pertenezcan a otro usuario y que tengan un formato valido.
	 * @param userDTO datos del usuario a dar de alta, provenientes de GUI.
	 * @return true o false, true si los datos son validos, falso en caso contrario.
	 */
	public Boolean validate(UserDTO userDTO) {
		Boolean valid = true;
		
		User user = findUserByUsername(userDTO.getUsername());
		
		if (user != null) {
			valid = false;
		}
		else {
			TitularDTO titularDTO = new TitularDTO();
			titularDTO.setTypeId(userDTO.getTypeId());
			titularDTO.setPersonalId(userDTO.getPersonalId());
			titularDTO.setName(userDTO.getName());
			titularDTO.setSurname(userDTO.getSurname());
			
			List<Validator<String, TitularDTO>> validators = new ArrayList<Validator<String, TitularDTO>>();
			
			validators.add(new IdValidator());
			validators.add(new NameValidator());;
			
			Validator<String, TitularDTO> validator = new CompositeValidator<String, TitularDTO>(validators);
			
			List<String> errors = validator.validate(titularDTO);
			
			if (!errors.isEmpty()) {
				valid = false;
			}
		}
		
		return valid;
	}
	
	private User findUserByUsername(String username) {
		return userDAO.findByUsername(username);
	}
	
	public UserDTO userLocatorByUsername(String username) {
		return buildUserDTO(userDAO.findByUsername(username));
	}
	
	/**
	 * Este metodo registra un nuevo usuario y llama al registo del movimiento
	 * asociado al alta del nuevo usuario.
	 * Perdura la informacion en la base de datos.
	 * @param userDTO datos del usuario a dar de alta, provenientes de GUI.
	 * @param superUserDTO datos del super usuario que realiza el alta, proveniente de GUI. 
	 */
	public void registerUser(UserDTO userDTO, UserDTO superUserDTO) {
		User user = buildUser(userDTO);
		
		userDAO.save(user);
		
		registerUserMovement(user, superUserDTO, UserMovement.Action.ALTA);
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
		User superUser = buildUser(superUserDTO);
		
		superUser.setId(superUserDTO.getId());
		
		UserMovement userMovement = new UserMovement.Builder()
				.setAction(action)
				.setUser(superUser)
				.setUserModified(user)
				.build();
		
		userMovementDAO.save(userMovement);
	}
	
	/**
	 * Este metodo modifica los datos de un usuario existente y llama al registro
	 * del movimiento asociado a la modificacion del usuario.
	 * Perdura la informacion en la base de datos.
	 * @param userDTO datos nuevos del usuario, provenientes de GUI.
	 * @param superUserDTO datos del super usuario que realiza el alta, proveniente de GUI. 
	 */
	public void modifyUser(UserDTO userDTO, UserDTO superUserDTO) {
		User modifiedUser = buildUser(userDTO);
		
		modifiedUser.setId(userDTO.getId());
		
		userDAO.update(modifiedUser);

		registerUserMovement(modifiedUser, superUserDTO, UserMovement.Action.MODIFICACION);
	}


}
