package dao;

import domain.Titular;
import domain.TypeId;

/**
 * Esta clase es una implementacion para SQL (utilizando Hibernate) de la interfaz LicenseDAO.
 * @author 
 *
 */
public class TitularDAOSQL extends GenericDAOSQL<Titular,Integer> implements TitularDAO {

	
	public Titular findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
