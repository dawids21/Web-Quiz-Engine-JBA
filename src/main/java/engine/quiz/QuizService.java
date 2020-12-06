package engine.quiz;

import engine.account.AccountRepository;
import engine.account.services.CurrentAccountService;
import engine.quiz.models.CompletionEntity;
import engine.quiz.models.QuizDto;
import engine.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class QuizService {

    private static final int PAGE_SIZE = 10;
    private final QuizRepository quizRepository;
    private final AccountRepository accountRepository;
    private final ObjectMapper objectMapper;
    private final QuizChecker quizChecker;

    private final CurrentAccountService currentAccountService;

    @Autowired
    public QuizService(QuizRepository quizRepository, AccountRepository accountRepository,
                       ObjectMapper objectMapper, QuizChecker quizChecker,
                       CurrentAccountService currentAccountService) {
        this.quizRepository = quizRepository;
        this.accountRepository = accountRepository;
        this.objectMapper = objectMapper;
        this.quizChecker = quizChecker;
        this.currentAccountService = currentAccountService;
    }

    public QuizDto addQuiz(QuizDto quizInput, String accountEmail) {
        var quiz = objectMapper.mapDtoToEntity(quizInput);
        quiz.getAnswers()
            .forEach(answer -> answer.setQuiz(quiz));

        quiz.getOptions()
            .forEach(option -> option.setQuiz(quiz));

        var accountEntity = accountRepository.findByEmail(accountEmail)
                                             .orElseThrow();
        quiz.setOwner(accountEntity);

        var quizEntity = quizRepository.save(quiz);
        return objectMapper.mapEntityToDto(quizEntity);
    }

    public Page<QuizDto> getAllQuizzes(int page) {
        Pageable paging = PageRequest.of(page, PAGE_SIZE);
        return quizRepository.findAll(paging)
                             .map(objectMapper::mapEntityToDto);
    }

    public QuizDto getQuizById(long id) {
        var quiz = quizRepository.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(
                                          HttpStatus.NOT_FOUND, "QuizEntity not found"));
        return objectMapper.mapEntityToDto(quiz);
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
        quizRepository.delete(quiz);
    }

    public boolean checkAnswer(long quizId, Set<Integer> answer) {
        var quizEntity = quizRepository.findById(quizId)
                                       .orElseThrow(() -> new ResponseStatusException(
                                                HttpStatus.NOT_FOUND,
                                                "QuizEntity not found"));

        var solved =
                 quizChecker.checkAnswer(objectMapper.mapEntityToDto(quizEntity), answer);
        if (solved) {
            var time = LocalDateTime.now();
            var accountEmail = currentAccountService.getCurrentAccount()
                                                    .getEmail();
            var account = accountRepository.findByEmail(accountEmail)
                                           .orElseThrow();
            var completion = new CompletionEntity();
            completion.setCompletedAt(time);
            completion.setAccountEntity(account);
            completion.setQuizEntity(quizEntity);
            account.addCompletion(completion);
            accountRepository.save(account);
        }
        return solved;
    }
}
