package mk.dmt.abc.exception;

import org.springframework.security.core.AuthenticationException;

public class MissingJwtTokenException extends AuthenticationException {

    public MissingJwtTokenException(String msg) {
        super(msg);
    }
}