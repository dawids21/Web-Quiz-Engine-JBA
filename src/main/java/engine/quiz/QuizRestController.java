package engine.quiz;

import engine.utils.ErrorsExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
@RestController
@RequestMapping(path = "/api")
public class QuizRestController {

    private final QuizDao quizDao;
    private final QuizChecker quizChecker;
    private final ErrorsExtractor errorsExtractor;

    @Autowired
    public QuizRestController(QuizDao quizDao, QuizChecker quizChecker,
                              ErrorsExtractor errorsExtractor) {
        this.quizDao = quizDao;
        this.quizChecker = quizChecker;
        this.errorsExtractor = errorsExtractor;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public QuizWithoutAnswerDto addQuiz(@Valid @RequestBody QuizInputDto quiz) {
        //TODO add quiz owner
        return quizDao.addQuiz(quiz);
    }

    @GetMapping(path = "/quizzes")
    public List<QuizWithoutAnswerDto> getAllQuizzes() {
        return quizDao.getAllQuizzes();
    }

    @GetMapping(path = "/quizzes/{id}")
    public QuizWithoutAnswerDto getQuiz(@PathVariable long id) {
        return quizDao.getQuizById(id);
    }

    @PostMapping(path = "/quizzes/{id}/solve", consumes = "application/json")
    public AnswerFeedback solveQuiz(@PathVariable long id,
                                    @Valid @RequestBody Map<String, Set<Integer>> body) {
        return new AnswerFeedback(quizChecker.checkAnswer(id, body.get("answer")));
    }

    //TODO add delete endpoint

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