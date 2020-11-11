package engine.quiz;

import engine.account.AccountDao;
import engine.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RestController
@RequestMapping(path = "/api")
public class QuizRestController {

    private final QuizService quizService;
    private final AccountDao accountDao;

    @Autowired
    public QuizRestController(QuizService quizService, AccountDao accountDao) {
        this.quizService = quizService;
        this.accountDao = accountDao;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public QuizWithoutAnswerDto addQuiz(@Valid @RequestBody QuizInputDto quiz) {
        //TODO add quiz owner
        return quizService.addQuiz(quiz);
    }

    @GetMapping(path = "/quizzes")
    public List<QuizWithoutAnswerDto> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping(path = "/quizzes/{id}")
    public QuizWithoutAnswerDto getQuiz(@PathVariable long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public AnswerFeedback solveQuiz(@PathVariable long id,
                                    @Valid @RequestBody Map<String, Set<Integer>> body) {
        return new AnswerFeedback(quizService.isAnswerCorrect(id, body.get("answer")));
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public String addUser(@Valid @RequestBody AccountDto accountDTO) {
        accountDao.addAccount(accountDTO);
        return "{\"success\": true}";
    }

    //TODO add delete endpoint

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(
             MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult()
                 .getAllErrors()
                 .forEach(error -> {
                     var fieldName = ((FieldError) error).getField();
                     var errorMessage = error.getDefaultMessage();
                     errors.put(fieldName, errorMessage);
                 });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(
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
}