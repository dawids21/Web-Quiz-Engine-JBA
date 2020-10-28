package engine;

import engine.models.*;

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

    public QuizDTOWithoutAnswer mapQuizToQuizDTOWithoutAnswer(Quiz source) {
        var quiz = new QuizDTOWithoutAnswer();
        quiz.setId(source.getId());
        quiz.setTitle(source.getTitle());
        quiz.setText(source.getText());
        quiz.setOptions(source.getOptions()
                              .stream()
                              .map(Option::getText)
                              .collect(Collectors.toList()));
        return quiz;
    }
}
