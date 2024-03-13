package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.TimeoutException;

public class TimeoutExceptionHandler extends MenuExceptionHandler {

    private static TimeoutExceptionHandler instance;

    private TimeoutExceptionHandler() {

    }

    public static TimeoutExceptionHandler getInstance() {
        if (instance == null)
            instance = new TimeoutExceptionHandler();
        return instance;
    }

    @Override
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        if (ex instanceof TimeoutException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
        } else if (successor != null) {
            return successor.handleException(ex);
        }
        return null;
    }


}
