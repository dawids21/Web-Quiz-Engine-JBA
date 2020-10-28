package engine.services;

import engine.models.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuizChecker {

    private final QuizRepository repository;

    @Autowired
    public QuizChecker(QuizRepository repository) {
        this.repository = repository;
    }

    public boolean checkAnswer(long id, Set<Integer> answer) {
        var quiz = repository.findById(id)
                             .orElseThrow(() -> new ResponseStatusException(
                                      HttpStatus.NOT_FOUND, "Quiz not found"));
        var correctAnswer = quiz.getAnswers()
                                .stream()
                                .map(Answer::getAnswer)
                                .collect(Collectors.toSet());
        return correctAnswer.equals(answer);
    }
}
