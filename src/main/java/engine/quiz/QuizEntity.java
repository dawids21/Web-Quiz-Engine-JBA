package engine.quiz;

import engine.account.models.AccountEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Quizzes")
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id = 0;

    private String title;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity owner;

    @OneToMany(mappedBy = "quizEntity", cascade = CascadeType.ALL)
    private List<Option> options;

    @OneToMany(mappedBy = "quizEntity", cascade = CascadeType.ALL)
    private Set<Answer> answers = new HashSet<>();

    public QuizEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AccountEntity getOwner() {
        return owner;
    }

    public void setOwner(AccountEntity owner) {
        this.owner = owner;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answer) {
        this.answers = answer;
    }
}
