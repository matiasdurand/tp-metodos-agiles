package DAOs;

import DTOs.TaxPayerDTO;
import domain.TypeId;

public class TaxPayerDAOSQL extends GenericDAOSQL<TaxPayerDTO,Integer> implements TaxPayerDAO {

	@Override
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		return null;
	}

}