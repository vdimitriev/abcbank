package mk.dmt.abc.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

    private String error;

    private String description;

    public ErrorResponse() {
    }

    public ErrorResponse(String error, String description) {
        this.error = error;
        this.description = description;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
