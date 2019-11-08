package dao;

import domain.Titular;
import domain.TypeId;

public interface TitularDAO extends GenericDAO<Titular, Integer> {

	public Titular findByPersonalId(TypeId typeId, Long personalId); 
	
	/*Borrar esto
	 * Aca se declaran los metodos especificos para buscar un titular.. 
	 */
}
