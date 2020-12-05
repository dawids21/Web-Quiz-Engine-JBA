package engine.quiz.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quizEntity;

    private int answer;

    public Answer() {
    }

    public Answer(int answer) {
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public QuizEntity getQuiz() {
        return quizEntity;
    }

    public void setQuiz(QuizEntity quizEntity) {
        this.quizEntity = quizEntity;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Answer answer1 = (Answer) o;
        return getId() == answer1.getId() && getAnswer() == answer1.getAnswer() &&
               Objects.equals(getQuiz(), answer1.getQuiz());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuiz(), getAnswer());
    }

    @Override
    public String toString() {
        return "Answer{" + "id=" + id + ", quizEntity=" + quizEntity + ", answer=" +
               answer + '}';
    }
}
