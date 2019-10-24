package DAOs;

import java.util.List;

import domain.Titular;
import domain.TypeId;

public class TitularDAOSQL extends GenericDAOSQL<Titular,Integer> implements TitularDAO {

	
	public TitularDAOSQL(Class<Titular> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}


	public List<Titular> findAllByPersonalId(TypeId typeId, Long personalIdFragment) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Titular findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*Borrar esto
	 * Aca se implementan los metodos especificos declarados en la interface TitularDAO 
	 */
}