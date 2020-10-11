package engine;

import java.util.ArrayList;

public class QuizRepository {

    private final ArrayList<Quiz> repository;

    public QuizRepository() {
        repository = new ArrayList<>();
    }

    public int add(Quiz quiz) {
        repository.add(quiz);
        return repository.size();
    }

    public Quiz get(int id) {
        return repository.get(id - 1);
    }

    public void remove(int id) {
        repository.remove(id - 1);
    }
}
