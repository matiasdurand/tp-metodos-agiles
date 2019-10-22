package controllers;

import DAOs.TaxPayerDAO;
import DAOs.TaxPayerDAOSQL;
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
	
	public void taxPayerLocator(TypeId typeId, Long personalId){
		
		if(!TitularController.getInstance().existsTitular(typeId, personalId)) {
			
			TaxPayerDTO taxPayerDTO = taxPayerDAO.findByPersonalId(typeId, personalId); 
			
			if(taxPayerDTO != null) {
				// TODO mandar dto contribuyente para cargar datos no editables
			}
			else { 
				// TODO configurar popup adverencia de que no existe el contribuyente y vuelve a la pantalla anterior (emitir licencia)
			}
		}
		else {
			// TODO muestra pop que ya existe y vuelve a la pantalla anterior (emitir licencia)
		}
	}

	
	
	
	
}
