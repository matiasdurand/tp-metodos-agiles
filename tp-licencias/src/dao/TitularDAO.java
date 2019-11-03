package dao;

import domain.Titular;
import domain.TypeId;

/**
 * Esta interfaz define los metodos CRUD especificos correspondiente a la entidad Titular.
 * @author 
 *
 */
public interface TitularDAO extends GenericDAO<Titular, Integer> {

	public Titular findByPersonalId(TypeId typeId, Long personalId); 
	
}
