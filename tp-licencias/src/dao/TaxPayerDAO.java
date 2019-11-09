  
package dao;

import java.util.List;

import domain.TypeId;
import dto.TaxPayerDTO;

/**
 * Esta interfaz define los metodos para recuperar los datos correspondientes a un contribuyente 
 * de la base de datos de contribuyentes.
 * @author 
 *
 */
public interface TaxPayerDAO {
	
	public TaxPayerDTO find(int id);
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId);
	public List<TaxPayerDTO> findAllTaxPayers();
	
}