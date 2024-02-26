package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class IndexOutOfBoundsExceptionHandler extends MenuExceptionHandler {

    private static IndexOutOfBoundsExceptionHandler instance;

    private IndexOutOfBoundsExceptionHandler() {

    }

    public static IndexOutOfBoundsExceptionHandler getInstance() {
        if (instance == null)
            instance = new IndexOutOfBoundsExceptionHandler();
        return instance;
    }

    @Override
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        if (ex instanceof IndexOutOfBoundsException) {
            return new ResponseEntity<>("Index out of bounds", HttpStatus.BAD_REQUEST);
        } else if (successor != null) {
            return successor.handleException(ex);
        }
        return null;
    }

}
