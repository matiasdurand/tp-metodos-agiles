  
package DAOs;

import DTOs.TaxPayerDTO;
import domain.TypeId;

public interface TaxPayerDAO extends GenericDAO<TaxPayerDTO,Integer> {
	
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId);
	
}