package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IndexOutOfBoundsExceptionHandler extends MenuExceptionHandler {

    @Override
    public ResponseEntity<Object> handleException(Exception ex) {
        if (ex instanceof IndexOutOfBoundsException) {
            return new ResponseEntity<>("Index out of bounds", HttpStatus.BAD_REQUEST);
        } else if (successor != null) {
            return successor.handleException(ex);
        }
        return null;
    }

}
