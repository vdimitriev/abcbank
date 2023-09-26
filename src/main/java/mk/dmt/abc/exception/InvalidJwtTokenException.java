package mk.dmt.abc.exception;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtTokenException extends AuthenticationException {

    public InvalidJwtTokenException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidJwtTokenException(String msg) {
        super(msg);
    }
}
