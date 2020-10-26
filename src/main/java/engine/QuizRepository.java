package engine;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;

@Component
public class QuizRepository {

    private final HashMap<Integer, Quiz> repository;
    private int nextId;

    public QuizRepository() {
        repository = new HashMap<>();
        nextId = 1;
    }

    public long add(Quiz quiz) {
        quiz.setId(nextId);
        repository.put(nextId, quiz);
        nextId++;
        return quiz.getId();
    }

    public Optional<Quiz> get(int id) {
        return Optional.ofNullable(repository.get(id));
    }

    public void remove(int id) {
        repository.remove(id);
    }

    public HashSet<Quiz> getAll() {
        return new HashSet<>(repository.values());
    }
}
