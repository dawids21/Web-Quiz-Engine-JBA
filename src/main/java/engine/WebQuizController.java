package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
@RestController
@RequestMapping(path = "/api")
public class WebQuizController {

    private final QuizService quizService;

    @Autowired
    public WebQuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz) {
        var quizId = quizService.addQuiz(quiz);
        quiz.setId(quizId);
        return quiz;
    }

    @GetMapping(path = "/quizzes")
    public Set<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return quizService.getQuiz(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public AnswerFeedback solveQuiz(@PathVariable int id,
                                    @Valid @RequestBody SolveJson body) {
        return new AnswerFeedback(quizService.isAnswerCorrect(id, body.getAnswer()));
    }

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
}