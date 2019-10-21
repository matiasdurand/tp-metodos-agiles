package validators;

import java.util.List;

public interface Validator <D,T> {
	
	public List<D> validate(T info);

}
