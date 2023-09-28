package mk.dmt.abc.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends RuntimeException  {

    public UsernameAlreadyExistsException() {
        super();
    }

    public UsernameAlreadyExistsException(String message) {
        super(message);
    }
}
