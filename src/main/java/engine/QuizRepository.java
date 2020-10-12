package engine;

import java.util.ArrayList;
import java.util.Optional;

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

    public Optional<Quiz> get(int id) {
        return repository.stream()
                         .filter(quiz -> quiz.getId() == id)
                         .findFirst();
    }

    public void remove(int id) {
        repository.stream()
                  .filter(quiz -> quiz.getId() == id)
                  .findFirst()
                  .ifPresent(repository::remove);
    }

    public ArrayList<Quiz> getAll() {
        return new ArrayList<Quiz>(repository);
    }
}
