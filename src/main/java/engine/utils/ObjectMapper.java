package engine.utils;

import engine.account.models.AccountDto;
import engine.account.models.AccountEntity;
import engine.account.models.Role;
import engine.quiz.models.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ObjectMapper {

    public QuizEntity mapDtoToEntity(QuizDto source) {
        var quiz = new QuizEntity();
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

    public AccountDto mapEntityToDto(AccountEntity source) {
        var account = new AccountDto();
        account.setEmail(source.getEmail());
        account.setPassword(source.getPassword());
        account.setRoles(source.getRoles()
                               .stream()
                               .map(Role::getAuthority)
                               .collect(Collectors.toList()));
        return account;
    }

    public QuizDto mapEntityToDto(QuizEntity source) {
        var quiz = new QuizDto();
        quiz.setId(source.getId());
        quiz.setTitle(source.getTitle());
        quiz.setText(source.getText());
        quiz.setOptions(source.getOptions()
                              .stream()
                              .map(Option::getText)
                              .collect(Collectors.toList()));
        quiz.setAnswer(source.getAnswers()
                             .stream()
                             .map(Answer::getAnswer)
                             .collect(Collectors.toSet()));
        return quiz;
    }

    public CompletionDto mapEntityToDto(CompletionEntity source) {
        return new CompletionDto(source.getQuizEntity()
                                       .getId(), source.getCompletedAt());
    }
}
