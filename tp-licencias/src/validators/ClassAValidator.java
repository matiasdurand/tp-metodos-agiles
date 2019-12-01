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
 * Clase validadora concreta de licencia tipo A. 
 * Esta clase valida que un titular este habilitado a obtener dicho tipo de licencia.
 * @author Suppicich Juan & Matias Durand
 *
 */
public class ClassAValidator implements Validator<LicenseType, Titular> {

	@Override
	public List<LicenseType> validate(Titular info) {
		
		List<LicenseType> validTypesToShow = new ArrayList<LicenseType>();
		List<License> licensesTypeA = info.getLicenses().stream()
				.filter(l -> l.getLicenseType().equals(LicenseType.A)).collect(Collectors.toList());
				
		if(licensesTypeA.isEmpty()) {
			
			LocalDate birthday = info.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate actual = LocalDate.now();

			if(ChronoUnit.YEARS.between(birthday, actual)>17) {
				validTypesToShow.add(LicenseType.A);
			}
		}
		
		return validTypesToShow;
	}

}
