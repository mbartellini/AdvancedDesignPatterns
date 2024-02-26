package at.technikumwien.menu.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final MenuExceptionHandler chainOfResponsibility;

    public GlobalExceptionHandler() {
        MenuExceptionHandler first = IndexOutOfBoundsExceptionHandler.getInstance();
        MenuExceptionHandler second =UnableToTranslateExceptionHandler.getInstance();
        MenuExceptionHandler last = BasicExceptionHandler.getInstance();
        first.setSuccessor(second);
        second.setSuccessor(last);
        chainOfResponsibility = first;
    }

    @ExceptionHandler({
            Exception.class,
            RuntimeException.class
    })
    public ResponseEntity<Object> handleException(Exception ex) {
        return chainOfResponsibility.handleException(ex);
    }
}
