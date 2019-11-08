package dao;

import domain.Titular;
import domain.TypeId;

public class TitularDAOSQL extends GenericDAOSQL<Titular,Integer> implements TitularDAO {

	
	public Titular findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		//Este metodo debe retornar null en caso que no exista (Borrar este comentario)
		return null;
	}
	
	/*Borrar esto
	 * Aca se implementan los metodos especificos declarados en la interface TitularDAO 
	 */
}
