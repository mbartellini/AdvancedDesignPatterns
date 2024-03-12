package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

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
    @ExceptionHandler({TimeoutException.class})
    @ResponseStatus(HttpStatus.REQUEST_TIMEOUT)
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        if (ex instanceof TimeoutException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.REQUEST_TIMEOUT);
        } else if (successor != null) {
            return successor.handleException(ex);
        }
        return null;
    }


}
