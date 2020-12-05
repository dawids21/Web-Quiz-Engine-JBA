package engine.account.models;

import engine.account.EmailConstraint;
import engine.quiz.models.QuizEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Email is mandatory")
    @EmailConstraint(message = "Email is not valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 5, message = "Password should have at least 5 characters")
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<QuizEntity> quizEntities = new ArrayList<>();

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL)
    private List<Role> roles = new ArrayList<>();

    public AccountEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<QuizEntity> getQuizzes() {
        return quizEntities;
    }

    public void setQuizzes(List<QuizEntity> quizEntities) {
        this.quizEntities = quizEntities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addQuiz(QuizEntity quizEntity) {
        quizEntities.add(quizEntity);
        quizEntity.setOwner(this);
    }

    public void removeQuiz(QuizEntity quizEntity) {
        quizEntities.remove(quizEntity);
        quizEntity.setOwner(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountEntity accountEntity = (AccountEntity) o;
        return getId() == accountEntity.getId() &&
               Objects.equals(getEmail(), accountEntity.getEmail()) &&
               Objects.equals(getPassword(), accountEntity.getPassword()) &&
               Objects.equals(getQuizzes(), accountEntity.getQuizzes()) &&
               Objects.equals(getRoles(), accountEntity.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getQuizzes(), getRoles());
    }

    @Override
    public String toString() {
        return "AccountEntity{" + "id=" + id + ", email='" + email + '\'' +
               ", password='" + password + '\'' + ", quizEntities=" + quizEntities +
               ", roles=" + roles + '}';
    }
}