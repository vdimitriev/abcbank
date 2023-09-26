package mk.dmt.abc.exception;

import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ApiException {

    public CustomerNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND, "Not Found", "Customer not found");
    }
}
