package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Component
@RestController
@RequestMapping(path = "/api")
public class WebQuizController {

    private final QuizRepository repository;

    @Autowired
    public WebQuizController(QuizRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        var quizId = repository.add(quiz);
        quiz.setId(quizId);
        return quiz;
    }

    @GetMapping(path = "/quizzes")
    public ArrayList<Quiz> getAllQuizzes() {
        return repository.getAll();
    }

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return repository.get(id)
                         .orElseThrow(QuizNotFoundException::new);
    }
}