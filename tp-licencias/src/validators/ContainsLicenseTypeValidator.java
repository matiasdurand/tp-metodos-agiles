package validators;

import java.util.List;
import java.util.stream.Collectors;
import domain.Titular;

public class ContainsLicenseTypeValidator implements Validator<Titular> {

	@Override
	public List<String> validate(Titular titular) {
		return titular.getLicenses().stream().map(l -> l.getLicenseType().toString()).collect(Collectors.toList());
	}
}
