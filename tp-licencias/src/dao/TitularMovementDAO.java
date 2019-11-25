package dao;

import java.util.List;

import audit.TitularMovement;

public interface TitularMovementDAO extends GenericDAO<TitularMovement, Integer> {
	
	public List<TitularMovement> findAllTitularMovements();

}
