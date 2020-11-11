package engine.quiz;

import engine.account.AccountRepository;
import engine.account.CurrentAccountService;
import engine.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class QuizDao {

    private final QuizRepository quizRepository;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;

    private final CurrentAccountService currentAccountService;

    @Autowired
    public QuizDao(QuizRepository quizRepository, AccountRepository accountRepository,
                   ObjectMapper objectMapper,
                   CurrentAccountService currentAccountService) {
        this.quizRepository = quizRepository;
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
        this.currentAccountService = currentAccountService;
    }

    public QuizWithoutAnswerDto addQuiz(QuizInputDto quizInput, String accountEmail) {
        var quiz = objectMapper.mapQuizInputDTOToQuiz(quizInput);
        quiz.getAnswers()
            .forEach(answer -> answer.setQuiz(quiz));

        quiz.getOptions()
            .forEach(option -> option.setQuiz(quiz));

        var accountEntity = accountRepository.findByEmail(accountEmail)
                                             .orElseThrow();
        accountEntity.addQuiz(quiz);

        var quizEntity = quizRepository.save(quiz);
        return objectMapper.mapQuizToQuizDTOWithoutAnswer(quizEntity);
    }

    public List<QuizWithoutAnswerDto> getAllQuizzes() {
        return StreamSupport.stream(quizRepository.findAll()
                                                  .spliterator(), false)
                            .map(objectMapper::mapQuizToQuizDTOWithoutAnswer)
                            .collect(Collectors.toList());
    }

    public QuizWithoutAnswerDto getQuizById(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "Quiz not found"));
        return objectMapper.mapQuizToQuizDTOWithoutAnswer(quiz);
    }

    public void deleteQuizById(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "Quiz not found"));
        if (!quiz.getOwner()
                 .getEmail()
                 .equals(currentAccountService.getCurrentAccount()
                                              .getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                                              "Current user is not the quiz owner");
        }

        quiz.getOwner()
            .removeQuiz(quiz);
        quizRepository.delete(quiz);
    }
}
