package engine.quiz;

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
    private Quiz quiz;

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

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
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
        return "Option{" + "id=" + id + ", quiz=" + quiz + ", text='" + text + '\'' + '}';
    }
}
