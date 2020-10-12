package engine;

import java.util.Optional;

public class QuizChecker {


    private final QuizRepository repository;

    public QuizChecker(QuizRepository repository) {
        this.repository = repository;
    }

    public Optional<Boolean> isAnswerCorrect(int id, int answer) {
        return repository.get(id)
                         .map(quiz -> quiz.getAnswer() == answer);
    }
}
