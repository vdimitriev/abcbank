package mk.dmt.abc.exception;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class ApiException extends RuntimeException {

    private final HttpStatus status;

    private final String errorCode;

    private String errorDescription;

    public ApiException(String message, HttpStatus status, String errorCode, String errorDescription) {
        this(message, null, status, errorCode, errorDescription);
    }

    public ApiException(String message, Throwable cause, HttpStatus status, String errorCode, String errorDescription) {
        this(message, cause, status, errorCode);
        Objects.requireNonNull(errorDescription, "errorDescription");
        this.errorDescription = errorDescription;
    }

    public ApiException(String message, Throwable cause, HttpStatus status, String errorCode) {
        super(message, cause);
        Objects.requireNonNull(status, "status");
        Objects.requireNonNull(errorCode, "errorCode");
        this.status = status;
        this.errorCode = errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
