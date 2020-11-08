package engine.controllers;

import engine.models.AnswerFeedback;
import engine.models.QuizDTOWithoutAnswer;
import engine.models.QuizInputDTO;
import engine.services.QuizService;
import engine.user.UserDTO;
import engine.user.UserService;
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
public class WebQuizController {

    private final QuizService quizService;
    private final UserService userService;

    @Autowired
    public WebQuizController(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public QuizDTOWithoutAnswer addQuiz(@Valid @RequestBody QuizInputDTO quiz) {
        //TODO add quiz owner
        return quizService.addQuiz(quiz);
    }

    @GetMapping(path = "/quizzes")
    public List<QuizDTOWithoutAnswer> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping(path = "/quizzes/{id}")
    public QuizDTOWithoutAnswer getQuiz(@PathVariable long id) {
        return quizService.getQuiz(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public AnswerFeedback solveQuiz(@PathVariable long id,
                                    @Valid @RequestBody Map<String, Set<Integer>> body) {
        return new AnswerFeedback(quizService.isAnswerCorrect(id, body.get("answer")));
    }

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public String addUser(@Valid @RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
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