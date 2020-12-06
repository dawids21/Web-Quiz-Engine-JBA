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

    @OneToMany(mappedBy = "accountEntity", cascade = CascadeType.ALL)
    private List<CompletionEntity> completionEntities = new ArrayList<>();

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

    public List<QuizEntity> getQuizEntities() {
        return quizEntities;
    }

    public void setQuizEntities(List<QuizEntity> quizEntities) {
        this.quizEntities = quizEntities;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<CompletionEntity> getCompletionEntities() {
        return completionEntities;
    }

    public void setCompletionEntities(List<CompletionEntity> completionEntities) {
        this.completionEntities = completionEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountEntity that = (AccountEntity) o;
        return getId() == that.getId() && Objects.equals(getEmail(), that.getEmail()) &&
               Objects.equals(getPassword(), that.getPassword()) &&
               Objects.equals(getQuizEntities(), that.getQuizEntities()) &&
               Objects.equals(getRoles(), that.getRoles()) &&
               Objects.equals(getCompletionEntities(), that.getCompletionEntities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getQuizEntities(),
                            getRoles(), getCompletionEntities());
    }

    @Override
    public String toString() {
        return "AccountEntity{" + "id=" + id + ", email='" + email + '\'' +
               ", password='" + password + '\'' + ", quizEntities=" + quizEntities +
               ", roles=" + roles + ", completionEntities=" + completionEntities + '}';
    }
}
