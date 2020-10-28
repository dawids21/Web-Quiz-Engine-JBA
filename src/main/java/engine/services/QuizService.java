package engine.services;

import engine.models.QuizDTOWithoutAnswer;
import engine.models.QuizInputDTO;
import engine.utils.ObjectMapper;
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
    private final ObjectMapper objectMapper;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizChecker quizChecker,
                       ObjectMapper objectMapper) {
        this.quizRepository = quizRepository;
        this.quizChecker = quizChecker;
        this.objectMapper = objectMapper;
    }

    public QuizDTOWithoutAnswer addQuiz(QuizInputDTO quizInput) {
        var quiz = objectMapper.mapQuizInputDTOToQuiz(quizInput);
        quiz.getAnswers()
            .forEach(answer -> answer.setQuiz(quiz));
        quiz.getOptions()
            .forEach(option -> option.setQuiz(quiz));
        var quizEntity = quizRepository.save(quiz);
        return objectMapper.mapQuizToQuizDTOWithoutAnswer(quizEntity);
    }

    public List<QuizDTOWithoutAnswer> getAllQuizzes() {
        return StreamSupport.stream(quizRepository.findAll()
                                                  .spliterator(), false)
                            .map(objectMapper::mapQuizToQuizDTOWithoutAnswer)
                            .collect(Collectors.toList());
    }

    public QuizDTOWithoutAnswer getQuiz(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "Quiz not found"));
        return objectMapper.mapQuizToQuizDTOWithoutAnswer(quiz);
    }

    public boolean isAnswerCorrect(long id, Set<Integer> answer) {
        return quizChecker.checkAnswer(id, answer);
    }
}
