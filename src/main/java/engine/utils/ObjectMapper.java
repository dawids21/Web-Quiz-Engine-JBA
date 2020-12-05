package engine.utils;

import engine.account.models.AccountDto;
import engine.account.models.AccountEntity;
import engine.account.models.Role;
import engine.quiz.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    public Quiz mapQuizInputDTOToQuiz(QuizInputDto source) {
        var quiz = new Quiz();
        quiz.setTitle(source.getTitle());
        quiz.setText(source.getText());
        quiz.setOptions(source.getOptions()
                              .stream()
                              .map(Option::new)
                              .collect(Collectors.toList()));
        quiz.setAnswers(source.getAnswer()
                              .stream()
                              .map(Answer::new)
                              .collect(Collectors.toSet()));
        return quiz;
    }

    public QuizWithoutAnswerDto mapQuizToQuizDTOWithoutAnswer(Quiz source) {
        var quiz = new QuizWithoutAnswerDto();
        quiz.setId(source.getId());
        quiz.setTitle(source.getTitle());
        quiz.setText(source.getText());
        quiz.setOptions(source.getOptions()
                              .stream()
                              .map(Option::getText)
                              .collect(Collectors.toList()));
        return quiz;
    }

    public AccountDto mapAccountToAccountDTO(AccountEntity source) {
        var account = new AccountDto();
        account.setEmail(source.getEmail());
        account.setPassword(source.getPassword());
        account.setRoles(source.getRoles()
                               .stream()
                               .map(Role::getAuthority)
                               .collect(Collectors.toList()));
        return account;
    }
}
