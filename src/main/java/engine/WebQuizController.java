package engine;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebQuizController {

    @GetMapping(path = "/api/quiz")
    public Quiz getQuiz() {
        return new Quiz("The Java Logo", "What is depicted on the Java logo?",
                        new String[]{"Robot", "Tea leaf", "Cup of Coffee", "Bug"});
    }
}
