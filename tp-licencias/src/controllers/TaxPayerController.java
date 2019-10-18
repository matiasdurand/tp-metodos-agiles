package controllers;

import DTOs.TaxPayerDTO;
import domain.TypeId;

public class TaxPayerController {
	
	private static TaxPayerController _INSTANCE = new TaxPayerController();
	private TaxPayerDAO taxPayerDAO = new TaxPayerDAOSQL(); 
	
	private TaxPayerController () { 
	}
	
	public static TaxPayerController getInstance() { 
		return _INSTANCE;
	}	
	
	public void taxPayerLocator(TypeId typeId, Long id){
		
		TaxPayerDTO taxPayerDTO = taxPayerDAO.search(typeId, id); 
		
		if(taxPayerDTO == null) {
			//configurar popup adverencia de que no existe el contribuyente
		}
		else { 
			//mandar dto contribuyente para cargar datos no editables
		}
		
	}

}
