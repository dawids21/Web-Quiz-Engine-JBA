package engine;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    private int answer;

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
        return "Answer{" + "id=" + id + ", quiz=" + quiz + ", answer=" + answer + '}';
    }
}
