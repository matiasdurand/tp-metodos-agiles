package dao;

import domain.TypeId;
import dto.TaxPayerDTO;

public interface TaxPayerDAO {
	
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId);
	
}
