package dao;

import domain.TypeId;
import dto.TaxPayerDTO;

/**
 * Esta interfaz define los metodos para recuperar los datos correspondientes a un contribuyente 
 * de la base de datos de contribuyentes.
 * @author 
 *
 */
public interface TaxPayerDAO {
	
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId);
	
}
