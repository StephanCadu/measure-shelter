package agrotechfields.measureshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjectAlreadyExistsException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ObjectAlreadyExistsException(String message) {
    super(message);
  }
}
