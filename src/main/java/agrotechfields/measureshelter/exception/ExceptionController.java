package agrotechfields.measureshelter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public String handleArgumentNotValid(MethodArgumentNotValidException exception) {

    FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);

    return "Error: " + fieldError.getField() + ", Message: " + fieldError.getDefaultMessage();
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ObjectNotFoundException.class)
  public String handleObjectNotFound(ObjectNotFoundException exception) {
    return exception.getMessage();
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ObjectAlreadyExistsException.class)
  public String handleObjectAlreadyExists(ObjectAlreadyExistsException exception) {
    return exception.getMessage();
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler(IsleNotWorkingException.class)
  public String handleIsleNotWorking(IsleNotWorkingException exception) {
    return exception.getMessage();
  }
}
