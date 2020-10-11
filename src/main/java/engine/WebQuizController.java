package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@RequestMapping(path = "/api")
public class WebQuizController {

    private final QuizRepository repository;

    @Autowired
    public WebQuizController(QuizRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/quiz")
    public Quiz getQuiz() {
        return new Quiz("The Java Logo", "What is depicted on the Java logo?",
                        new String[]{"Robot", "Tea leaf", "Cup of Coffee", "Bug"}, 2);
    }

    @PostMapping(path = "/quiz")
    public AnswerFeedback answer(@RequestParam int answer) {
        if (answer == 2) {
            return new AnswerFeedback(true);
        } else {
            return new AnswerFeedback(false);
        }
    }

    @PostMapping(path = "/quizzes", consumes = "application/json")
    public Quiz addQuiz(@RequestBody Quiz quiz) {
        var quizId = repository.add(quiz);
        quiz.setId(quizId);
        return quiz;
    }

    @GetMapping(path = "/quizzes/{id}")
    public Quiz getQuiz(@PathVariable int id) {
        return repository.get(id);
    }
}