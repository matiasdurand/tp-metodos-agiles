package utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import controllers.TitularController;
import domain.TypeId;

/**
 * Clase calculadora y contructora de fecha de vencimiento de Licencia. Singleton.
 * Singleton.
 * @author Juan Suppicich & Matias Durand
 *
 */
public class ExpiryDateCalculator {
	
	private static ExpiryDateCalculator _INSTANCE = new ExpiryDateCalculator();
	
	private ExpiryDateCalculator() {
		
	}
	
	public static ExpiryDateCalculator getInstance() {
		return _INSTANCE;
	}
	
	/**
	 * Este metodo calcula y contruye la fecha de expiracion de la licencia en base al tipo,
	 * numero y fecha de nacimiento del titular. 
	 * @param typeId tipo de documento del titular.
	 * @param personalId numero de documento del titular.
	 * @param birthdate fecha de nacimiento del titualar.
	 * @return fecha de vencimiento de la licencia, donde el dia y mes coincide con el dia y mes 
	 * de nacimiento del titular.
	 */
	public Date calculateExpiryDate(TypeId typeId, Long personalId, Date birthdate) {

		LocalDate birthdateLocalDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate actual = LocalDate.now();
		Long age = ChronoUnit.YEARS.between(birthdateLocalDate, actual);
		int validity = 0;
		
		if(age<21) {
		
		Boolean existsTitular = TitularController.getInstance().existsTitular(typeId, personalId);
			//Preguntamos si existe el titular, si no existe se infiere que no poseía ninguna licencia.
			if(existsTitular) {
				validity = 1;
			}
			else {
				validity = 3;
			}
		}
		else {
			if(age>=21 && age<=46) {
				validity = 5;
			}
			else {
				if(age>46 && age<=60) {
					validity = 4;
				}
				else {
					if(age>60 && age<=70) {
						validity = 3;
					}
					else {
						if(age>70) {
							validity = 1;
						}
					}
				}
			}
		}
		
		int year = LocalDate.now().getYear() + validity;
		int month = birthdateLocalDate.getMonthValue();
		int day = birthdateLocalDate.getDayOfMonth();
		
		LocalDate expiricyDate = LocalDate.of(year,month,day);
		
		return Date.from(expiricyDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

	}
	

}
