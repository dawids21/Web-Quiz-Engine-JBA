package engine.account.models;

import engine.account.EmailConstraint;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountDto {

    @NotBlank(message = "Email is mandatory")
    @EmailConstraint(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 5, message = "Password should have at least 5 characters")
    private String password;

    private List<Role.Authority> roles = new ArrayList<>();

    public AccountDto() {
    }

    public AccountDto(@Valid String email, @Valid String password) {
        this.email = email;
        this.password = password;
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

    public List<Role.Authority> getRoles() {
        return roles;
    }

    public void setRoles(List<Role.Authority> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountDto that = (AccountDto) o;
        return Objects.equals(getEmail(), that.getEmail()) &&
               Objects.equals(getPassword(), that.getPassword()) &&
               Objects.equals(getRoles(), that.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword(), getRoles());
    }

    @Override
    public String toString() {
        return "AccountDto{" + "email='" + email + '\'' + ", password='" + password +
               '\'' + ", roles=" + roles + '}';
    }
}
