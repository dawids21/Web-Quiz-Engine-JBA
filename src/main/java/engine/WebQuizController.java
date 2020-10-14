package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Quiz addQuiz(@RequestBody Quiz quiz) {
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
        return quizService.getQuiz(id)
                          .orElseThrow(
                                   () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                     "Quiz not found"));
    }

    @PostMapping(path = "/quizzes/{id}/solve")
    public AnswerFeedback solveQuiz(@PathVariable int id, @RequestParam int answer) {
        return new AnswerFeedback(quizService.isAnswerCorrect(id, answer)
                                             .orElseThrow(
                                                      () -> new ResponseStatusException(
                                                               HttpStatus.NOT_FOUND,
                                                               "Quiz not found")));
    }
}