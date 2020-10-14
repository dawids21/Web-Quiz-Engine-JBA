package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizChecker quizChecker;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizChecker quizChecker) {
        this.quizRepository = quizRepository;
        this.quizChecker = quizChecker;
    }
}
