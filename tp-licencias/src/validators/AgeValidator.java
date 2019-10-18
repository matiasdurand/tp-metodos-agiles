package validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import domain.LicenseType;
import domain.Titular;

public class AgeValidator implements Validator<Titular> {
	
	@Override
	public List<String> validate(Titular titular) {
		List<String> invalidLicensesTypes = new ArrayList<String>();
		
		LocalDate birthday = titular.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		LocalDate actual = LocalDate.now();
		Long age = ChronoUnit.YEARS.between(birthday, actual);
	
		if(age < 21) {
			invalidLicensesTypes.add("C");
			invalidLicensesTypes.add("D");
			invalidLicensesTypes.add("E");
		}
		else {
			if(age > 65) {
				
				List<LicenseType> licenseTypes = titular.getLicenses().stream().map(l -> l.getLicenseType()).collect(Collectors.toList());
				
				if(!licenseTypes.contains(LicenseType.C) && !licenseTypes.contains(LicenseType.D) && !licenseTypes.contains(LicenseType.E)) { 
					
					invalidLicensesTypes.add("C");
					invalidLicensesTypes.add("D");
					invalidLicensesTypes.add("E");
				}
			}
		}
		
		return invalidLicensesTypes;
	}
	
	
	

}
