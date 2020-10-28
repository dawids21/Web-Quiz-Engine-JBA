package engine;

import engine.models.Answer;
import engine.models.Option;
import engine.models.Quiz;
import engine.models.QuizInputDTO;

import java.util.stream.Collectors;

public class ObjectMapperUtils {

    public Quiz mapQuizInputDTOToQuiz(QuizInputDTO source) {
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
}
