package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class QuizChecker {

    private final QuizRepository repository;

    @Autowired
    public QuizChecker(QuizRepository repository) {
        this.repository = repository;
    }

    public Optional<Boolean> checkAnswer(int id, Set<Integer> answer) {
        return repository.get(id)
                         .map(quiz -> quiz.getAnswer() == answer);
    }
}
