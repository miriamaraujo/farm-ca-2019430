package ie.cct.farmca2019430;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2754808759234017613L;
	
	public NotFoundException(String msg) {
		super(msg);
	}

}
