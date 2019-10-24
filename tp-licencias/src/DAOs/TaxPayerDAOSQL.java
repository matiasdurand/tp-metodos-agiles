package DAOs;

import DTOs.TaxPayerDTO;
import domain.TypeId;

public class TaxPayerDAOSQL extends GenericTaxDAOSQL<TaxPayerDTO,Integer> implements TaxPayerDAO {

	public TaxPayerDAOSQL(Class<TaxPayerDTO> type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		return null;
	}

}