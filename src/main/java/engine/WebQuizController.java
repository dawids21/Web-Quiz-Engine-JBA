package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebQuizController {

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