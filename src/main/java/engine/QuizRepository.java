package engine;

import java.util.ArrayList;
import java.util.Optional;

public class QuizRepository {

    private final ArrayList<Quiz> repository;
    private int nextId;

    public QuizRepository() {
        repository = new ArrayList<>();
        nextId = 1;
    }

    public int add(Quiz quiz) {
        quiz.setId(nextId);
        nextId++;
        repository.add(quiz);
        return quiz.getId();
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
