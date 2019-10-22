package validators;

import java.util.ArrayList;
import java.util.List;

import DTOs.LicenseDTO;

public class ObservationValidator implements Validator<String,LicenseDTO> {

	@Override
	public List<String> validate(LicenseDTO info) {
		List<String> errors = new ArrayList<String>(); 
		
		String observation = info.getObservation();

		if(observation == null) {
			errors.add("Lo sentimos, hubo un error.");
		}
		else {
			if(observation.length() > 240) {
				errors.add("La observacion es demasiado larga.");
			}
		}
		
		return errors;
	}

}
