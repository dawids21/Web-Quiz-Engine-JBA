package engine.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class UserDTO {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email is not valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Length(min = 5, message = "Password should have at least 5 characters")
    private String password;

    public UserDTO() {
    }

    public UserDTO(@Valid String email, @Valid String password) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getEmail(), userDTO.getEmail()) &&
               Objects.equals(getPassword(), userDTO.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }

    @Override
    public String toString() {
        return "UserDTO{" + "email='" + email + '\'' + ", password='" + password + '\'' +
               '}';
    }
}
