package engine.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ErrorsExtractor {

    public ErrorsExtractor() {
    }

    public Map<String, String> getExceptionErrorsMap(
             ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getConstraintViolations()
                 .forEach(constraintViolation -> {
                     var fieldName = constraintViolation.getPropertyPath()
                                                        .toString();
                     var value = constraintViolation.getInvalidValue()
                                                    .toString();
                     var message = constraintViolation.getMessage();
                     errors.put(fieldName, value);
                     errors.put("message", message);
                 });
        return errors;
    }

    public Map<String, String> getExceptionErrorMap(
             MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<String, String>();
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> {
                     var fieldName = ((FieldError) error).getField();
                     var errorMessage = error.getDefaultMessage();
                     errors.put(fieldName, errorMessage);
                 });
        return errors;
    }
}