package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizChecker quizChecker;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizChecker quizChecker) {
        this.quizRepository = quizRepository;
        this.quizChecker = quizChecker;
    }

    public int addQuiz(Quiz quiz) {
        return quizRepository.add(quiz);
    }

    public Set<Quiz> getAllQuizzes() {
        return quizRepository.getAll();
    }

    public Optional<Quiz> getQuiz(int id) {
        return quizRepository.get(id);
    }

    public Optional<Boolean> isAnswerCorrect(int id, int answer) {
        return quizChecker.checkAnswer(id, answer);
    }
}
