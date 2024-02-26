package at.technikumwien.menu.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

public abstract class MenuExceptionHandler {
    MenuExceptionHandler successor;

    public void setSuccessor(MenuExceptionHandler eh) {
        this.successor = eh;
    }

    public abstract ResponseEntity<Object> handleException(Exception ex);

}
