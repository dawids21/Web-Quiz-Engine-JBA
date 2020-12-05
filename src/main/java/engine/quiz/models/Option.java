package engine.quiz.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quizEntity;

    private String text;

    public Option() {
    }

    public Option(String text) {
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Option option = (Option) o;
        return getId() == option.getId() && Objects.equals(getQuiz(), option.getQuiz()) &&
               Objects.equals(getText(), option.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuiz(), getText());
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", quizEntity=" + quizEntity + ", text='" + text +
               '\'' + '}';
    }
}
