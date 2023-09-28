package mk.dmt.abc.exception;

public class CountryNotAllowedException extends RuntimeException {

    public CountryNotAllowedException() {
        super();
    }

    public CountryNotAllowedException(String message) {
        super(message);
    }
}
