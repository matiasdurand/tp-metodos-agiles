  
package DAOs;

import java.io.Serializable;
import java.util.List;

import domain.TaxPayer;
import domain.TypeId;

public interface TaxPayerDAO {
	
	
	
	void save(TaxPayer contribuyente);
	TaxPayer update (TaxPayer contribuyente);
	TaxPayer find(int id);
	public TaxPayer findByPersonalId(TypeId typeId, Long personalId);
	List<TaxPayer> findAllTaxPayers();
	
}