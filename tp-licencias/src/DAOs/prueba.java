package DAOs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import domain.BloodType;
import domain.TaxPayer;
import domain.Titular;
import domain.TypeId;
import domain.User;

public class prueba {

	
public static void main(String[] args) throws ParseException {
	
	/*SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	User Manu = new User();
	Manu.setPassword("macrigato");
	Manu.setUsername("manupa14");
	UserDAOSQL M = new UserDAOSQL(User.class);
	M.save(Manu);
	
	Titular pepito = new Titular();
	pepito.setAdress("la concha de tu madre 123");
	pepito.setBirthday(dateFormat.parse("10/12/1996"));
	pepito.setBloodType(BloodType.ABpositive);
	pepito.setName("pepito");
	pepito.setOrganDonor(true);
	pepito.setPersonalId((long) 39841740);
	pepito.setSurname("suarez");
	pepito.setTypeId(TypeId.DNI);
	pepito.SetUsuarioCreador(Manu);
	
	
	TitularDAOSQL T = new TitularDAOSQL(Titular.class);
	T.save(pepito);*/
	
	UserDAOSQL M = new UserDAOSQL(User.class);
	
	/*User Manu = new User();
	Manu.setPassword("macrigato2");
	Manu.setUsername("manupa19");
	M.save(Manu);
	
	User Lea = new User();
	Lea.setPassword("leannratm");
	Lea.setUsername("leap22");
	M.save(Lea);
	
	User Tito = new User();
	Tito.setPassword("federales");
	Tito.setUsername("tito19");
	M.save(Tito);*/
	
	String nameUsuario = M.findByUsernameAndPassword("manupa14", "macrigato").getUsername();
	String contrasenia = M.findByUsernameAndPassword("manupa14", "macrigato").getPassword();
	System.out.println(nameUsuario);
	System.out.println(contrasenia);
	
	
	
	
	
	
	
	
}





}
