package engine.account;

import engine.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncryptor passwordEncryptor;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          PasswordEncryptor passwordEncryptor) {
        this.accountRepository = accountRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    public void addUser(AccountDTO accountDTO) {
        if (accountRepository.existsByEmail(accountDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Email " + accountDTO.getEmail() +
                                              " already exists");
        }
        var user = new Account();
        user.setEmail(accountDTO.getEmail());
        user.setPassword(passwordEncryptor.encrypt(accountDTO.getPassword()));
        accountRepository.save(user);
    }
}
