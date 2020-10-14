package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

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

    public Quiz getQuiz(int id) {
        return quizRepository.get(id)
                             .orElseThrow(() -> new ResponseStatusException(
                                      HttpStatus.NOT_FOUND, "Quiz not found"));
    }

    public boolean isAnswerCorrect(int id, Set<Integer> answer) {
        return quizChecker.checkAnswer(id, answer)
                          .orElseThrow(
                                   () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                                     "Quiz not found"));

    }
}
