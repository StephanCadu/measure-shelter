package agrotechfields.measureshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class IsleNotWorkingException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public IsleNotWorkingException(String message) {
    super(message);
  }
}
