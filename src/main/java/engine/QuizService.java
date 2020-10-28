package engine;

import engine.models.QuizDTOWithoutAnswer;
import engine.models.QuizInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizChecker quizChecker;
    private final ObjectMapperUtils objectMapperUtils;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizChecker quizChecker,
                       ObjectMapperUtils objectMapperUtils) {
        this.quizRepository = quizRepository;
        this.quizChecker = quizChecker;
        this.objectMapperUtils = objectMapperUtils;
    }

    public QuizDTOWithoutAnswer addQuiz(QuizInputDTO quizInput) {
        var quiz = objectMapperUtils.mapQuizInputDTOToQuiz(quizInput);
        var quizEntity = quizRepository.save(quiz);
        return objectMapperUtils.mapQuizToQuizDTOWithoutAnswer(quizEntity);
    }

    public List<QuizDTOWithoutAnswer> getAllQuizzes() {
        return StreamSupport.stream(quizRepository.findAll()
                                                  .spliterator(), false)
                            .map(objectMapperUtils::mapQuizToQuizDTOWithoutAnswer)
                            .collect(Collectors.toList());
    }

    public QuizDTOWithoutAnswer getQuiz(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "Quiz not found"));
        return objectMapperUtils.mapQuizToQuizDTOWithoutAnswer(quiz);
    }

    public boolean isAnswerCorrect(long id, Set<Integer> answer) {
        return quizChecker.checkAnswer(id, answer);
    }
}
