package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;

@Component
@RestController
@RequestMapping(path = "/api")
public class WebQuizController {

    private final QuizRepository repository;
    private final QuizChecker quizChecker;
    private final QuizService quizService;

    @Autowired
    public WebQuizController(QuizRepository repository, QuizChecker quizChecker,
                             QuizService quizService) {
        this.repository = repository;
        this.quizChecker = quizChecker;
        this.quizService = quizService;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        var quizId = repository.add(quiz);
        quiz.setId(quizId);
        return quiz;
    }

    @GetMapping(path = "/quizzes")
    public HashSet<Quiz> getAllQuizzes() {
        return repository.getAll();
    }

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return repository.get(id)
                         .orElseThrow(
                                  () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                    "Quiz not found"));
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public AnswerFeedback solveQuiz(@PathVariable int id, @RequestParam int answer) {
        return new AnswerFeedback(quizChecker.isAnswerCorrect(id, answer)
                                             .orElseThrow(
                                                      () -> new ResponseStatusException(
                                                               HttpStatus.NOT_FOUND,
                                                               "Quiz not found")));
    }
}