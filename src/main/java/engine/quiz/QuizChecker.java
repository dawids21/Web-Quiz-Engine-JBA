package engine.quiz;

import engine.quiz.models.QuizDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class QuizChecker {

    public QuizChecker() {
    }

    public boolean checkAnswer(QuizDto quiz, Set<Integer> answer) {
        return answer.equals(quiz.getAnswer());
    }
}
