package engine.account;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return getId() == role.getId() &&
               Objects.equals(getAuthority(), role.getAuthority()) &&
               Objects.equals(getAccount(), role.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAuthority(), getAccount());
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", authority='" + authority + '\'' + ", account=" +
               account + '}';
    }
}
