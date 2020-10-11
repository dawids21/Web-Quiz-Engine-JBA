package engine;

import java.util.ArrayList;

public class QuizRepository {

    private final ArrayList<Quiz> repository;

    public QuizRepository() {
        repository = new ArrayList<>();
    }

    public int add(Quiz quiz) {
        quiz.setId(repository.size() + 1);
        repository.add(quiz);
        return repository.size();
    }

    public Quiz get(int id) {
        return repository.stream()
                         .filter(quiz -> quiz.getId() == id)
                         .findFirst()
                         .orElseThrow(QuizNotFoundException::new);
    }

    public void remove(int id) {
        repository.stream()
                  .filter(quiz -> quiz.getId() == id)
                  .findFirst()
                  .ifPresent(repository::remove);
    }
}
