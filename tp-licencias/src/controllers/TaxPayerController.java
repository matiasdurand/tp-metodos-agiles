package controllers;

import DTOs.TaxPayerDTO;
import domain.TypeId;

public class TaxPayerController {
	
	private static TaxPayerController _INSTANCE = null;
	//private TaxPayerDAO taxPayerDAO; 
	
	private TaxPayerController () { 
		//taxPayerDAO = new TaxPayerDAOSQL();
	}
	
	public static TaxPayerController getInstance() { 
		if(_INSTANCE == null) { 
			_INSTANCE = new TaxPayerController();
		}
		return _INSTANCE;
	}	
	
	public void taxPayerLocator(TypeId typeId, Long id){
		
		TaxPayerDTO taxPayerDTO = taxPayerDAO.search(typeId, id); 
		
		if(taxPayerDTO == null) {
			//configurar popup
		}
		else { 
			//mansar dto para cargar datos no editables
		}
		
	}

}
