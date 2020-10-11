package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class WebQuizController {

    private final QuizRepository repository;

    @Autowired
    public WebQuizController(QuizRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz() {
        return new Quiz("The Java Logo", "What is depicted on the Java logo?",
                        new String[]{"Robot", "Tea leaf", "Cup of Coffee", "Bug"});
    }

    @PostMapping(path = "/api/quiz")
    public AnswerFeedback answer(@RequestParam int answer) {
        if (answer == 2) {
            return new AnswerFeedback(true);
        } else {
            return new AnswerFeedback(false);
        }
    }
}