package engine.quiz.models;

import engine.account.models.AccountEntity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Completions")
public class CompletionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity accountEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quizEntity;

    public CompletionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public void setAccountEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
    }

    public QuizEntity getQuizEntity() {
        return quizEntity;
    }

    public void setQuizEntity(QuizEntity quizEntity) {
        this.quizEntity = quizEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CompletionEntity that = (CompletionEntity) o;
        return getId() == that.getId() &&
               Objects.equals(getCompletedAt(), that.getCompletedAt()) &&
               Objects.equals(getAccountEntity(), that.getAccountEntity()) &&
               Objects.equals(getQuizEntity(), that.getQuizEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCompletedAt(), getAccountEntity(),
                            getQuizEntity());
    }

    @Override
    public String toString() {
        return "CompletionEntity{" + "id=" + id + ", completedAt=" + completedAt +
               ", accountEntity=" + accountEntity + ", quizEntity=" + quizEntity + '}';
    }
}
