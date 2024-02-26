package at.technikumwien.menu.handlers;

import at.technikumwien.menu.exceptions.UnableToTranslateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UnableToTranslateExceptionHandler  extends MenuExceptionHandler {

    @Override
    public ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof UnableToTranslateException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (successor != null) {
            return successor.handleException(ex);
        }
        return null;
    }

}
