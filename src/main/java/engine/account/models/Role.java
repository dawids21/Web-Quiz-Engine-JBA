package engine.account.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity accountEntity;

    public Role() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public AccountEntity getAccount() {
        return accountEntity;
    }

    public void setAccount(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
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
        return "Role{" + "id=" + id + ", authority='" + authority + '\'' +
               ", accountEntity=" + accountEntity + '}';
    }

    public enum Authority {
        USER, ADMIN
    }
}
