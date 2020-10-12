package engine;

public class QuizChecker {

    private final Quiz quiz;
    private final int answer;

    public QuizChecker(Quiz quiz, int answer) {
        this.quiz = quiz;
        this.answer = answer;
    }

    public boolean isAnswerCorrect() {
        return quiz.getAnswer() == answer;
    }
}
