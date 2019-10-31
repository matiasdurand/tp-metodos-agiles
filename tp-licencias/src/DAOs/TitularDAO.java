
package DAOs;

import java.util.List;

import domain.Titular;
import domain.TypeId;

public interface TitularDAO extends GenericDAO<Titular, Integer> {

	public Titular findByPersonalId(TypeId typeId, Long personalId);
	List<Titular> findAllTitulars();
	
	/*Borrar esto
	 * Aca se declaran los metodos especificos para buscar un titular.. 
	 */
}