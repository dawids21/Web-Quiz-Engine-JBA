package engine.quiz;

import engine.account.AccountRepository;
import engine.account.services.CurrentAccountService;
import engine.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class QuizDao {

    private static final int PAGE_SIZE = 10;
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

    public QuizInputDto addQuiz(QuizInputDto quizInput, String accountEmail) {
        var quiz = objectMapper.mapQuizInputDTOToQuiz(quizInput);
        quiz.getAnswers()
            .forEach(answer -> answer.setQuiz(quiz));

        quiz.getOptions()
            .forEach(option -> option.setQuiz(quiz));

        var accountEntity = accountRepository.findByEmail(accountEmail)
                                             .orElseThrow();
        accountEntity.addQuiz(quiz);

        var quizEntity = quizRepository.save(quiz);
        return objectMapper.mapQuizEntityToQuizDTO(quizEntity);
    }

    public Page<QuizInputDto> getAllQuizzes(int page) {
        Pageable paging = PageRequest.of(page, PAGE_SIZE);
        return quizRepository.findAll(paging)
                             .map(objectMapper::mapQuizEntityToQuizDTO);
    }

    public QuizInputDto getQuizById(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "QuizEntity not found"));
        return objectMapper.mapQuizEntityToQuizDTO(quiz);
    }

    public void deleteQuizById(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "QuizEntity not found"));
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
