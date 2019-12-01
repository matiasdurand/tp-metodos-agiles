package validators;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.License;
import domain.LicenseType;
import domain.Titular;

/**
 * Clase validadora concreta de licencia tipo D. 
 * Esta clase valida que un titular este habilitado a obtener dicho tipo de licencia.
 * @author Suppicich Juan & Matias Durand
 *
 */
public class ClassDValidator implements Validator<LicenseType,Titular>  {
	
	@Override
	public List<LicenseType> validate(Titular info) {
		
		List<LicenseType> validTypesToShow = new ArrayList<LicenseType>();
		List<License> licensesTypeD = info.getLicenses().stream()
				.filter(l -> l.getLicenseType().equals(LicenseType.D)).collect(Collectors.toList());
				
		LocalDate birthday = info.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate actual = LocalDate.now();
		Long age = ChronoUnit.YEARS.between(birthday, actual);
		
		if(licensesTypeD.isEmpty()) {
			
			List<License> oneYearOldLicensesTypeB = info.getLicenses().stream()
					.filter(l -> ChronoUnit.YEARS.between(l.getEmisionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),actual)>1
							&& l.getLicenseType().equals(LicenseType.B))
								.collect(Collectors.toList());
			
			if(!oneYearOldLicensesTypeB.isEmpty()) {
				
				if(age>=21 && age<=65) {
					validTypesToShow.add(LicenseType.D);
				}
				else {
					
					if (age>65) {
						
						List<LicenseType> profesionalLicenseTypes = new ArrayList<LicenseType>();
						profesionalLicenseTypes.add(LicenseType.C);
						profesionalLicenseTypes.add(LicenseType.D);
						profesionalLicenseTypes.add(LicenseType.E);
						
						List<License> profesionalLicenses = info.getLicenses().stream()
								.filter(l -> profesionalLicenseTypes.contains(l.getLicenseType())).collect(Collectors.toList());
						
						if(!profesionalLicenses.isEmpty()) {
							validTypesToShow.add(LicenseType.D);
						}
						
					}
					
				}
				
			}
			
		}
		
		return validTypesToShow;
	}

}
