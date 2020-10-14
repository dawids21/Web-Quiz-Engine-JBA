package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class QuizChecker {

    private final QuizRepository repository;

    @Autowired
    public QuizChecker(QuizRepository repository) {
        this.repository = repository;
    }

    public Optional<Boolean> checkAnswer(int id, int answer) {
        return repository.get(id)
                         .map(quiz -> quiz.getAnswer() == answer);
    }
}
