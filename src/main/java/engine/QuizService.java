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
        //TODO implement addQuiz
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Set<Quiz> getAllQuizzes() {
        //TODO implement getAllQuizzes
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Optional<Quiz> getQuiz(int id) {
        //TODO implement getQuiz
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public Optional<Boolean> isAnswerCorrect(int id, int answer) {
        //TODO implement isAnswerCorrect
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
