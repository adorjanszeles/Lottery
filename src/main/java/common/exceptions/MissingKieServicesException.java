package common.exceptions;

/**
 * Egyedi kivétel kie service-hez
 */

public class MissingKieServicesException extends Exception {
    public MissingKieServicesException(String message) {
        super(message);
    }

    public MissingKieServicesException(Throwable cause) {
        super(cause);
    }

    public MissingKieServicesException(String message, Throwable cause) {
        super(message, cause);
    }

}
