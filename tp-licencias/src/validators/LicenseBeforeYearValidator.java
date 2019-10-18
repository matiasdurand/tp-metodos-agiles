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

public class LicenseBeforeYearValidator implements Validator<Titular> {

	@Override
	public List<String> validate(Titular titular) {
		List<String> invalidLicensesTypes = new ArrayList<String>();
		List<License> licensesTypeB = titular.getLicenses().stream().filter(l -> l.getLicenseType().equals(LicenseType.B))
				.collect(Collectors.toList());
		
		if(licensesTypeB.isEmpty()) {
			invalidLicensesTypes.add("C");
			invalidLicensesTypes.add("D");
			invalidLicensesTypes.add("E");
		}
		else {
			LocalDate actual = LocalDate.now();
			List<License> subListLicenses = licensesTypeB.stream()
					.filter(l -> ChronoUnit.YEARS.between(l.getEmisionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),actual)>1)
						.collect(Collectors.toList());
			
			if(subListLicenses.isEmpty()) {
				invalidLicensesTypes.add("C");
				invalidLicensesTypes.add("D");
				invalidLicensesTypes.add("E");
			}
		}

		return invalidLicensesTypes;
	}


}
