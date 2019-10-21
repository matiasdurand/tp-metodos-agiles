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

public class ClassFValidator implements Validator<LicenseType,Titular> {
	
	@Override
	public List<LicenseType> validate(Titular info) {
		
		List<LicenseType> validTypesToShow = new ArrayList<LicenseType>();
		List<License> licensesTypeF = info.getLicenses().stream()
				.filter(l -> l.getLicenseType().equals(LicenseType.F)).collect(Collectors.toList());
				
		if(licensesTypeF.isEmpty()) {
			
			LocalDate birthday = info.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate actual = LocalDate.now();

			if(ChronoUnit.YEARS.between(birthday, actual)>17) {
				validTypesToShow.add(LicenseType.F);
			}
		}
		
		return validTypesToShow;
	}

}
