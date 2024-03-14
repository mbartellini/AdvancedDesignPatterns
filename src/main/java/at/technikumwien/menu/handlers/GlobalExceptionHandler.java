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
        TimeoutExceptionHandler third = TimeoutExceptionHandler.getInstance();
        MenuExceptionHandler fourth = BasicExceptionHandler.getInstance();
        first.setSuccessor(second);
        second.setSuccessor(third);
        third.setSuccessor(fourth);
        chainOfResponsibility = first;
    }

    @ExceptionHandler({
            Exception.class,
            RuntimeException.class
    })
    public ResponseEntity<Object> handleException(Exception ex) throws Exception {
        return chainOfResponsibility.handleException(ex);
    }
}
