package engine.account;

import engine.account.models.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    Boolean existsByEmail(String email);

    Optional<AccountEntity> findByEmail(String email);
}
