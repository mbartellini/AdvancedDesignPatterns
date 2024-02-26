package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicExceptionHandler extends MenuExceptionHandler {

    @Override
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
