package engine;

public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizChecker quizChecker;

    public QuizService(QuizRepository quizRepository, QuizChecker quizChecker) {
        this.quizRepository = quizRepository;
        this.quizChecker = quizChecker;
    }
}
