package mk.dmt.abc.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(final CustomerNotFoundException e) {
        return buildResponseEntity(HttpStatus.NOT_FOUND,
                "not_found",
                "Customer was not found.");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    protected ResponseEntity<ErrorResponse> handleEntityAlreadyExists(final UsernameAlreadyExistsException e) {
        return buildResponseEntity(HttpStatus.CONFLICT,
                "username_already_exists",
                "username_already_exists");
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(CountryNotAllowedException.class)
    protected ResponseEntity<ErrorResponse> handleEntityAlreadyExists(final CountryNotAllowedException e) {
        return buildResponseEntity(HttpStatus.CONFLICT,
                "country_not_allowed",
                "country_not_allowed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST,
                "bad_request",
                "Method argument validation failed");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(final ConstraintViolationException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST,
                "bad_request",
                "Constraint validation failed");
    }

    private ResponseEntity<ErrorResponse> buildResponseEntity(final HttpStatus status,
                                                              final String error,
                                                              final String description) {
        final ErrorResponse errorResponse = new ErrorResponse(error, description);
        return new ResponseEntity<>(errorResponse, status);
    }

}
