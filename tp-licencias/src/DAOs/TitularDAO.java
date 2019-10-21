package DAOs;

import domain.Titular;

public interface TitularDAO extends GenericDAO<Titular, Integer> {

	public Titular findByPersonalId(Long personalId);
	/*Borrar esto
	 * Aca se declaran los metodos especificos para buscar un titular.. 
	 */
}
