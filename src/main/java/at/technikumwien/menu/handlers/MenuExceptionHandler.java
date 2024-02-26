package at.technikumwien.menu.handlers;

import org.springframework.http.ResponseEntity;

public abstract class MenuExceptionHandler {
    MenuExceptionHandler successor;

    public void setSuccessor(MenuExceptionHandler eh) {
        this.successor = eh;
    }

    public abstract ResponseEntity<Object> handleException(Exception ex) throws Exception;

}
