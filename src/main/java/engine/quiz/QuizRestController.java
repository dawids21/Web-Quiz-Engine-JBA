package engine.quiz;

import engine.account.services.CurrentAccountService;
import engine.quiz.models.AnswerFeedback;
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
    private final QuizChecker quizChecker;
    private final ErrorsExtractor errorsExtractor;
    private final CurrentAccountService currentAccountService;

    @Autowired
    public QuizRestController(QuizService quizService, QuizChecker quizChecker,
                              ErrorsExtractor errorsExtractor,
                              CurrentAccountService currentAccountService) {
        this.quizService = quizService;
        this.quizChecker = quizChecker;
        this.errorsExtractor = errorsExtractor;
        this.currentAccountService = currentAccountService;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public QuizDto addQuiz(@Valid @RequestBody QuizDto quiz) {
        var account = currentAccountService.getCurrentAccount();
        return quizService.addQuiz(quiz, account.getEmail());
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
        return new AnswerFeedback(quizChecker.checkAnswer(id, body.get("answer")));
    }

    @DeleteMapping("/quizzes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable long id) {
        quizService.deleteQuizById(id);
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