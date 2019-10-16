package validators;

import java.util.List;

public interface Validator <T> {
	
	public List<String> validate(T info);

}
