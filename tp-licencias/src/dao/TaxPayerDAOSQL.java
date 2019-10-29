package dao;

import domain.TypeId;
import dto.TaxPayerDTO;

public class TaxPayerDAOSQL implements TaxPayerDAO {

	@Override
	public TaxPayerDTO findByPersonalId(TypeId typeId, Long personalId) {
		// TODO Auto-generated method stub
		//Este metodo debe retornar null en caso que no exista el contribuyente en la BD (Borrar este comentario)
		return null;
	}

}
