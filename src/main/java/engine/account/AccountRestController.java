package engine.account;

import engine.account.models.AccountDto;
import engine.utils.ErrorsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

@Component
@RestController
@RequestMapping(path = "/api")
public class AccountRestController {

    private final AccountService accountService;
    private final ErrorsExtractor errorsExtractor = new ErrorsExtractor();

    @Autowired
    public AccountRestController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public String addUser(@Valid @RequestBody AccountDto accountDTO) {
        accountService.addAccount(accountDTO);
        return "{\"success\": true}";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(
             MethodArgumentNotValidException exception) {
        return errorsExtractor.getExceptionErrorMap(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(
             ConstraintViolationException exception) {
        return errorsExtractor.getExceptionErrorsMap(exception);
    }
}