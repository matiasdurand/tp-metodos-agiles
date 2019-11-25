package controllers;

import dao.TaxPayerDAO;
import dao.TaxPayerDAOSQL;
import domain.TypeId;
import dto.TaxPayerDTO;

/**
 * Esta es la clase controladora de Contribuyente. Singleton.
 * Implementa los metodos que seran llamados desde la GUI.
 * Se comunica con la capa DAO de contribuyentes para recuperar los datos de la base 
 * de datos de contribuyentes.
 * @author Juan Suppicich & Matias Durand.
 *
 */
public class TaxPayerController {
	
	private static TaxPayerController _INSTANCE = new TaxPayerController();
	private TaxPayerDAO taxPayerDAO = new TaxPayerDAOSQL(TaxPayerDTO.class); 
	
	private TaxPayerController () { 
	}
	
	public static TaxPayerController getInstance() { 
		return _INSTANCE;
	}	
	
	/**
	 * Este metodo localiza un contribuyente en la base de datos de contribuyentes
	 * mediante el tipo y numero de documento. Delega la busqueda a la capa DAO. 
	 * @param typeId tipo de documento
	 * @param personalId numero de documento
	 * @return el contribuyente que posee dicho tipo y numero de documento o null en el caso
	 * de que el contribuyente no exista.
	 */
	public TaxPayerDTO taxPayerLocator(TypeId typeId, Long personalId){
		
		return taxPayerDAO.findByPersonalId(typeId, personalId);
			
	}

	
	
	
	
}
