package engine.quiz;

import engine.quiz.models.AnswerFeedback;
import engine.quiz.models.CompletionDto;
import engine.quiz.models.QuizDto;
import engine.utils.ErrorsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

@Component
@RestController
@RequestMapping(path = "/api")
public class QuizRestController {

    private final QuizService quizService;
    private final ErrorsExtractor errorsExtractor;

    @Autowired
    public QuizRestController(QuizService quizService, ErrorsExtractor errorsExtractor) {
        this.quizService = quizService;
        this.errorsExtractor = errorsExtractor;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public QuizDto addQuiz(@Valid @RequestBody QuizDto quiz) {
        return quizService.addQuiz(quiz);
    }

    @GetMapping(path = "/quizzes")
    public Page<QuizDto> getAllQuizzes(@RequestParam(defaultValue = "0") int page) {
        return quizService.getAllQuizzes(page);
    }

    @GetMapping(path = "/quizzes/{id}")
    public QuizDto getQuiz(@PathVariable long id) {
        return quizService.getQuizById(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public AnswerFeedback solveQuiz(@PathVariable long id,
                                    @Valid @RequestBody Map<String, Set<Integer>> body) {
        return new AnswerFeedback(quizService.checkAnswer(id, body.get("answer")));
    }

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id) {
        quizService.deleteQuizById(id);
    }

    @GetMapping("/quizzes/completed")
    public Page<CompletionDto> getCompleted(@RequestParam(defaultValue = "0") int page) {
        return quizService.getCompleted(page);
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