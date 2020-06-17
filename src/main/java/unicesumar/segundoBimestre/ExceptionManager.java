package unicesumar.segundoBimestre;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionManager {
	
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(unicesumar.segundoBimestre.cliente.NotFoundException.class)
	public void manage(unicesumar.segundoBimestre.cliente.NotFoundException e) {
		
	}
}
