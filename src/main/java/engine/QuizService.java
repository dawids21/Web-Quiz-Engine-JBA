package engine;

import engine.models.Quiz;
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
