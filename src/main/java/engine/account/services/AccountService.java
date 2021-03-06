package engine.account.services;

import engine.account.AccountNotFoundException;
import engine.account.AccountRepository;
import engine.account.PasswordEncryptor;
import engine.account.models.AccountDto;
import engine.account.models.AccountEntity;
import engine.account.models.Role;
import engine.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncryptor passwordEncryptor;
    private final ObjectMapper objectMapper;

    @Autowired
    public AccountService(AccountRepository accountRepository,
                          PasswordEncryptor passwordEncryptor,
                          ObjectMapper objectMapper) {
        this.accountRepository = accountRepository;
        this.passwordEncryptor = passwordEncryptor;
        this.objectMapper = objectMapper;
    }

    public void addAccount(AccountDto accountDTO) {
        if (accountRepository.existsByEmail(accountDTO.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                              "Email " + accountDTO.getEmail() +
                                              " already exists");
        }
        var account = new AccountEntity();
        account.setEmail(accountDTO.getEmail());
        account.setPassword(passwordEncryptor.encrypt(accountDTO.getPassword()));
        var role = new Role();
        role.setAccount(account);
        role.setAuthority(Role.Authority.USER);
        var roles = new ArrayList<Role>();
        roles.add(role);
        account.setRoles(roles);
        accountRepository.save(account);
    }

    public AccountDto getAccount(long id) throws AccountNotFoundException {
        var entity = accountRepository.findById(id)
                                      .orElseThrow(AccountNotFoundException::new);
        return objectMapper.mapEntityToDto(entity);
    }

    public AccountDto getAccount(String email) throws AccountNotFoundException {
        var entity = accountRepository.findByEmail(email)
                                      .orElseThrow(AccountNotFoundException::new);
        return objectMapper.mapEntityToDto(entity);
    }
}
