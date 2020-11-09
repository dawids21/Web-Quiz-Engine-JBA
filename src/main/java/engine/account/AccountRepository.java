package engine.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);
}
