package controllers;

import dao.TaxPayerDAO;
import dao.TaxPayerDAOSQL;
import domain.TypeId;
import dto.TaxPayerDTO;

public class TaxPayerController {
	
	private static TaxPayerController _INSTANCE = new TaxPayerController();
	private TaxPayerDAO taxPayerDAO = new TaxPayerDAOSQL(); 
	
	private TaxPayerController () { 
	}
	
	public static TaxPayerController getInstance() { 
		return _INSTANCE;
	}	
	
	public TaxPayerDTO taxPayerLocator(TypeId typeId, Long personalId){
		
		return taxPayerDAO.findByPersonalId(typeId, personalId);
			
	}

	
	
	
	
}
