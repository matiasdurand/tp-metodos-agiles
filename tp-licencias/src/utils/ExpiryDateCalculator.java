package useful;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import controllers.TitularController;
import domain.Titular;
import domain.TypeId;

public class ExpiricyDateCalculator {
	
	private static ExpiricyDateCalculator _INSTANCE = new ExpiricyDateCalculator();
	
	private ExpiricyDateCalculator() {
		
	}
	
	public static ExpiricyDateCalculator getInstance() {
		return _INSTANCE;
	}
	
	public Date calculateExpiricyDate(TypeId typeId, Long personalId, Date birthdate) {

		LocalDate birthdateLocalDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate actual = LocalDate.now();
		Long age = ChronoUnit.YEARS.between(birthdateLocalDate, actual);
		int validity = 0;
		
		if(age<21) {
			
			Titular titular = TitularController.getInstance().findTitularByPersonalId(typeId, personalId);
			
			if(titular == null) {
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
