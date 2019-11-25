package dao;

import java.util.List;

import audit.UserMovement;

public interface UserMovementDAO extends GenericDAO<UserMovement, Integer> {
	
	public List<UserMovement> findAllUserMovements();
	

}
