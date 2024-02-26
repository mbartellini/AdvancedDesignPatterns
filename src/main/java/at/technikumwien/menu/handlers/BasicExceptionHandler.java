package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BasicExceptionHandler extends MenuExceptionHandler {

    private static BasicExceptionHandler instance;

    private BasicExceptionHandler() {

    }

    public static BasicExceptionHandler getInstance() {
        if (instance == null)
            instance = new BasicExceptionHandler();
        return instance;
    }

    @Override
    public ResponseEntity<Object> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
