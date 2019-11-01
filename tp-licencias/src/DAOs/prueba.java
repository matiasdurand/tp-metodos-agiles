package DAOs;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import domain.BloodType;
import domain.Titular;
import domain.TypeId;

public class prueba {

	
public static void main(String[] args) throws ParseException {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	Titular pepito = new Titular();
	pepito.setAdress("la concha de tu madre 123");
	pepito.setBirthday(dateFormat.parse("10/12/1996"));
	pepito.setBloodType(BloodType.ABpositive);
	pepito.setName("pepito");
	pepito.setOrganDonor(true);
	pepito.setPersonalId((long) 39841740);
	pepito.setSurname("suarez");
	pepito.setTypeId(TypeId.DNI);
	
	TitularDAOSQL T = new TitularDAOSQL(Titular.class);
	T.save(pepito);
	
	
	
	
	
	
}





}
